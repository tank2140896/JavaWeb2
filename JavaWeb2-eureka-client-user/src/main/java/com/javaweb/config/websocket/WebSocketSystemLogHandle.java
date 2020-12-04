package com.javaweb.config.websocket;

import java.io.IOException;
import java.io.InputStream;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

//注意：无法注入
@Component
@ServerEndpoint(value="/ws/systemLog") 
public class WebSocketSystemLogHandle {
	
	private Process process;
	
	private InputStream inputStream;
	
	@OnMessage
	public void onMessage(String message) {
		System.out.println("接收到的消息为："+message);
	}

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("成功建立连接");
		try{
			//process = Runtime.getRuntime().exec("cmd.exe /k type D:\\javaweb.log |more");
			process = Runtime.getRuntime().exec("tail -f /tmp/javaweb.log");
			inputStream = process.getInputStream();
			new OnlineSystemLogThread(inputStream,session).start();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("连接关闭");
		try{
			if(inputStream!=null){
				inputStream.close();
			}
			if(process!=null){
				process.destroy();
			}
			session.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@OnError
	public void onError(Throwable throwable){
		System.out.println(throwable.getMessage());
	}

}
