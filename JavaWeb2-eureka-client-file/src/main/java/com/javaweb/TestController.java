package com.javaweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
