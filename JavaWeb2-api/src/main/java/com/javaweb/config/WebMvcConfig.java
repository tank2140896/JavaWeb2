package com.javaweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.javaweb.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/web/**");
    }
    
    //更加细化的可以在Controller中写为:@CrossOrigin(origins="http://192.168.1.100:8080",maxAge=3600)
    public void addCorsMappings(CorsRegistry corsRegistry) {
    	corsRegistry.addMapping("/**")
        	        .allowedOrigins("*")
        	        .allowedHeaders("*")
        	        .allowedMethods("*")
        	        .allowCredentials(true);
    }
    
}