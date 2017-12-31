package com.javaweb.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	//拦截器
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionHandlerInterceptor()).addPathPatterns("/**");
	}

	//跨域访问配置
	public void addCorsMappings(CorsRegistry registry) {
		//registry.addMapping("/*")
		//  	    .allowedOrigins("*")//"http://127.0.0.1:8888"
		//  	    .exposedHeaders("x-total-count","x-auth-token")
		//  	    .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE")
		//  	    .allowCredentials(true);
		registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE").allowCredentials(true);
	}

	//格式化
	public void addFormatters(FormatterRegistry registry) {
		
	}

	//URI到视图的映射
	public void addViewControllers(ViewControllerRegistry registry) {
		
	}

}

class SessionHandlerInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
}
