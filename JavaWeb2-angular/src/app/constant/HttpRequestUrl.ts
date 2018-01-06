export class HttpRequestUrl{

    public static HTTP_REQUEST_PREFIX = 'http://localhost:8888/javaweb-web';

    //public static GET_KAPTCHA_SUFFIX = '/kaptcha';

    public static LOGIN_SUFFIX = '/login';

    public static LOGOUT_SUFFIX = '/web/logout';

    public static SYS_USER_LSIT_SUFFIX = '/web/sys/user/list';//用户列表

    public static SYS_USER_ADD_SUFFIX = '/web/sys/user/add';//新增用户

    public static SYS_USER_DELETE_SUFFIX = '/web/sys/user/delete';//删除用户

    public static SYS_USER_MODIFY_SUFFIX = '/web/sys/user/modify';//修改用户

    public static SYS_USER_DETAIL_SUFFIX = '/web/sys/user/detail';//用户详情



    //public static GET_KAPTCHA:string = HttpRequestUrl.HTTP_REQUEST_PREFIX+HttpRequestUrl.GET_KAPTCHA_SUFFIX;

    public static LOGIN:string = HttpRequestUrl.HTTP_REQUEST_PREFIX+HttpRequestUrl.LOGIN_SUFFIX;

    public static LOGOUT:string = HttpRequestUrl.HTTP_REQUEST_PREFIX+HttpRequestUrl.LOGOUT_SUFFIX;

    public static SYS_USER_LSIT:string = HttpRequestUrl.HTTP_REQUEST_PREFIX+HttpRequestUrl.SYS_USER_LSIT_SUFFIX;

}
