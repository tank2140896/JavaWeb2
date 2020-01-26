package com.javaweb.config.redis;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import com.javaweb.redis.RedisConfig;

@Configuration
@EnableCaching
public class MyRedisConfig extends RedisConfig{

}
