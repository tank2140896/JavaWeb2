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
    /** 任何人都能访问 end */

    /** 登录才能访问 start */
    public static LOGOUT = '/web/loggedIn/logout';//用户退出
    public static GET_REDIS_USER_INFO = '/web/loggedIn/getRedisUserInfo';//获取redis中的用户信息
    /** 登录才能访问 end */

    /** 拥有权限才能访问 start */
    public static SYS_USER_LIST = '/web/sys/user/list';//用户列表
    public static SYS_USER_DELETE = '/web/sys/user/delete';//删除用户
    public static SYS_USER_ADD = '/web/sys/user/add';//新增用户
    public static SYS_USER_MODIFY = '/web/sys/user/modify';//修改用户
    public static SYS_USER_DETAIL = '/web/sys/user/detail';//用户详情
    public static SYS_USER_ROLE_INFO = '/web/sys/user/userRoleInfo';//用户角色信息
    public static SYS_USER_ROLE_ASSIGNMENT = '/web/sys/user/roleAssignment';//用户角色分配
    /** 拥有权限才能访问 end */

}
