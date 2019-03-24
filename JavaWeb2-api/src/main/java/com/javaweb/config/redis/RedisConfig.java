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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        return factory;
	}
	
	@Bean("redisTemplate1")
	public RedisTemplate<String,String> redisTemplate1(){
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(getRedisConnectionFactory1());
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL,Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
		stringRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		stringRedisTemplate.afterPropertiesSet();
		return stringRedisTemplate;
	}
    
}

/**
 * 单一模式建议采用此配置
 * spring.redis.host=192.168.0.103
 * spring.redis.password=
 * spring.redis.port=6379
 * spring.redis.pool.max-idle=100
 * spring.redis.pool.min-idle=5
 * spring.redis.pool.max-active=100
 * spring.redis.pool.max-wait=-1
 * spring.redis.timeout=5000
 * #spring-session-redis配置
 * #spring.session.store-type=Redis
 * #redis缓存10分钟
 * spring.cache.redis.time-to-live=600000
@Configuration
@EnableCaching
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds=900)//900秒
public class RedisConfig extends CachingConfigurerSupport {
	
	@Bean
	public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL,Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
		stringRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		stringRedisTemplate.afterPropertiesSet();
		return stringRedisTemplate;
	}
	
    //定义缓存数据key生成策略(包名+类名+方法名+所有参数) 
    @Bean  
    public KeyGenerator keyGenerator(){  
        return new KeyGenerator() {  
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder stringBuilder = new StringBuilder();  
				stringBuilder.append(target.getClass().getName());  
				stringBuilder.append(method.getName());  
                for (Object obj : params) {  
                	stringBuilder.append(obj.toString());  
                }  
                return stringBuilder.toString();
			}  
        };  
    }
    
    //1.新版本不需要这个Bean了;2.使用：@Cacheable(value="a");3.要缓存的Java对象必须实现Serializable接口
    //@Bean  
    //public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) { 
    //	  RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);//RedisCacheManager redisCacheManager = RedisCacheManager.create(redisTemplate.getRequiredConnectionFactory());  
    //	  Map<String,Long> expires = new HashMap<>();
	//	  cacheManager.setExpires(expires);
	//	  cacheManager.setDefaultExpiration(15*60);//默认缓存15分钟  
    //    return cacheManager;  
    //}  
	
}
*/
