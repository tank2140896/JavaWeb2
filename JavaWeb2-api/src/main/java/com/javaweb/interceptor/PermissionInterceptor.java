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
		//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();  
		String userId = request.getHeader(SystemConstant.HEAD_USERID);
		String token = request.getHeader(SystemConstant.HEAD_TOKEN);
		String type = request.getHeader(SystemConstant.HEAD_TYPE);
		String servletPath = request.getServletPath();
		if(userId==null||token==null||type==null){
			request.getRequestDispatcher("/requestParameterLost").forward(request,response);
			//response.sendRedirect(basePath+"/requestParameterLost");
			return false;
		}
		if(!type.matches("[1-9]")){//1：web；2：安卓；3：IOS
			request.getRequestDispatcher("/requestParameterLost").forward(request,response);
			//response.sendRedirect(basePath+"/requestParameterLost");
			return false;
		}
		//(TokenData)request.getSession().getAttribute(userId);
		TokenData tokenData = (TokenData)redisTemplate.opsForValue().get(userId+","+type);
		if(tokenData==null){
			request.getRequestDispatcher("/invalidRequest").forward(request,response);
			//response.sendRedirect(basePath+"/invalidRequest");
			return false;
		}
		if(!tokenData.getUser().getUserId().equals(userId)){
			request.getRequestDispatcher("/requestParameterError").forward(request,response);
			//response.sendRedirect(basePath+"/requestParameterError");
			return false;
		}
		if(!tokenData.getToken().equals(token)){
			request.getRequestDispatcher("/requestParameterError").forward(request,response);
			//response.sendRedirect(basePath+"/requestParameterError");
			return false;
		}
		if(!tokenData.getType().equals(type)){
			request.getRequestDispatcher("/requestParameterError").forward(request,response);
			//response.sendRedirect(basePath+"/requestParameterError");
			return false;
		}
		if(servletPath.startsWith("/web/loggedIn")){//该路径下只要登录即可访问，不需要权限
			redisTemplate.opsForValue().set(userId,tokenData,SystemConstant.SYSTEM_DEFAULT_SESSION_OUT,TimeUnit.MINUTES);
			return true;
		}
		long count = tokenData.getAuthOperateList().stream().filter(i->{
			String splitApiUrl[] = i.getApiUrl().split(",");
			for(String str:splitApiUrl){
				if(servletPath.startsWith(str)){
					return true;
				}
			}
			return false; 
		}).count();
		if(count<=0){
			request.getRequestDispatcher("/noAuthory").forward(request,response);
			//response.sendRedirect(basePath+"/noAuthory");
			return false;
		}else{
			redisTemplate.opsForValue().set(userId+","+type,tokenData,SystemConstant.SYSTEM_DEFAULT_SESSION_OUT,TimeUnit.MINUTES);
			return true;
		}
	}
	
}
