package com.javaweb.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaweb.config.FeignConfig;
import com.javaweb.web.eo.Jwt;

@FeignClient(value="eureka-client-security-oauth2-jwt",fallback=AuthServiceClientFallback.class,configuration=FeignConfig.class)
public interface AuthServiceClient {
        
        @PostMapping("/oauth/token")
        public Jwt getToken(@RequestHeader(value="Authorization") String authorization,
                            @RequestParam("grant_type") String type,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password);

}
