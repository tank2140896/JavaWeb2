export class HttpRequestUrl{

    public static HTTP_REQUEST_PREFIX = 'http://localhost:8888/javaweb-web';

    //public static GET_KAPTCHA_SUFFIX = '/kaptcha';

    public static LOGIN_SUFFIX = '/login';

    public static LOGOUT_SUFFIX = '/logout';

    public static SYS_USER_LSIT_SUFFIX = '/sys/user/list';

    //public static GET_KAPTCHA:string = HttpRequestUrl.HTTP_REQUEST_PREFIX+HttpRequestUrl.GET_KAPTCHA_SUFFIX;

    public static LOGIN:string = HttpRequestUrl.HTTP_REQUEST_PREFIX+HttpRequestUrl.LOGIN_SUFFIX;

    public static LOGOUT:string = HttpRequestUrl.HTTP_REQUEST_PREFIX+HttpRequestUrl.LOGOUT_SUFFIX;

    public static SYS_USER_LSIT:string = HttpRequestUrl.HTTP_REQUEST_PREFIX+HttpRequestUrl.SYS_USER_LSIT_SUFFIX;

}