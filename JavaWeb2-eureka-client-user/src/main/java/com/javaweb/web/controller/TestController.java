package com.javaweb.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.alibaba.fastjson.JSONArray;
import com.javaweb.base.BaseController;
import com.javaweb.constant.CommonConstant;
import com.javaweb.web.docking.LogServerApi;
import com.javaweb.web.docking.LogServerApiEntity;

@RestController
public class TestController extends BaseController {
	
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
    @Autowired
    private LogServerApi logServerApi;
    
    //获取所有的url
    @GetMapping("/apiUrlTest")
    public String apiUrlTest() {
    	List<String> urlList = new ArrayList<>();
    	Map<RequestMappingInfo,HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
		Set<RequestMappingInfo> set = map.keySet();
		Iterator<RequestMappingInfo> iterator = set.iterator();
		while(iterator.hasNext()){
			 RequestMappingInfo requestMappingInfo = iterator.next();
			 urlList.add(requestMappingInfo.getPatternsCondition().toString().replace("[", "").replace("]", ""));
		}
		urlList = urlList.stream().distinct().collect(Collectors.toList());
        return JSONArray.toJSONString(urlList);
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
