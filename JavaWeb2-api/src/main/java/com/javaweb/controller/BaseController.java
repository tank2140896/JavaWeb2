package com.javaweb.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class BaseController {
	
	@Autowired
	public RedisTemplate<String,String> redisTemplate;
	
	@Resource(name="redisTemplate")
	public ValueOperations<Object,Object> valueOperations;

}
