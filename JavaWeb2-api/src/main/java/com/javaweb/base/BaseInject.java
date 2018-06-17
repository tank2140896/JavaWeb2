package com.javaweb.base;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

public class BaseInject {
	
	/**
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	@Autowired
	protected Neo4jTemplate neo4jTemplate;
	*/
	
	@Autowired
	protected Environment environment;
	
	@Autowired  
	protected SolrClient solrClient;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Autowired
    protected MessageSource messageSource;
	
	@Autowired
	protected KafkaTemplate<String,String> kafkaTemplate;
	
	@Autowired
	protected RedisTemplate<String,String> redisTemplate;

}
