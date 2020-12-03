package com.javaweb.config.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.websocket.Session;

public class OnlineSystemLogThread extends Thread {

	private BufferedReader bufferedReader;
	
	private Session session;
	
	public OnlineSystemLogThread(InputStream inputStream,Session session) {
		this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		this.session = session;
	}
	
	public void run() {
		String line;
		try {
			while((line=bufferedReader.readLine())!=null) {
				session.getBasicRemote().sendText(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
