package com.javaweb.config.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NettyWebSocketClientHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	
	public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
		String content = textWebSocketFrame.text();
		System.out.println("接收到客户端的消息为:"+content);
		sendMessageToOne("指定某人发信息",channelHandlerContext);
		sendMessageToAll("给所有人发信息");
	}

	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("与客户端建立连接");
		channelGroup.add(ctx.channel());
	}

	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("与客户端断开连接");
		channelGroup.remove(ctx.channel());
	}
	
	//给指定的人发消息
	public void sendMessageToOne(String message,ChannelHandlerContext ctx){
		ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
	}
	
	//给所有人发消息
	public void sendMessageToAll(String message){
		channelGroup.writeAndFlush(new TextWebSocketFrame(message));
	}
	
}
