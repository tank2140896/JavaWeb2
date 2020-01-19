package com.javaweb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseResponseResult;
import com.javaweb.web.eo.UserLoginRequest;

@RestController
public class UserServerLoginController {
    
    @Autowired
    private UserServerApi userServerApi;
    
    @PostMapping("/login")
    public BaseResponseResult webLogin(@RequestBody UserLoginRequest userLoginRequest) {
        return userServerApi.webLogin(userLoginRequest);
    }

}
