"use strict";
exports.__esModule = true;
var HttpRequestUrl = /** @class */ (function () {
    function HttpRequestUrl() {
    }
    HttpRequestUrl.HTTP_REQUEST_PREFIX = 'http://localhost:8888/javaweb-web';
    HttpRequestUrl.LOGIN = HttpRequestUrl.HTTP_REQUEST_PREFIX + '/login'; //用户登录
    HttpRequestUrl.LOGOUT = HttpRequestUrl.HTTP_REQUEST_PREFIX + '/web/loggedIn/logout'; //用户退出
    HttpRequestUrl.GET_REDIS_USER_INFO = HttpRequestUrl.HTTP_REQUEST_PREFIX + '/web/loggedIn/getRedisUserInfo'; //用户退出
    HttpRequestUrl.SYS_USER_LIST = HttpRequestUrl.HTTP_REQUEST_PREFIX + '/web/sys/user/list'; //用户列表
    return HttpRequestUrl;
}());
exports.HttpRequestUrl = HttpRequestUrl;
//# sourceMappingURL=HttpRequestUrl.js.map