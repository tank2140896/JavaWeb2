package com.javaweb.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class RedisParameter2 {
	
	@Value("${redis2.host:127.0.0.1}") 
	private String host;
	
	@Value("${redis2.password:}") 
	private String password;
	
	@Value("${redis2.port:6379}")
	private int port;
	
	@Value("${redis2.minIdle:5}")
	private int minIdle;
	
	@Value("${redis2.maxIdle:100}")
	private int maxIdle;
	
	@Value("${redis2.maxTotal:1000}")
	private int maxTotal;
	
	@Value("${redis2.maxWaitMillis:-1}")
	private long maxWaitMillis;
	
	@Value("${redis2.commandTimeout:5000}")
	private long commandTimeout;

}
