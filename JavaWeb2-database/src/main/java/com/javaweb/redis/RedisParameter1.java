package com.javaweb.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class RedisParameter1 {
	
	@Value("${redis1.host:127.0.0.1}") 
	private String host;
	
	@Value("${redis1.password:}") 
	private String password;
	
	@Value("${redis1.port:6379}")
	private int port;
	
	@Value("${redis1.minIdle:5}")
	private int minIdle;
	
	@Value("${redis1.maxIdle:100}")
	private int maxIdle;
	
	@Value("${redis1.maxTotal:1000}")
	private int maxTotal;
	
	@Value("${redis1.maxWaitMillis:-1}")
	private long maxWaitMillis;
	
	@Value("${redis1.commandTimeout:5000}")
	private long commandTimeout;

}
