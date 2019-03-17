package com.javaweb.interceptor.except;

import javax.servlet.http.HttpServletRequest;

public class ExceptUrlImpl implements ExceptUrl {

	public boolean pass(HttpServletRequest request) {
		return true;
	}

	public boolean active() {
		return false;
	}

}
