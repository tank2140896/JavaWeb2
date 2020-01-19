package com.javaweb.web.docking;

import org.springframework.context.annotation.Configuration;

import feign.Retryer;

@Configuration
public class LogServerFeignConfig {

    public Retryer feignRetryer() {
        return new Retryer.Default(100,1000,3);
    }
    
}
