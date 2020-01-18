package com.javaweb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="eureka-client-feigin-log",fallback=ServerApiFallbackImpl.class,configuration=FeignConfig.class)
public interface ServerApi {
        
        @GetMapping("/test2")
        String testServerApi();

}
