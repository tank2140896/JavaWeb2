"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var HttpRequestUrl_1 = require("../constant/HttpRequestUrl");
var head_token_1 = require("../models/token/head.token");
var AuthService = /** @class */ (function () {
    function AuthService(sessionService, httpService) {
        this.sessionService = sessionService;
        this.httpService = httpService;
    }
    /** URL路径权限判断 */
    AuthService.prototype.canActivate = function (route, state) {
        var sessionData = this.sessionService.getSessionData();
        if (sessionData == null) { //防止用户清空缓存
            return false;
        }
        var headToken = new head_token_1.HeadToken();
        headToken.token = sessionData.token;
        headToken.userId = sessionData.user.userId;
        headToken.type = sessionData.type;
        //this.getRedisUserInfo(headToken);//防止服务器端redis的session失效
        sessionData = this.sessionService.getSessionData();
        if (sessionData == null) {
            return false;
        }
        else {
            var moduleList = sessionData.moduleList;
            if (moduleList == null /*||moduleList.length==0*/) {
                return false;
            }
            var url = state.url.split("?")[0]; //获得页面请求的URL
            //console.log(url);
            if (url == '/web') {
                return true;
            }
            else {
                for (var _i = 0, moduleList_1 = moduleList; _i < moduleList_1.length; _i++) {
                    var i = moduleList_1[_i];
                    if (url == i.pageUrl) {
                        return true;
                    }
                }
            }
        }
        return false;
    };
    /** 操作权限判断 */
    AuthService.prototype.canShow = function (apiUrl) {
        var sessionData = this.sessionService.getSessionData();
        /** 为降低服务器压力，去掉没太大必要的校验
        if(sessionData==null){//防止用户清空缓存
            return false;
        }
        let headToken:HeadToken = new HeadToken();
        headToken.token = sessionData.token;
        headToken.userId = sessionData.user.userId;
        headToken.type = sessionData.type;
        this.getRedisUserInfo(headToken);//防止服务器端redis的session失效
        sessionData = this.sessionService.getSessionData();
        */
        if (sessionData == null) {
            return false;
        }
        else {
            var authOperateList = sessionData.authOperateList;
            if (authOperateList == null /*||authOperateList.length==0*/) {
                return false;
            }
            for (var _i = 0, authOperateList_1 = authOperateList; _i < authOperateList_1.length; _i++) {
                var i = authOperateList_1[_i];
                var splitApiUrl = i.apiUrl.split(',');
                for (var j = 0; j < splitApiUrl.length; j++) {
                    if (apiUrl == splitApiUrl[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    };
    AuthService.prototype.getRedisUserInfo = function (headToken) {
        var _this = this;
        this.httpService.getJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.GET_REDIS_USER_INFO, true), headToken).subscribe(function (result) {
            var getResult = result;
            var ret = getResult.data;
            if (ret == null) {
                _this.sessionService.clearSessionData();
            }
        });
    };
    AuthService = __decorate([
        core_1.Injectable()
    ], AuthService);
    return AuthService;
}());
exports.AuthService = AuthService;
//# sourceMappingURL=AuthService.js.map