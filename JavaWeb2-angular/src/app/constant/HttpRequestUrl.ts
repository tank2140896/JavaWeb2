export class HttpRequestUrl{

    public static HTTP_REQUEST_PREFIX = 'http://localhost:8888/javaweb-web';

    public static LOGIN = HttpRequestUrl.HTTP_REQUEST_PREFIX+'/login';//用户登录

    public static LOGOUT = HttpRequestUrl.HTTP_REQUEST_PREFIX+'/web/loggedIn/logout';//用户退出

    public static GET_REDIS_USER_INFO = HttpRequestUrl.HTTP_REQUEST_PREFIX+'/web/loggedIn/getRedisUserInfo';//用户退出


    public static SYS_USER_LIST = HttpRequestUrl.HTTP_REQUEST_PREFIX+'/web/sys/user/list';//用户列表

}
