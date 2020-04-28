package com.javaweb.constant;

public class ApiConstant {
	
	public static final String ALL_OPEN_PREFIX = "";
	public static final String GET_SERVE_TIME = "/getServeTime";//获取服务器时间接口
	public static final String WEB_LOGIN = "/webLogin";//登录接口
	public static final String REQUEST_PARAMETER_LOST = "/requestParameterLost";//请求参数缺失接口
	public static final String INVALID_REQUEST = "/invalidRequest";//请求失效接口
	public static final String REQUEST_PARAMETER_ERROR = "/requestParameterError";//请求参数错误接口
	public static final String NO_AUTHORY = "/noAuthory";//没有权限接口
	public static final String NOT_FOUND = "/notFound";//请求接口不存在接口
	public static final String INTERNAL_ERROR = "/internalError";//系统异常接口
	
	public static final String WEB_LOGIN_ACCESS_PREFIX = "/web/loginAccess";
	public static final String LOGIN_OUT = "/logout";//用户登出接口
	public static final String GET_REDIS_TOKEN_DATA = "/getRedisTokenData";//获取redis中的token信息接口
	public static final String GET_DICTIONARY = "/getDictionary";//获得字典信息接口
	
	public static final String WEB_USER_PREFIX = "/web/sys/user";
	public static final String USER_ADD = "/add";//新增用户接口
	public static final String USER_LIST = "/list";//查询用户接口
	public static final String USER_MODIFY = "/modify";//修改用户接口
	public static final String USER_DETAIL = "/detail/{userId}";//用户详情接口
	public static final String USER_DELETE = "/delete/{userId}";//删除用户接口
	public static final String USER_ROLE_INFO = "/userRoleInfo/{userId}";//用户角色信息接口
	public static final String USER_ROLE_INFO2 = "/userRoleInfo";//用户角色信息接口（所有角色信息）
	public static final String USER_ROLE_ASSIGNMENT = "/userRoleAssignment/{userId}";//用户角色分配接口
	public static final String USER_MODULE_INFO = "/userModuleInfo/{userId}";//用户模块信息接口
	public static final String USER_MODULE_INFO2 = "/userModuleInfo";//用户模块信息接口（所有模块信息）
	public static final String USER_MODULE_ASSIGNMENT = "/userModuleAssignment/{userId}";//用户模块分配接口
	
	public static final String WEB_ROLE_PREFIX = "/web/sys/role";
	public static final String ROLE_ADD = "/add";//新增角色接口
	public static final String ROLE_LIST = "/list";//查询角色接口
	public static final String ROLE_MODIFY = "/modify";//修改角色接口
	public static final String ROLE_DETAIL = "/detail/{roleId}";//角色详情接口
	public static final String ROLE_DELETE = "/delete/{roleId}";//删除角色接口
	public static final String ROLE_MODULE_ASSIGNMENT = "/roleModuleAssignment/{roleId}";//角色模块分配接口
	public static final String ROLE_MODULE_INFO = "/roleModuleInfo/{roleId}";//角色模块信息接口
	public static final String ROLE_MODULE_INFO2 = "/roleModuleInfo";//角色模块信息接口（所有模块信息）
	
	public static final String WEB_MODULE_PREFIX = "/web/sys/module";
	public static final String MODULE_GET_MODULE_ID_AND_NAME_LIST = "/getModuleIdAndNameList/{moduleType}";//获取模块ID和模块名称列表接口
	public static final String MODULE_ADD = "/add";//新增模块接口
	public static final String MODULE_LIST = "/list";//查询模块接口
	public static final String MODULE_MODIFY = "/modify";//修改模块接口
	public static final String MODULE_DETAIL = "/detail/{moduleId}";//模块详情接口
	public static final String MODULE_DELETE = "/delete/{moduleId}";//删除模块接口
	
	public static final String WEB_DICTIONARY_PREFIX = "/web/sys/dictionary";
	public static final String DICTIONARY_ADD = "/add";//新增字典接口
	public static final String DICTIONARY_LIST = "/list";//查询字典接口
	public static final String DICTIONARY_MODIFY = "/modify";//修改字典接口
	public static final String DICTIONARY_DETAIL = "/detail/{dictionaryId}";//字典详情接口
	public static final String DICTIONARY_DELETE = "/delete/{dictionaryId}";//删除字典接口
	
	public static final String WEB_OPERATIONLOG_PREFIX = "/web/sys/operationLog";
	public static final String OPERATIONLOG_LIST = "/list";//查询操作日志接口
	
	public static final String WEB_SCHEDULE_PREFIX = "/web/sys/schedule";
	public static final String SCHEDULE_LIST = "/list";//日程列表接口
	public static final String SCHEDULE_ADD = "/add";//保存日程接口
	
	public static final String WEB_INTERFACES_PREFIX = "/web/sys/interfaces";
	public static final String INTERFACES_LIST = "/list";//接口列表接口
	public static final String INTERFACES_MODIFY = "/modify";//修改接口接口
	public static final String INTERFACES_DETAIL = "/detail/{interfacesId}";//接口详情接口
	public static final String INTERFACES_USER_ROLE_DATA_PERMISSION = "/userRoleDataPermission";//用户角色数据权限接口
	public static final String INTERFACES_DATA_PERMISSION_ASSIGNMENT = "/dataPermissionAssignment/{interfacesId}";//数据权限分配接口
	
	public static final String WEB_ONLINE_USER_PREFIX = "/web/sys/onlineUser";
	public static final String ONLINE_USER_LIST = "/list";//在线用户列表接口
	public static final String ONLINE_USER_OFFLINE = "/offline/{key}";//用户下线接口
	
}
