package com.javaweb.conf.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class ShiroSession {
	
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	public static Session getSession(){
		return getSubject().getSession();
	}
	
	public static Object getAttribute(Object key){
		return getSession().getAttribute(key);
	}
	
	public static void setAttribute(Object key,Object value){
		getSession().setAttribute(key, value);
	}
	
}
