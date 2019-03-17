package com.javaweb.interceptor.except;

import javax.servlet.http.HttpServletRequest;

public interface ExceptUrl {
	
	public boolean pass(HttpServletRequest request);
	
	public boolean active();
	
}
