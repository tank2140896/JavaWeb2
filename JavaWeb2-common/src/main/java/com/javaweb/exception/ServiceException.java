package com.javaweb.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3963482452340751481L;
	
	public ServiceException(){
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

}
