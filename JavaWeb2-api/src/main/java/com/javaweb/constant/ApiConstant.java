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
	
	public static final String MODULE_PREFIX = "/web/pc/sys/module";
	public static final String MODULE_ADD = "/add";//新增模块接口
	public static final String MODULE_LIST = "/list";//查询模块接口
	public static final String MODULE_MODIFY = "/modify";//修改模块接口
	public static final String MODULE_DETAIL = "/detail/{moduleId}";//模块详情接口
	public static final String MODULE_DELETE = "/delete/{moduleId}";//删除模块接口
	
	public static final String ROLE_PREFIX = "/web/pc/sys/role";
	public static final String ROLE_ADD = "/add";//新增角色接口
	public static final String ROLE_LIST = "/list";//查询角色接口
	public static final String ROLE_MODIFY = "/modify";//修改角色接口
	public static final String ROLE_DETAIL = "/detail/{roleId}";//角色详情接口
	public static final String ROLE_DELETE = "/delete/{roleId}";//删除角色接口
	public static final String ROLE_MODULE_ASSIGNMENT = "/roleModuleAssignment/{roleId}";//角色模块分配接口
	public static final String ROLE_MODULE_INFO = "/roleModuleInfo/{roleId}";//角色模块信息接口
	
	public static final String USER_PREFIX = "/web/pc/sys/user";
	public static final String USER_ADD = "/add";//新增角色接口
	public static final String USER_LIST = "/list";//查询角色接口
	public static final String USER_MODIFY = "/modify";//修改角色接口
	public static final String USER_DETAIL = "/detail/{userId}";//角色详情接口
	public static final String USER_DELETE = "/delete/{userId}";//删除角色接口
	public static final String USER_ROLE_INFO = "/userRoleInfo/{userId}";//角色模块分配接口
	public static final String USER_ROLE_ASSIGNMENT = "/userRoleAssignment/{userId}";//角色模块信息接口
	public static final String USER_MODULE_INFO = "/userModuleInfo/{userId}";//角色模块信息接口
	public static final String USER_MODULE_ASSIGNMENT = "/userModuleAssignment/{userId}";//角色模块信息接口
	
}
