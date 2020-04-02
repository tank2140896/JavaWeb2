package com.javaweb.constant;

public class SwaggerConstant {

	/* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ swagger-基础常量 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */
	public static final String SWAGGER_TITLE = "SpringBoot使用Swagger构建API文档";
	public static final String SWAGGER_HEAD_USERID = "用户ID";
	public static final String SWAGGER_HEAD_TOKEN = "TOKEN";
	public static final String SWAGGER_HEAD_TYPE = "登录类型";
	/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ swagger-基础常量 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
	
	/* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ swagger-AllOpenController ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */
	public static final String SWAGGER_ALL_OPEN_CONTROLLER_TAGS = "1、无需登录即可访问模块";
	
	public static final String SWAGGER_GET_SERVE_TIME = "获取服务器时间接口";
	
	public static final String SWAGGER_LOGIN = "用户登录接口";
	public static final String SWAGGER_LOGIN_NOTES = "输入用户名、密码来进行登录";
	public static final String SWAGGER_LOGIN_PARAM = "用户登录实体类";
	
	public static final String SWAGGER_REQUEST_PARAMETER_LOST = "请求参数缺失接口";
	public static final String SWAGGER_INVALID_REQUEST = "请求失效接口";
	public static final String SWAGGER_REQUEST_PARAMETER_ERROR = "请求参数错误接口";
	public static final String SWAGGER_NO_AUTHORY = "没有权限接口";
	public static final String SWAGGER_NOT_FOUND = "请求接口不存在接口";
	public static final String SWAGGER_INTERNAL_ERROR = "系统异常接口";
	/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ swagger-AllOpenController ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
	
	/* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ swagger-LoginAccessController ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */
	public static final String SWAGGER_LOGIN_ACCESS_CONTROLLER_TAGS = "2、登录才可访问的模块";
	
	public static final String SWAGGER_LOGOUT = "用户登出接口";
	public static final String SWAGGER_GET_REDIS_TOKEN_DATA = "获取redis中的token信息接口";
	public static final String SWAGGER_GET_DICTIONARY = "获得字典信息接口";
	/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ swagger-LoginAccessController ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
	
	/* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ swagger-ModuleController ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */
	public static final String SWAGGER_MODULE_CONTROLLER_TAGS = "3、登录且需要权限才可访问的模块管理模块";
	
	public static final String SWAGGER_MODULE_GET_MODULE_ID_AND_NAME_LIST = "获取模块ID和模块名称列表接口";
	public static final String SWAGGER_MODULE_ADD = "新增模块接口";
	public static final String SWAGGER_MODULE_LIST = "查询模块接口";
	public static final String SWAGGER_MODULE_MODIFY = "修改模块接口";
	public static final String SWAGGER_MODULE_DETAIL = "模块详情接口";
	public static final String SWAGGER_MODULE_DELETE = "删除模块接口";
	/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ swagger-ModuleController ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
	
	/* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ swagger-RoleController ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */
	public static final String SWAGGER_ROLE_CONTROLLER_TAGS = "4、登录且需要权限才可访问的角色管理模块";
	
	public static final String SWAGGER_ROLE_ADD = "新增角色接口";
	public static final String SWAGGER_ROLE_LIST = "查询角色接口";
	public static final String SWAGGER_ROLE_MODIFY = "修改角色接口";
	public static final String SWAGGER_ROLE_DETAIL = "角色详情接口";
	public static final String SWAGGER_ROLE_DELETE = "删除角色接口";
	public static final String SWAGGER_ROLE_MODULE_ASSIGNMENT = "角色模块分配接口";
	public static final String SWAGGER_ROLE_MODULE_INFO = "角色模块信息接口";
	/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ swagger-RoleController ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
	
	/* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ swagger-UserController ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */
	public static final String SWAGGER_USER_CONTROLLER_TAGS = "5、登录且需要权限才可访问的用户管理模块";
	
	public static final String SWAGGER_USER_ADD = "新增用户接口";
	public static final String SWAGGER_USER_LIST = "查询用户接口";
	public static final String SWAGGER_USER_MODIFY = "修改用户接口";
	public static final String SWAGGER_USER_DETAIL = "用户详情接口";
	public static final String SWAGGER_USER_DELETE = "删除用户接口";
	public static final String SWAGGER_USER_ROLE_INFO = "用户角色信息接口";
	public static final String SWAGGER_USER_ROLE_ASSIGNMENT = "用户角色分配接口";
	public static final String SWAGGER_USER_MODULE_INFO = "用户模块信息接口";
	public static final String SWAGGER_USER_MODULE_ASSIGNMENT = "用户模块分配接口";
	/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ swagger-UserController ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
	
}
