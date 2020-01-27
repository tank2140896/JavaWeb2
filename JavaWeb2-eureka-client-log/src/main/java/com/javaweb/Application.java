package com.javaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude={
		DataSourceAutoConfiguration.class,//去除数据源默认配置
		RedisAutoConfiguration.class,//去除redis默认配置
		RedisRepositoriesAutoConfiguration.class//去除redis默认配置
})
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages={"com.javaweb"})
//@MapperScan("com.javaweb.web.dao")//这样写就不必在所有的dao接口上加@Mapper
public class Application {
	
    public static void main(String[] args) {
    	SpringApplication.run(Application.class,args);//最简单的启动写法
    }

}
