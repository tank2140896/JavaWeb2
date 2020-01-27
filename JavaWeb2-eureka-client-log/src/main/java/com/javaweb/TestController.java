package com.javaweb;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.constant.CommonConstant;

@RestController
public class TestController {

    @PostMapping("/test")
    public String test(@RequestBody LogServerApiEntity logServerApiEntity) {
    	System.out.println(CommonConstant.ZERO_STRING_VALUE);
        return logServerApiEntity.toString();
    }
    
}
