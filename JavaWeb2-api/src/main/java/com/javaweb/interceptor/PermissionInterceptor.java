package com.javaweb.interceptor;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.javaweb.constant.SystemConstant;
import com.javaweb.web.eo.TokenData;

@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter {
	
	/**
	httpServletRequest.getRequestURI()             /javaweb/app/html/home.html
	httpServletRequest.getRequestURL().toString()  http://localhost:8080/javaweb/app/html/home.html 
	httpServletRequest.getServletPath()            /app/html/home.html
	*/
	@SuppressWarnings({"rawtypes","unchecked"})
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); 
		RedisTemplate redisTemplate = (RedisTemplate) factory.getBean("redisTemplate"); 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();  
		String userId = request.getHeader(SystemConstant.HEAD_USERID);
		String token = request.getHeader(SystemConstant.HEAD_TOKEN);
		String servletPath = request.getServletPath();
		if(servletPath.startsWith("/web")){
			if(userId==null||token==null){
				response.sendRedirect(basePath+"/requestParameterLost");
				return false;
			}
			TokenData tokenData = (TokenData)redisTemplate.opsForValue().get(userId);
			if(tokenData==null){
				response.sendRedirect(basePath+"/invalidRequest");
				return false;
			}
			if(!tokenData.getUser().getUserId().equals(userId)){
				response.sendRedirect(basePath+"/requestParameterError");
				return false;
			}
			if(!tokenData.getToken().equals(token)){
				response.sendRedirect(basePath+"/requestParameterError");
				return false;
			}
			if(servletPath.startsWith("/web/logged")){
				return true;
			}
			long count = tokenData.getAuthOperateList().stream().filter(i->i.getApiUrl().equals(servletPath)).count();
			if(count<=0){
				response.sendRedirect(basePath+"/noAuthory");
				return false;
			}else{
				redisTemplate.opsForValue().set(userId,tokenData,SystemConstant.SYSTEM_DEFAULT_SESSION_OUT,TimeUnit.MINUTES);
				return true;
			}
		}else{
			return true;
		}
	}
	
}
