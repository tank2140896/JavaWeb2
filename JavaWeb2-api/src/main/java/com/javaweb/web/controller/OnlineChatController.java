package com.javaweb.web.controller;

import java.util.LinkedList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/web/other/onlineChat")
@ServerEndpoint(value="/web/other/onlineChat/chat")
public class OnlineChatController {
	
	public static LinkedList<Session> client = new LinkedList<Session>();
	//public static Map<String,String> user = new HashMap<String,String>(); 

	@OnMessage
	public void onMessage(String message,Session session/*,@PathParam("username") String username*/) {
		try{
			/**
			JSONObject jo = new JSONObject();
			JSONObject inner = new JSONObject();
			inner.put("message", message);
			inner.put("username", username);
			jo.put("onlineMessage", inner);
			*/
			for (Session c : client) {
				c.getBasicRemote().sendText(message/*jo.toString()*/);
			}
		}catch(Exception e){
			//do nothing
		}
	}

	@OnOpen
	public void onOpen(Session session/*,@PathParam("username") String username*/) {
		try{
			client.add(session);
			/**
			user.put(URLEncoder.encode(username,"UTF-8"),URLEncoder.encode(username,"UTF-8"));
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();
			//获得在线用户列表
			Set<String> key = user.keySet();
			for (String u : key) {
				ja.add(u);
			}
			jo.put("onlineUser", ja);
			session.getBasicRemote().sendText(jo.toString());
			*/
		}catch(Exception e){
			//do nothing
		}
	}

	@OnClose
	public void onClose(Session session/*,@PathParam("username") String username*/) {
		try{
			client.remove(session);
			//user.remove(URLEncoder.encode(username, "UTF-8"));
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
