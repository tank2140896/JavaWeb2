package com.javaweb.constant;

import com.javaweb.web.po.User;

public class SystemConstant {
	
	public static final String BASE_PACKAGE = "com.javaweb";
	
	public static final String DEFAULT_DATA_SOURCE_POINT_CUT = "execution(* com.javaweb.web.dao..*.*(..))";
	
	public static final String HEAD_TYPE_PATTERN = "[1-9]";
	
	public static final String URL_INTERCEPTOR_PATTERN = "/web/**";
	
	public static final String URL_LOGIN_PERMISSION = "/web/loggedIn";
	
	public static final Long SYSTEM_DEFAULT_SESSION_OUT = 15L;
	
	public static final Long SYSTEM_DEFAULT_KAPTCHA_TIME_OUT = 5L;
	
	public static final String ADMIN = "admin";
	
	public static final String HEAD_USERID = "userId";
	
	public static final String HEAD_TOKEN = "token";
	
	public static final String HEAD_TYPE = "type";

	public static final String REDIS_TEMPLATE = "redisTemplate";
	
	public static final String SYSTEM_DEFAULT_USER_ID = ADMIN;
	
	public static final String SYSTEM_DEFAULT_USER_NAME = ADMIN;
	
	public static final String SYSTEM_DEFAULT_USER_PASSWORD = ADMIN;
	
	public static final String SYSTEM_DEFAULT_USER_PERSON_NAME = "超级管理员";
	
	public static final User SYSTEM_DEFAULT_USER = new User();
	static{
		SYSTEM_DEFAULT_USER.setUserId(SYSTEM_DEFAULT_USER_ID);
		SYSTEM_DEFAULT_USER.setUserName(SYSTEM_DEFAULT_USER_NAME);
		SYSTEM_DEFAULT_USER.setPassword(SYSTEM_DEFAULT_USER_PASSWORD);
		SYSTEM_DEFAULT_USER.setPersonName(SYSTEM_DEFAULT_USER_PERSON_NAME);
		SYSTEM_DEFAULT_USER.setLevel(0);
		SYSTEM_DEFAULT_USER.setStatus(0);
	}
	
	public static final int SUCCESS = 200;
	
	public static final int NO_AUTHORY = 401;
	
	public static final int NOT_FOUND = 404;
	
	public static final int INTERNAL_ERROR = 500;
	
	public static final int LOGIN_FAIL = 600;
	
	public static final int VALIDATE_ERROR = 601;
	
	public static final int INVALID_REQUEST = 602;
	
	public static final int REQUEST_PARAMETER_ERROR = 603;
	
	public static final int REQUEST_PARAMETER_LOST = 604;
	
}
