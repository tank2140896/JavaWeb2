package com.javaweb.base;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ValueOperations;

public class BaseTool extends BaseInject {
	
	@Resource(name="redisTemplate1")
	protected ValueOperations<Object,Object> valueOperations1;
	
	public void setDataToRedis1(String key,Object value,long timeOut,TimeUnit timeUnit){
		valueOperations1.set(key,value,timeOut,timeUnit);
	}
	
	public void setDefaultDataToRedis1(String key,Object value){
		valueOperations1.set(key,value,30L,TimeUnit.MINUTES);
	}
	
	public Object getDateFromRedis1(String key){
		return valueOperations1.get(key);
	}
	
	public boolean deleteFromRedisByKey1(String key) {
		return stringRedisTemplate1.delete(key);
	}

}
