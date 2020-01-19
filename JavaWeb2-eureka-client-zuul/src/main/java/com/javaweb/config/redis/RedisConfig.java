package com.javaweb.config.redis;

import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

//集群模式建议采用此配置
@Configuration
@EnableCaching
public class RedisConfig {
	
	@Autowired
	private RedisParameter1 redisParameter1;
	
	//redis连接1
	public RedisConnectionFactory getRedisConnectionFactory1() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(redisParameter1.getHost());
		redisStandaloneConfiguration.setPassword(redisParameter1.getPassword());
		redisStandaloneConfiguration.setPort(redisParameter1.getPort());
		GenericObjectPoolConfig<?> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
		genericObjectPoolConfig.setMinIdle(redisParameter1.getMinIdle());
		genericObjectPoolConfig.setMaxIdle(redisParameter1.getMaxIdle());
		genericObjectPoolConfig.setMaxTotal(redisParameter1.getMaxTotal());
		genericObjectPoolConfig.setMaxWaitMillis(redisParameter1.getMaxWaitMillis());
		LettuceClientConfiguration lettuceClientConfiguration = LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofMillis(redisParameter1.getCommandTimeout())).poolConfig(genericObjectPoolConfig).build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration,lettuceClientConfiguration);
        factory.afterPropertiesSet();//必须初始化实例
        return factory;
	}
	
    @Bean("redisTemplate1")
	public StringRedisTemplate redisTemplate1(){
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(getRedisConnectionFactory1());
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL,Visibility.ANY);
		objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
		stringRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		stringRedisTemplate.afterPropertiesSet();
		return stringRedisTemplate;
	}
    
}
