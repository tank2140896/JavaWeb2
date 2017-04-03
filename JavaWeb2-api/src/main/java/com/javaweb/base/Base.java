package com.javaweb.base;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class Base {
	
	@Autowired
	public RedisTemplate<String,String> redisTemplate;
	
	@Resource(name="redisTemplate")
	public ValueOperations<Object,Object> valueOperations;
	
	/**
	@Autowired
	public MongoTemplate mongoTemplate;
	
	@Autowired
	public Neo4jTemplate neo4jTemplate;
	*/

}
