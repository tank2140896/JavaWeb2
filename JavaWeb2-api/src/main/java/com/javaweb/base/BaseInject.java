package com.javaweb.base;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

public class BaseInject {
	
	/**
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	@Autowired
	protected Neo4jTemplate neo4jTemplate;
	
	@Autowired
	protected TransportClient transportClient;
	
	@Autowired
	protected KafkaConsumer<String,String> kafkaConsumer;
	
	@Autowired
	protected KafkaProducer<String,String> kafkaProduct;
	*/
	
	@Autowired
	@Qualifier("mysql_d1_JdbcTemplate")
	protected JdbcTemplate mysql_d1_JdbcTemplate;
	
	@Autowired
	@Qualifier("mysql_d2_JdbcTemplate")
	protected JdbcTemplate mysql_d2_JdbcTemplate;
	
	@Autowired
	@Qualifier("redisTemplate1")
	protected RedisTemplate<String,String> redisTemplate1;
	
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
	
}
