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
	request.getRequestDispatcher("/test").forward(request,response);//服务端跳转
	response.sendRedirect(basePath+"/test");//页面端跳转
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
			return false;
		}
		if(!type.matches("[1-9]")){//1：web；2：安卓；3：IOS
			request.getRequestDispatcher("/requestParameterLost").forward(request,response);
			return false;
		}
		TokenData tokenData = (TokenData)redisTemplate.opsForValue().get(userId+","+type);//(TokenData)request.getSession().getAttribute(userId);
		if(tokenData==null){
			request.getRequestDispatcher("/invalidRequest").forward(request,response);
			return false;
		}
		if(!tokenData.getUser().getUserId().equals(userId)){
			request.getRequestDispatcher("/requestParameterError").forward(request,response);
			return false;
		}
		if(!tokenData.getToken().equals(token)){
			request.getRequestDispatcher("/requestParameterError").forward(request,response);
			return false;
		}
		if(!tokenData.getType().equals(type)){
			request.getRequestDispatcher("/requestParameterError").forward(request,response);
			return false;
		}
		if(servletPath.startsWith(SystemConstant.URL_LOGIN_PERMISSION)){//该路径下只要登录即可访问，不需要权限
			redisTemplate.opsForValue().set(userId,tokenData,SystemConstant.SYSTEM_DEFAULT_SESSION_OUT,TimeUnit.MINUTES);
			return true;
		}
		long count = tokenData.getAuthOperateList().stream().filter(i->{
			String splitApiUrl[] = i.getApiUrl().split(",");//某一操作可能会调用多个操作，多个操作约定用逗号分开
			for(String str:splitApiUrl){
				if(servletPath.startsWith(str)){
					return true;
				}
			}
			return false; 
		}).count();
		if(count<=0){
			request.getRequestDispatcher("/noAuthory").forward(request,response);
			return false;
		}else{
			redisTemplate.opsForValue().set(userId+","+type,tokenData,SystemConstant.SYSTEM_DEFAULT_SESSION_OUT,TimeUnit.MINUTES);
			return true;
		}
	}
	
}
