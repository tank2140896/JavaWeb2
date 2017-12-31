package com.javaweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class HandlerExceptionInterceptor implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception e) {
		System.out.println(e.getMessage());
		return new ModelAndView("/notFound");
	}

}
