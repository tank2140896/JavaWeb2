package com.javaweb.config.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyClientConfig {
	
	@Autowired
	private NettyClientHandle nettyClientHandle;
	
	/** 使用请解注
	import javax.annotation.PostConstruct;
	@PostConstruct
	*/
	public void init(){
		Runnable runnable = ()->{
			try {
				start();
			} catch (Exception e) {
				System.err.println("Netty客户端启动失败，失败原因为："+e.getMessage());
			}
		};
		new Thread(runnable).start();
	}
	
	public void start() throws Exception {
		//final NettyClientHandle nettyClient = new NettyClientHandle();
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress("127.0.0.1",1107)).handler(new ChannelInitializer<SocketChannel>() {
				 @Override
				 public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(nettyClientHandle);
				 } 
			 });
			ChannelFuture f = b.connect().sync();
			f.channel().closeFuture().sync();
		}finally{
			group.shutdownGracefully().sync();
		}
	}

}
