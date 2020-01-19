package com.javaweb.web.docking;

import org.springframework.stereotype.Component;

@Component
public class LogServerApiFallbackImpl implements LogServerApi {

    public String test(LogServerApiEntity logServerApiEntity) {
        return "test from user to log error";
    }

}
