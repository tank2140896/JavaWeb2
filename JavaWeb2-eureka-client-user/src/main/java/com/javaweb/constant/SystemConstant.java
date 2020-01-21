package com.javaweb.constant;

import com.javaweb.web.po.User;

public class SystemConstant {
	
	public static final String PROJECT_NAME = "JavaWeb";
	
	public static final String PROJECT_VERSION = "1.0";

	public static final String PROJECT_GITHUB_URL = "https://github.com/tank2140896/JavaWeb2";

	public static final String DEFAULT_SECURITY_KEY = "the key of JavaWeb";
	
	public static final String BASE_PACKAGE = "com.javaweb";
	
	public static final String DEFAULT_LOG_POINT_CUT = "execution(* com.javaweb.web.controller..*.*(..))";

	public static final String DEFAULT_DATA_SOURCE_POINT_CUT = "execution(* com.javaweb.web.dao..*.*(..))";
	
	public static final String DATA_SOURCE_PACKAGE_NAME_1 = "ds1";
	
	public static final String DATA_SOURCE_PACKAGE_NAME_2 = "ds2";
	
	public static final String DATA_SOURCE_KEY_1 = "mysql_d1";
	
	public static final String DATA_SOURCE_KEY_2 = "mysql_d2";
	
	public static final String URL_WEB_INTERCEPTOR_PATTERN = "/web/**";
	
	public static final String URL_APP_INTERCEPTOR_PATTERN = "/app/**";

	public static final String URL_LOGIN_WEB_PERMISSION = "/web/loginAccess";
	
	public static final Long SYSTEM_DEFAULT_SESSION_OUT = 15L;
	
	public static final Long SYSTEM_DEFAULT_KAPTCHA_TIME_OUT = 5L;

	public static final Long SYSTEM_DEFAULT_JWT_TIME_OUT = 5L;
	
	public static final String ADMIN = "admin123456";
	
	public static final String HEAD_USERID = "userId";
	
	public static final String HEAD_TOKEN = "token";
	
	public static final String HEAD_TYPE = "type";

	public static final String REDIS_TEMPLATE_1 = "redisTemplate1";
	
	public static final String SYSTEM_DEFAULT_USER_ID = ADMIN;
	
	public static final String SYSTEM_DEFAULT_USER_NAME = ADMIN;
	
	public static final String SYSTEM_DEFAULT_USER_PASSWORD = ADMIN;
	
	public static final String SYSTEM_DEFAULT_USER_PERSON_NAME = "管理员";
	
	public static final Integer SYSTEM_DEFAULT_USER_LEVEL = 0;
	
	public static final Integer SYSTEM_DEFAULT_USER_STATUS = 0;
	
	public static final User SYSTEM_DEFAULT_USER = new User();
	static{
		SYSTEM_DEFAULT_USER.setUserId(SYSTEM_DEFAULT_USER_ID);
		SYSTEM_DEFAULT_USER.setUserName(SYSTEM_DEFAULT_USER_NAME);
		SYSTEM_DEFAULT_USER.setPersonName(SYSTEM_DEFAULT_USER_PERSON_NAME);
		SYSTEM_DEFAULT_USER.setLevel(SYSTEM_DEFAULT_USER_LEVEL);
		SYSTEM_DEFAULT_USER.setStatus(SYSTEM_DEFAULT_USER_STATUS);
	}
	
}
