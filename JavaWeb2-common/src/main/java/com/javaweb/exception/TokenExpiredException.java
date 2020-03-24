package com.javaweb.exception;

public class TokenExpiredException extends Exception {
	
	private static final long serialVersionUID = -5089043915083888174L;
	
	public TokenExpiredException(){
		super();
	}

	public TokenExpiredException(String message) {
		super(message);
	}

}
