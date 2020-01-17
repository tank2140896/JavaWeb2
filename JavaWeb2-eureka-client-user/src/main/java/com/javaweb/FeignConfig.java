package com.javaweb;

import org.springframework.context.annotation.Configuration;

import feign.Retryer;

@Configuration
public class FeignConfig {

    public Retryer feignRetryer() {
        return new Retryer.Default(100,1000,3);
    }
    
}
