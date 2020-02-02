package com.javaweb.config.webmvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.javaweb.constant.SystemConstant;
import com.javaweb.interceptor.AppPermissionInterceptor;
import com.javaweb.interceptor.WebPermissionInterceptor;
import com.javaweb.resolver.TokenDataHandlerMethodArgumentResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Value("${test.model.open}")
	private boolean testModelOpen;
	
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    public void addInterceptors(InterceptorRegistry registry) {
    	if(!testModelOpen) {
    		registry.addInterceptor(new WebPermissionInterceptor()).addPathPatterns(SystemConstant.URL_WEB_INTERCEPTOR_PATTERN);//拦截/web下面的所有请求
    		registry.addInterceptor(new AppPermissionInterceptor()).addPathPatterns(SystemConstant.URL_APP_INTERCEPTOR_PATTERN);//拦截/app下面的所有请求
    	}
    }
    
    //更加细化的可以在Controller中写为:@CrossOrigin(origins="http://192.168.1.100:8080",maxAge=3600)
    public void addCorsMappings(CorsRegistry corsRegistry) {
    	corsRegistry.addMapping("/**")
        	        .allowedOrigins("*")
        	        .allowedHeaders("*")
        	        .allowedMethods("*")
        	        .allowCredentials(true);
    }

    //这里主要是添加了swagger访问例外(http://IP:端口/项目名称/swagger-ui.html)
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	//registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
    	//registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new TokenDataHandlerMethodArgumentResolver());
	}
    
}