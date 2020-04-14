package com.javaweb.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.constant.CommonConstant;
import com.javaweb.web.docking.LogServerApi;
import com.javaweb.web.docking.LogServerApiEntity;

@RestController
public class TestController extends BaseController {
	
    @Autowired
    private LogServerApi logServerApi;
    
    //测试
    @GetMapping("/test")
    public String test() {
		return "success";
    }
    
    //测试eureka微服务间的通信
    @GetMapping("/eurekaTest")
    public String eurekaTest(HttpServletRequest request) {
        LogServerApiEntity logServerApiEntity = new LogServerApiEntity();
        logServerApiEntity.setUsername("abc");
        logServerApiEntity.setPassword("123");
        String part1 = logServerApi.test(logServerApiEntity);
        String part2 = discoveryClient.getInstances("eureka-client-log").toString();
        String part3 = eurekaClient.getInstancesByVipAddress("eureka-client-log",false).toString();
        String part4 = String.valueOf(request.getServerPort());
        return String.join(CommonConstant.COMMA,part1,part2,part3,part4);
    }
    
}
