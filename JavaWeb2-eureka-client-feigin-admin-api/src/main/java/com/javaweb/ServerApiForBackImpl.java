package com.javaweb;

import org.springframework.stereotype.Component;

@Component
public class ServerApiForBackImpl implements ServerApi {
        
        public String test1() {
            return "这是降级处理";
        }

}
