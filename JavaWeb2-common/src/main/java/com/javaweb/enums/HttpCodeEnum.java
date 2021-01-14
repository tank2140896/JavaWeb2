package com.javaweb.enums;

public enum HttpCodeEnum {
	
	/** 基本与HTTP状态码匹配 start */
	SUCCESS(200),
	INVALID_REQUEST(400),
	NO_AUTHORY(401),
	FORBIDDEN(403),
	NOT_FOUND(404),
	INTERNAL_ERROR(500),
	/** 基本与HTTP状态码匹配 end */
	/** 自定义的HTTP状态码 start */
	LOGIN_FAIL(600),
	VALIDATE_ERROR(601),
	REQUEST_PARAMETER_ERROR(602),
	REQUEST_PARAMETER_LOST(603),
	REQUEST_LIMIT(604);
	/** 自定义的HTTP状态码 end */
	
	private int code;
	
	HttpCodeEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
}
