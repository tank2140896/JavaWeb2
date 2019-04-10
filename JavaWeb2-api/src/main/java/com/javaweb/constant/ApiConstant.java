package com.javaweb.constant;

public class ApiConstant {
	
	public static final String ALL_OPEN_PREFIX = "";
	public static final String LOGIN = "/login";//登录接口
	public static final String REQUEST_PARAMETER_LOST = "/requestParameterLost";//请求参数缺失接口
	public static final String INVALID_REQUEST = "/invalidRequest";//请求失效接口
	public static final String REQUEST_PARAMETER_ERROR = "/requestParameterError";//请求参数错误接口
	public static final String NO_AUTHORY = "/noAuthory";//没有权限接口
	public static final String NOT_FOUND = "/notFound";//请求接口不存在接口
	public static final String INTERNAL_ERROR = "/internalError";//系统异常接口
	
	public static final String LOGIN_ACCESS_PREFIX = "/web/pc/loginAccess";
	public static final String LOGIN_OUT = "/logout";//用户登出接口
	public static final String GET_REDIS_TOKEN_DATA = "/getRedisTokenData";//获取redis中的token信息接口
	
}
