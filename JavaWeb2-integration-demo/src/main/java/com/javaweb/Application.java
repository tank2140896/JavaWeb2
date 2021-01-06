package com.javaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude={
		DataSourceAutoConfiguration.class,//去除数据源默认配置
		RedisAutoConfiguration.class,//去除redis默认配置
		RedisRepositoriesAutoConfiguration.class,//去除redis默认配置
		MultipartAutoConfiguration.class//去除文件上传默认配置
})
public class Application {
	
	//不要运行我！！！
    public static void main(String[] args) {
    	SpringApplication.run(Application.class,args);//最简单的启动写法
    }

}
