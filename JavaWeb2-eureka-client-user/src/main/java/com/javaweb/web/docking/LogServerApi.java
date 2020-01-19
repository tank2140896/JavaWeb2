package com.javaweb.web.docking;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="eureka-client-feigin-log",fallback=LogServerApiFallbackImpl.class,configuration=LogServerFeignConfig.class)
public interface LogServerApi {
    
    @RequestMapping(method=RequestMethod.POST,value="/test",consumes="application/json")
    public String test(LogServerApiEntity logServerApiEntity);

}
