package com.javaweb.config.websocket;

import java.io.IOException;
import java.util.LinkedList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

//@Component
@Service
@ServerEndpoint(value="/websocket/{key}")  
public class WebSocketHandleService {
	
	public static LinkedList<Session> client = new LinkedList<Session>();//如果是分布式部署的话,这里推荐采用redis进行存储替代
	
	@OnMessage
	public void onMessage(String message) {
		for(Session session:client){
			try{
				session.getBasicRemote().sendText(message);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	@OnOpen
	public void onOpen(Session session) {
		client.add(session);
	}

	@OnClose
	public void onClose(Session session) {
		client.remove(session);
		try{
			session.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@OnError
	public void onError(Throwable throwable){
		//do nothing
	}

}