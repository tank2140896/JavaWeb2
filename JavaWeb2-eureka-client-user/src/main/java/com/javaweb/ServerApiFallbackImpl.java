package com.javaweb;

import org.springframework.stereotype.Component;

@Component
public class ServerApiFallbackImpl implements ServerApi {
        
        public String testServerApi() {
            return "这是降级处理";
        }

}
