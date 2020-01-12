package com.javaweb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="eureka-client-user",fallback=ServerApiForBackImpl.class)
public interface ServerApi {
        
        @GetMapping("/test")
        String test1();

}
