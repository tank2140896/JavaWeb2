package com.javaweb.base;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import com.netflix.discovery.EurekaClient;

public class BaseInject {
    
    @Autowired
    protected EurekaClient eurekaClient;
    
    @Autowired
    protected DiscoveryClient discoveryClient;
	
	@Autowired
	protected Environment environment;
	
	@Autowired
    protected MessageSource messageSource;
	
	@Autowired
	@Qualifier("mysql_d1_JdbcTemplate")
	protected JdbcTemplate mysql_d1_JdbcTemplate;
	
	@Autowired
	@Qualifier("mysql_d2_JdbcTemplate")
	protected JdbcTemplate mysql_d2_JdbcTemplate;
	
	/** 两种装配bean的方式  start */
	@Autowired
	@Qualifier("redisTemplate")
	protected StringRedisTemplate stringRedisTemplate;
	
	@Resource(name="redisTemplate")
	protected ValueOperations<Object,Object> valueOperations;
	/** 两种装配bean的方式  end */
	
}
