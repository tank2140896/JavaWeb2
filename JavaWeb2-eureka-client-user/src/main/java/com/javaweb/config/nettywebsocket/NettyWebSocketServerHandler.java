package com.javaweb.config.nettywebsocket;

import java.time.Duration;

import org.springframework.core.env.Environment;

import com.javaweb.base.BaseTool;
import com.javaweb.constant.SystemConstant;
import com.javaweb.context.ApplicationContextHelper;
import com.javaweb.web.eo.TokenData;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import net.sf.json.JSONObject;

//若要被Controller调用加上@Service即可
public class NettyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	
	private Environment environment = null;
	
	public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
		String content = textWebSocketFrame.text();
		//System.out.println("接收到客户端的消息为:"+content);
		if(environment==null){
			environment = (Environment)ApplicationContextHelper.getBean(SystemConstant.ENVIRONMENT);
		}
		Long redisSessionTimeout = Long.parseLong(environment.getProperty("redis.session.timeout"));//获得配置文件中redis设置session失效的时间
		TokenData tokenData = BaseTool.getTokenData(JSONObject.fromObject(content).getString(SystemConstant.HEAD_TOKEN));
		if(tokenData!=null){
			sendMessageToAll(content);
			BaseTool.getRedisTemplate().opsForValue().set(tokenData.getToken(),tokenData,Duration.ofMinutes(redisSessionTimeout));
		}else{
			this.channelInactive(channelHandlerContext);
		}
		//sendMessageToOne("指定某人发信息",channelHandlerContext);
		//sendMessageToAll("给所有人发信息");
	}

	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//System.out.println("与客户端建立连接");
		channelGroup.add(ctx.channel());
	}

	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		//System.out.println("与客户端断开连接");
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
