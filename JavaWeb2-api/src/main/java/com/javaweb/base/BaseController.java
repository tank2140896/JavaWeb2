package com.javaweb.base;

import javax.servlet.http.HttpServletRequest;

public class BaseController extends Base {
	
	public Object getSessionAttribute(HttpServletRequest request,String key){
		return request.getSession().getAttribute(key);
	}
	
	public void setSessionAttribute(HttpServletRequest request,String key,Object object){
		request.getSession().setAttribute(key, object);
	}

}
