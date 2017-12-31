package com.javaweb.constant;

import com.javaweb.web.po.User;

public class SystemConstant {
	
	public static final String SYSTEM_DEFAULT_USER_ID = "admin";
	
	public static final String SYSTEM_DEFAULT_USER_NAME = "admin";
	
	public static final String SYSTEM_DEFAULT_USER_PASSWORD = "admin";
	
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
	
	public static final int VALIDATE_ERROR_CODE = 10;
	
	public static final int LOGIN_FAIL_CODE = 11;
	
	public static final int SUCCESS_CODE = 200;
	
	public static final int NO_AUTHORY_CODE = 401;
	
	public static final int NOT_FOUND_CODE = 404;
	
	public static final int INTERNAL_ERROR_CODE = 500;
	
}
