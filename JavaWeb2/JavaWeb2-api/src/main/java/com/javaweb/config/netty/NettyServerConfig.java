package com.javaweb.config.netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyServerConfig {
	
	@Autowired
	private NettyServerHandle nettyServerHandle;
	
	/**
	 * 使用请解注
	 * import javax.annotation.PostConstruct;
	 * @PostConstruct
	 */
	public void init(){
		Runnable runnable = ()->{
			try {
				start();
			} catch (Exception e) {
				System.err.println("Netty服务端启动失败，失败原因为："+e.getMessage());
			}
		};
		new Thread(runnable).start();
	}
	
	public void start() throws Exception {
		//final NettyServerHandle nettyServer = new NettyServerHandle();
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(1107)).childHandler(new ChannelInitializer<Channel>() {
				 @Override
				 protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(nettyServerHandle);
				 } 
			 });
			ChannelFuture f = b.bind().sync();
			f.channel().closeFuture().sync();
		}finally{
			group.shutdownGracefully().sync();
		}
	}

}
