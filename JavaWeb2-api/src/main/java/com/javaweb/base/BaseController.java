package com.javaweb.base;

import com.javaweb.controller.LoginController;
import com.javaweb.dataobject.eo.TokenData;

public class BaseController extends Base {
	
	/**
	httpServletRequest.getRequestURI()             /javaweb/app/html/home.html
	httpServletRequest.getRequestURL().toString()  http://localhost:8080/javaweb/app/html/home.html 
	httpServletRequest.getServletPath()            /app/html/home.html
	*/
	
	public boolean check(String userId,String token,String servletPath){
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
