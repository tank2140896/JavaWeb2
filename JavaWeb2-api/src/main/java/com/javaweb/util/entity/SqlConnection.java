package com.javaweb.util.entity;

public class SqlConnection {
	
	private int port = 3306;
	
	private String ip = "127.0.0.1";
	
	private String username = "root";
	
	private String password = "root";
	
	private String db = "test";
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}
	
}
