package com.javaweb.web.controller;

import org.springframework.context.annotation.Configuration;

import feign.Retryer;

@Configuration
public class UserServerFeignConfig {

    public Retryer feignRetryer() {
        return new Retryer.Default(100,1000,3);
    }
    
}
