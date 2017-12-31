package com.javaweb.base;

public class BaseException extends Exception {
	
	private static final long serialVersionUID = -5124760637419597955L;

	public BaseException(){
		super();
	}
	
	public BaseException(String message){
		super(message);
	}
	
	public BaseException(Throwable cause) {
		super(cause);
	}
	
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

}
