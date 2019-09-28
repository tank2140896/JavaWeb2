package com.javaweb.base;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
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
	
	@Autowired
	protected KafkaTemplate<String,String> kafkaTemplate;
	*/
	
	@Autowired
	@Qualifier("mysql_d1_JdbcTemplate")
	protected JdbcTemplate mysql_d1_JdbcTemplate;
	
	@Autowired
	@Qualifier("mysql_d2_JdbcTemplate")
	protected JdbcTemplate mysql_d2_JdbcTemplate;
	
	@Autowired
	@Qualifier("redisTemplate1")
	protected StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	protected Environment environment;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Autowired
    protected MessageSource messageSource;
	
	@Autowired  
	protected SolrClient solrClient;
	
	@Autowired
	protected JavaMailSender javaMailSender;
	
}