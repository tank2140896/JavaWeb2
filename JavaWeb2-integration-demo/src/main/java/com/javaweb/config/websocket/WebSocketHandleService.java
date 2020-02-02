package com.javaweb.config.websocket;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.eo.chat.ChatResponse;

import lombok.Getter;
import lombok.Setter;

@Service
@ServerEndpoint(value="/websocket/{key}")  
public class WebSocketHandleService {
	
	public static LinkedList<Session> client = new LinkedList<Session>();//如果是分布式部署的话,这里推荐采用redis进行存储替代
	
	public static Map<String,User> userMap = new HashMap<String,User>();//如果是分布式部署的话,这里推荐采用redis进行存储替代 

	@OnMessage
	public void onMessage(String message,User user) {
		try{
			ChatResponse chatResponse = new ChatResponse();
			chatResponse.setMessage(message);
			chatResponse.setUserId(user.getUserId());
			chatResponse.setUserName(user.getUserName());
			ObjectMapper objectMapper = new ObjectMapper();
			for (Session session:client) {
				session.getBasicRemote().sendText(objectMapper.writeValueAsString(chatResponse));
			}
		}catch(Exception e){
			//do nothing
		}
	}

	@OnOpen
	//@SuppressWarnings("unchecked")
	public void onOpen(Session session,@PathParam("key") String key) {
		try{
			//可以从redis获得用户信息
			//RedisTemplate<String,Object> redisTemplate = (RedisTemplate<String,Object>)ApplicationContextHelper.getBean("redisTemplate");
			//TokenData tokenData = (TokenData)redisTemplate.opsForValue().get(key);
			TokenData tokenData = new TokenData();
			if(tokenData!=null){
				User user = tokenData.getUser();
				if(user!=null){
					if(userMap.get(key)==null){
						client.add(session);
						userMap.put(key,user);
					}
				}
			}
		}catch(Exception e){
			//do nothing
		}
	}

	@OnClose
	public void onClose(Session session,@PathParam("key") String key) {
		try{
			client.remove(session);
			userMap.remove(key);
			session.close();
		}catch(Exception e){
			//do nothing
		}
	}
	
	@OnError
	public void onError(Throwable throwable){
		//do nothing
	}

}

@Getter
@Setter
class User {
	
	private String userId;
	
	private String userName;
	
}

@Getter
@Setter
class TokenData{ 
	
	private User user;
	
}