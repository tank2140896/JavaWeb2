package com.javaweb.config.websocket;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

@Configuration
public class NettyWebSocketServerConfig {
	
	@Value("${netty.websocket.server.port}")
    private Integer nettyWebsocketServerPort;
	
	@PostConstruct
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
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.option(ChannelOption.SO_BACKLOG,1024);
			bootstrap.group(group).channel(NioServerSocketChannel.class).
            localAddress(nettyWebsocketServerPort).childHandler
			(new ChannelInitializer<NioSocketChannel>() {
				@Override
				public void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
					nioSocketChannel.pipeline().addLast(new HttpServerCodec());
					nioSocketChannel.pipeline().addLast(new ChunkedWriteHandler());
					nioSocketChannel.pipeline().addLast(new HttpObjectAggregator(8192));
					nioSocketChannel.pipeline().addLast(new WebSocketServerProtocolHandler("/ws","WebSocket",true,65536*10));
					nioSocketChannel.pipeline().addLast(new NettyWebSocketServerHandler());
				} 
			});
			ChannelFuture channelFuture = bootstrap.bind().sync();
			channelFuture.channel().closeFuture().sync();
		}finally{
			group.shutdownGracefully().sync();
		}
	}

}
