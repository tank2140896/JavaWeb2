package com.javaweb.config;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    
    /**
    //1、新版本不需要这个Bean了
    //2、使用：@Cacheable(value="a")
    //3、要缓存的Java对象必须实现Serializable接口
    @Bean  
    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) { 
     	//RedisCacheManager redisCacheManager = RedisCacheManager.create(redisTemplate.getRequiredConnectionFactory());
    	RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);  
    	Map<String,Long> expires = new HashMap<>();
		cacheManager.setExpires(expires);
		cacheManager.setDefaultExpiration(15*60);//默认缓存15分钟  
        return cacheManager;  
    }  
    */
	
}
