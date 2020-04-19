export class ApiConstant{

  public static HTTP_REQUEST_PREFIX = 'http://localhost:2001';
  public static WEBSOCKET_REQUEST_URL = 'ws://127.0.0.1:2100/ws';

  /** 任何人都能访问 start */
  public static GET_SERVE_TIME = '/getServeTime';//获取服务器时间
  public static LOGIN = '/webLogin';//用户登录
  /** 任何人都能访问 end */

  /** 登录才能访问 start */
  public static LOGOUT = '/web/loginAccess/logout';//用户退出
  public static GET_REDIS_TOKEN_DATA = '/web/loginAccess/getRedisTokenData';//获取redis中的用户信息
  public static GET_DICTIONARY = '/web/loginAccess/getDictionary';//获取字典信息
  /** 登录才能访问 end */

  /** 拥有权限才能访问 start */
  public static SYS_USER_LIST = '/web/sys/user/list';//用户列表
  public static SYS_USER_DELETE = '/web/sys/user/delete';//删除用户
  public static SYS_USER_ADD = '/web/sys/user/add';//新增用户
  public static SYS_USER_MODIFY = '/web/sys/user/modify';//修改用户
  public static SYS_USER_DETAIL = '/web/sys/user/detail';//用户详情
  public static SYS_USER_ROLE_INFO = '/web/sys/user/userRoleInfo';//用户角色信息
  public static SYS_USER_ROLE_ASSIGNMENT = '/web/sys/user/userRoleAssignment';//用户角色分配
  public static SYS_USER_MODULE_INFO = '/web/sys/user/userModuleInfo';//用户模块信息
  public static SYS_USER_MODULE_ASSIGNMENT = '/web/sys/user/userModuleAssignment';//用户模块分配

  public static SYS_ROLE_LIST = '/web/sys/role/list';//角色列表
  public static SYS_ROLE_DELETE = '/web/sys/role/delete';//删除角色
  public static SYS_ROLE_ADD = '/web/sys/role/add';//新增角色
  public static SYS_ROLE_MODIFY = '/web/sys/role/modify';//修改角色
  public static SYS_ROLE_DETAIL = '/web/sys/role/detail';//角色详情
  public static SYS_ROLE_MODULE_INFO = '/web/sys/role/roleModuleInfo';//角色模块信息
  public static SYS_ROLE_MODULE_ASSIGNMENT = '/web/sys/role/roleModuleAssignment';//角色模块分配

  public static SYS_MODULE_LIST = '/web/sys/module/list';//模块列表
  public static SYS_MODULE_DELETE = '/web/sys/module/delete';//删除模块
  public static SYS_MODULE_ADD = '/web/sys/module/add';//新增模块
  public static SYS_MODULE_MODIFY = '/web/sys/module/modify';//修改模块
  public static SYS_MODULE_MODULE_ID_AND_NAME_LIST = '/web/sys/module/getModuleIdAndNameList';//获取模块ID和模块名称列表
  public static SYS_MODULE_DETAIL = '/web/sys/module/detail';//模块详情

  public static SYS_DICTIONARY_LIST = '/web/sys/dictionary/list';//字典列表
  public static SYS_DICTIONARY_DELETE = '/web/sys/dictionary/delete';//删除字典
  public static SYS_DICTIONARY_ADD = '/web/sys/dictionary/add';//新增字典
  public static SYS_DICTIONARY_MODIFY = '/web/sys/dictionary/modify';//修改字典
  public static SYS_DICTIONARY_DETAIL = '/web/sys/dictionary/detail';//字典详情

  public static SYS_OPERATION_LOG_LIST = '/web/sys/operationLog/list';//操作日志列表

  public static SYS_SCHEDULE_LIST = '/web/sys/schedule/list';//日程列表
  public static SYS_SCHEDULE_ADD = '/web/sys/schedule/add';//新增日程

  public static SYS_INTERFACES_LIST = '/web/sys/interfaces/list';//接口列表
  public static SYS_INTERFACES_MODIFY = '/web/sys/interfaces/modify';//修改接口
  public static SYS_INTERFACES_DETAIL = '/web/sys/interfaces/detail';//接口详情
  /** 拥有权限才能访问 end */

  public static getPath(urlName:string,isFullPath:boolean):string{
    if(isFullPath){
      return ApiConstant.HTTP_REQUEST_PREFIX+urlName;
    }else{
      return urlName;
    }
  }

}