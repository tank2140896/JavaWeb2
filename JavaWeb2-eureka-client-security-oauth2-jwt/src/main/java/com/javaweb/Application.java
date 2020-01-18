package com.javaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})//采用多数据源时需要加上
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages={"com.javaweb"})
public class Application {
	
    public static void main(String[] args) {
    	SpringApplication.run(Application.class,args);
    }

}
