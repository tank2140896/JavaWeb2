package com.javaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages={"com.javaweb"})
public class Application {
	
    public static void main(String[] args) {
    	SpringApplication.run(Application.class,args);
    }

}
