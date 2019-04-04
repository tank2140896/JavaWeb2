package com.javaweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.javaweb.constant.SystemConstant;
import com.javaweb.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionInterceptor()).addPathPatterns(SystemConstant.URL_INTERCEPTOR_PATTERN);//拦截/web下面的所有请求
    }
    
    //更加细化的可以在Controller中写为:@CrossOrigin(origins="http://192.168.1.100:8080",maxAge=3600)
    public void addCorsMappings(CorsRegistry corsRegistry) {
    	corsRegistry.addMapping("/**")
        	        .allowedOrigins("*")
        	        .allowedHeaders("*")
        	        .allowedMethods("*")
        	        .allowCredentials(true);
    }

    //这里主要是添加了swagger访问例外
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
}