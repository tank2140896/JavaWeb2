package com.javaweb.exception;

import com.javaweb.base.BaseException;

public class TokenExpiredException extends BaseException {
	
	private static final long serialVersionUID = -5089043915083888174L;
	
	public TokenExpiredException(){
		super();
	}

	public TokenExpiredException(String message) {
		super(message);
	}

}
