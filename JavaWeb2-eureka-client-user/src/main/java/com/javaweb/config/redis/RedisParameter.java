package com.javaweb.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class RedisParameter {
	
	@Value("${redis.host:127.0.0.1}") 
	private String host;
	
	@Value("${redis.password:}") 
	private String password;
	
	@Value("${redis.port:6379}")
	private int port;
	
	@Value("${redis.minIdle:5}")
	private int minIdle;
	
	@Value("${redis.maxIdle:100}")
	private int maxIdle;
	
	@Value("${redis.maxTotal:1000}")
	private int maxTotal;
	
	@Value("${redis.maxWaitMillis:-1}")
	private long maxWaitMillis;
	
	@Value("${redis.commandTimeout:5000}")
	private long commandTimeout;

}
