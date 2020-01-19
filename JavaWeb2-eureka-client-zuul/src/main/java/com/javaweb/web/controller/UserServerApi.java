package com.javaweb.web.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaweb.base.BaseResponseResult;
import com.javaweb.web.eo.UserLoginRequest;

@FeignClient(value="eureka-client-feigin-user",fallback=UserServerApiFallbackImpl.class,configuration=UserServerFeignConfig.class)
public interface UserServerApi {
    
    @RequestMapping(method=RequestMethod.POST,value="/webLogin",consumes="application/json")
    public BaseResponseResult webLogin(UserLoginRequest userLoginRequest);

}
