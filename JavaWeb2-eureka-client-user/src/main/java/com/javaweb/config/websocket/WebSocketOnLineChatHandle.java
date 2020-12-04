package com.javaweb.config.websocket;

import java.io.IOException;
import java.util.LinkedList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.web.eo.onlineChat.OnlineChatRequest;
import com.javaweb.web.eo.onlineChat.OnlineChatResponse;

//注意：无法注入
@Component
@ServerEndpoint(value="/ws/onlineChat") 
public class WebSocketOnLineChatHandle {
	
	public static LinkedList<Session> client = new LinkedList<Session>();//如果是分布式部署的话，这里推荐采用redis进行存储替代
	
	@OnMessage
	public void onMessage(String message) {
		try{
			OnlineChatRequest onlineChatRequest = new ObjectMapper().readValue(message,OnlineChatRequest.class);
			OnlineChatResponse onlineChatResponse = new OnlineChatResponse();
			onlineChatResponse.setMessage(onlineChatRequest.getMessage());
			onlineChatResponse.setUserId(onlineChatRequest.getUserId());
			onlineChatResponse.setUserName(onlineChatRequest.getUserName());
			for (Session session:client) {
				session.getBasicRemote().sendText(new ObjectMapper().writeValueAsString(onlineChatResponse));
			}
		}catch(Exception e){
			//do nothing
		}
	}

	@OnOpen
	public void onOpen(Session session) {
		client.add(session);
		System.out.println("成功建立连接");
	}

	@OnClose
	public void onClose(Session session) {
		client.remove(session);
		try{
			session.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println("连接关闭");
	}
	
	@OnError
	public void onError(Throwable throwable){
		System.out.println(throwable.getMessage());
	}

}
