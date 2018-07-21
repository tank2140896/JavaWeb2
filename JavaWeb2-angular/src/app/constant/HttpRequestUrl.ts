export class HttpRequestUrl{

    public static getPath(urlName:string,isFullPath:boolean):string{
        if(isFullPath){
            return HttpRequestUrl.HTTP_REQUEST_PREFIX+urlName;
        }else{
            return urlName;
        }
    }

    public static HTTP_REQUEST_PREFIX = 'http://localhost:8888/javaweb-web';

    /** 任何人都能访问 start */
    public static LOGIN = '/login';//用户登录
    public static KAPTCHA = '/kaptcha';//获取验证码
    /** 任何人都能访问 end */

    /** 登录才能访问 start */
    public static LOGOUT = '/web/pc/loginAccess/logout';//用户退出
    public static GET_REDIS_USER_INFO = '/web/pc/loginAccess/getRedisUserInfo';//获取redis中的用户信息
    /** 登录才能访问 end */

    /** 拥有权限才能访问 start */
    public static SYS_USER_LIST = '/web/pc/sys/user/list';//用户列表
    public static SYS_USER_DELETE = '/web/pc/sys/user/delete';//删除用户
    public static SYS_USER_ADD = '/web/pc/sys/user/add';//新增用户
    public static SYS_USER_MODIFY = '/web/pc/sys/user/modify';//修改用户
    public static SYS_USER_DETAIL = '/web/pc/sys/user/detail';//用户详情
    public static SYS_USER_ROLE_INFO = '/web/pc/sys/user/userRoleInfo';//用户角色信息
    public static SYS_USER_ROLE_ASSIGNMENT = '/web/pc/sys/user/roleAssignment';//用户角色分配

    public static SYS_ROLE_LIST = '/web/pc/sys/role/list';//角色列表
    public static SYS_ROLE_DELETE = '/web/pc/sys/role/delete';//删除角色
    public static SYS_ROLE_ADD = '/web/pc/sys/role/add';//新增角色
    public static SYS_ROLE_MODIFY = '/web/pc/sys/role/modify';//修改角色
    public static SYS_ROLE_DETAIL = '/web/pc/sys/role/detail';//角色详情
    public static SYS_ROLE_MODULE_INFO = '/web/pc/sys/role/roleModuleInfo';//角色模块信息
    public static SYS_ROLE_MODULE_ASSIGNMENT = '/web/pc/sys/role/moduleAssignment';//角色模块分配

    public static SYS_MODULE_LIST = '/web/pc/sys/module/list';//模块列表
    public static SYS_MODULE_DELETE = '/web/pc/sys/module/delete';//删除模块
    public static SYS_MODULE_ADD = '/web/pc/sys/module/add';//新增模块
    public static SYS_MODULE_MODIFY = '/web/pc/sys/module/modify';//修改模块
    public static SYS_MODULE_DETAIL = '/web/pc/sys/module/detail';//模块详情

    public static OTHER_ONLINE_CHAT = '/web/pc/other/onlineChat/sendChatMessage';//发送聊天信息
    /** 拥有权限才能访问 end */

}
