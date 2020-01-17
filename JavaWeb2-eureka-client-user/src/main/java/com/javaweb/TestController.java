package com.javaweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @Autowired
    private ServerApi serverApi;

    @GetMapping("/test")
    public String test() {
        return "test user success";
    }
    
    @GetMapping(value="/test2")
    public String testServerApi() {
        return serverApi.testServerApi();
    }
    
}
