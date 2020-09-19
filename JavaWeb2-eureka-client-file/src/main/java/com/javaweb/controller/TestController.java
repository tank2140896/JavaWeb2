package com.javaweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.eo.LogServerApiEntity;

@RestController
public class TestController {
	
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/test2")
    public String test2(@RequestBody LogServerApiEntity logServerApiEntity) {
        return logServerApiEntity.toString();
    }
    
}
