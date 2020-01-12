package com.javaweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerApi {
    
    @Autowired
    private ServerApi serverApi;

    @GetMapping(value="/yhfw/test")
    public String test1() {
        return serverApi.test1();
    }

}
