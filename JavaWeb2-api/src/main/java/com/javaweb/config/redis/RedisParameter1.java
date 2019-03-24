package com.javaweb.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisParameter1 {
	
	@Value("${redis1.host}") 
	private String host;
	
	@Value("${redis1.password}") 
	private String password;
	
	@Value("${redis1.port}")
	private int port;
	
	@Value("${redis1.minIdle}")
	private int minIdle;
	
	@Value("${redis1.maxIdle}")
	private int maxIdle;
	
	@Value("${redis1.maxTotal}")
	private int maxTotal;
	
	@Value("${redis1.maxWaitMillis}")
	private long maxWaitMillis;
	
	@Value("${redis1.commandTimeout}")
	private long commandTimeout;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public long getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public long getCommandTimeout() {
		return commandTimeout;
	}

	public void setCommandTimeout(long commandTimeout) {
		this.commandTimeout = commandTimeout;
	}

}
