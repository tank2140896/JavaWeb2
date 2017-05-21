package com.javaweb.conf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.base.BaseController;
import com.javaweb.constant.SystemConstant;
import com.javaweb.controller.LoginController;
import com.javaweb.dataobject.eo.TokenData;

@WebFilter(urlPatterns="/*",filterName="authFilter")
public class AuthFilter extends BaseController implements Filter {
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = ((HttpServletRequest)request);
		HttpServletResponse httpServletResponse = ((HttpServletResponse)response);
		/**
		httpServletRequest.getRequestURI()             /javaweb/app/html/home.html
		httpServletRequest.getRequestURL().toString()  http://localhost:8080/javaweb/app/html/home.html 
		httpServletRequest.getServletPath()            /app/html/home.html
		*/ 
		String servletPath = httpServletRequest.getServletPath();
		if(servletPath.matches(SystemConstant.NO_LOGIN_URL_REGEX)){
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}else{
			String userId = httpServletRequest.getHeader(SystemConstant.HEAD_USERID);
			String token = httpServletRequest.getHeader(SystemConstant.HEAD_TOKEN);
			if(check(userId, token, servletPath)){
				filterChain.doFilter(httpServletRequest, httpServletResponse);
			}else{
				httpServletResponse.sendRedirect("/unauthorized");
			}
		}
	}
	
	private boolean check(String userId,String token,String servletPath){
		try {
			if(userId==null||token==null){
				return false;
			}
			TokenData tokenData = LoginController.getCache(userId,valueOperations);
			if(tokenData==null){
				return false;
			}
			if(!tokenData.getUser().getUserId().equals(userId)){
				return false;
			}
			if(!tokenData.getToken().equals(token)){
				return false;
			}
			long count = tokenData.getAuthOperateList().stream().filter(i->i.getApiUrl().equals(servletPath)).count();
			if(count<=0){
				return false;
			}
			LoginController.setCache(tokenData, valueOperations);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
