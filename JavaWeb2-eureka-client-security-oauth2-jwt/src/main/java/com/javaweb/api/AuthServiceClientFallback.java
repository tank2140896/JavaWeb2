package com.javaweb.api;

import org.springframework.stereotype.Component;

import com.javaweb.web.eo.Jwt;

@Component
public class AuthServiceClientFallback implements AuthServiceClient {

    public Jwt getToken(String authorization, String type, String username, String password) {
        return null;
    }
        

}
