package com.javaweb.base;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.solr.client.solrj.SolrClient;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.javaweb.config.hbase.HbaseHandleService;
import com.javaweb.config.websocket.WebSocketHandleService;

public class BaseInject {
    
	@Autowired
	protected HbaseHandleService hbaseHandleService;
	
	@Autowired
	protected KafkaConsumer<String,String> kafkaConsumer;
	
	@Autowired
	protected KafkaProducer<String,String> kafkaProduct;
	
	@Autowired
	protected KafkaTemplate<String,String> kafkaTemplate;
	
	@Autowired  
	protected SolrClient solrClient;
	
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	@Autowired
	protected WebSocketHandleService webSocketHandleService;
	
	@Autowired
	protected TransportClient transportClient;
	
	@Autowired
	protected DefaultKaptcha defaultKaptcha;
	
	@Autowired
	protected JavaMailSender javaMailSender;
	
	@Autowired
	@Qualifier("redisTemplate1")
	protected StringRedisTemplate stringRedisTemplate1;
	
	@Autowired
	@Qualifier("redisTemplate2")
	protected StringRedisTemplate stringRedisTemplate2;
	
	@Autowired
	protected RestTemplate restTemplate;
	
}
