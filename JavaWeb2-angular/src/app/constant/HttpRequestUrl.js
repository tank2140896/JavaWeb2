"use strict";
exports.__esModule = true;
var HttpRequestUrl = /** @class */ (function () {
    function HttpRequestUrl() {
    }
    HttpRequestUrl.HTTP_REQUEST_PREFIX = 'http://localhost:8888/javaweb-web';
    //public static GET_KAPTCHA_SUFFIX = '/kaptcha';
    HttpRequestUrl.LOGIN_SUFFIX = '/web/login';
    HttpRequestUrl.LOGOUT_SUFFIX = '/web/logout';
    HttpRequestUrl.SYS_USER_LSIT_SUFFIX = '/web/sys/user/list'; //用户列表
    HttpRequestUrl.SYS_USER_ADD_SUFFIX = '/web/sys/user/add'; //新增用户
    HttpRequestUrl.SYS_USER_DELETE_SUFFIX = '/web/sys/user/delete'; //删除用户
    HttpRequestUrl.SYS_USER_MODIFY_SUFFIX = '/web/sys/user/modify'; //修改用户
    HttpRequestUrl.SYS_USER_DETAIL_SUFFIX = '/web/sys/user/detail'; //用户详情
    //public static GET_KAPTCHA:string = HttpRequestUrl.HTTP_REQUEST_PREFIX+HttpRequestUrl.GET_KAPTCHA_SUFFIX;
    HttpRequestUrl.LOGIN = HttpRequestUrl.HTTP_REQUEST_PREFIX + HttpRequestUrl.LOGIN_SUFFIX;
    HttpRequestUrl.LOGOUT = HttpRequestUrl.HTTP_REQUEST_PREFIX + HttpRequestUrl.LOGOUT_SUFFIX;
    HttpRequestUrl.SYS_USER_LSIT = HttpRequestUrl.HTTP_REQUEST_PREFIX + HttpRequestUrl.SYS_USER_LSIT_SUFFIX;
    return HttpRequestUrl;
}());
exports.HttpRequestUrl = HttpRequestUrl;
//# sourceMappingURL=HttpRequestUrl.js.map