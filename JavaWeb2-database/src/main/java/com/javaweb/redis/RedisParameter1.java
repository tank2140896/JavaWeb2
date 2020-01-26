package com.javaweb.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
