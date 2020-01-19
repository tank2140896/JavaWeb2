package com.javaweb;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    public String test(@RequestBody LogServerApiEntity logServerApiEntity) {
        return logServerApiEntity.toString();
    }
    
}
