package com.javaweb.config.requestRewrite;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

	@Bean
    public FilterRegistrationBean<WebServletRequestReplacedFilter> httpServletRequestReplacedRegistration() {
        FilterRegistrationBean<WebServletRequestReplacedFilter> registration = new FilterRegistrationBean<WebServletRequestReplacedFilter>();
        registration.setFilter(new WebServletRequestReplacedFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName","paramValue");
        registration.setName("httpServletRequestReplacedFilter");
        registration.setOrder(1);
        return registration;
    }
	
}
