package com.javaweb.constant;

import com.javaweb.web.po.User;

public class SystemConstant {
	
	public static final Long SYSTEM_DEFAULT_SESSION_OUT = 15L;
	
	public static final String HEAD_USERID = "userId";
	
	public static final String HEAD_TOKEN = "token";
	
	public static final String SYSTEM_DEFAULT_USER_ID = "admin2018";
	
	public static final String SYSTEM_DEFAULT_USER_NAME = "admin2018";
	
	public static final String SYSTEM_DEFAULT_USER_PASSWORD = "admin2018";
	
	public static final String SYSTEM_DEFAULT_USER_PERSON_NAME = "超级管理员";
	
	public static final User SYSTEM_DEFAULT_USER = new User();
	static{
		SYSTEM_DEFAULT_USER.setUserId(SYSTEM_DEFAULT_USER_ID);
		SYSTEM_DEFAULT_USER.setUserName(SYSTEM_DEFAULT_USER_NAME);
		SYSTEM_DEFAULT_USER.setPassword(SYSTEM_DEFAULT_USER_PASSWORD);
		SYSTEM_DEFAULT_USER.setPersonName(SYSTEM_DEFAULT_USER_PERSON_NAME);
		SYSTEM_DEFAULT_USER.setLevel(1);
		SYSTEM_DEFAULT_USER.setStatus(0);
	}
	
	public static final int VALIDATE_ERROR = 10;
	
	public static final int LOGIN_FAIL = 11;
	
	public static final int SUCCESS = 200;
	
	public static final int NO_AUTHORY = 401;
	
	public static final int NOT_FOUND = 404;
	
	public static final int INTERNAL_ERROR = 500;
	
	public static final int INVALID_REQUEST = 601;
	
	public static final int REQUEST_PARAMETER_ERROR = 602;
	
	public static final int REQUEST_PARAMETER_LOST = 603;
	
}
