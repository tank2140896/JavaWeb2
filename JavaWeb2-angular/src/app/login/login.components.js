"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var router_animations_1 = require("../router.animations");
var common_constant_1 = require("../constant/common.constant");
var user_login_1 = require("../models/user/user.login");
var HttpRequestUrl_1 = require("../constant/HttpRequestUrl");
var StringUtil_1 = require("../util/StringUtil");
var LoginComponent = /** @class */ (function () {
    function LoginComponent(router, httpService, sessionService) {
        this.router = router;
        this.httpService = httpService;
        this.sessionService = sessionService;
        this.uuid = null; //uuid
        this.userLogin = new user_login_1.UserLogin(); //用户登录参数封装，用到[()]双向绑定需要初始化
        this.userLoginErrorMessage = common_constant_1.CommonConstant.EMPTY;
    }
    LoginComponent.prototype.ngOnInit = function () {
        this.getKaptcha();
    };
    //获取验证码
    LoginComponent.prototype.getKaptcha = function () {
        if (this.uuid == null) {
            var getUuid = window.sessionStorage.getItem('kaptcha');
            if (getUuid == null || getUuid == '') {
                var generateUuid = StringUtil_1.StringUtil.getUuid();
                this.userLogin.uuid = generateUuid;
                window.sessionStorage.setItem('kaptcha', generateUuid);
            }
        }
        this.uuid = window.sessionStorage.getItem('kaptcha');
        this.imageUrl = HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.KAPTCHA, true) + '/' + this.uuid + '?time=' + new Date();
    };
    /*------ 用户登录 start ------*/
    LoginComponent.prototype.login = function () {
        var _this = this;
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.LOGIN, true), this.userLogin, null).subscribe(function (result) {
            if (result.code == 200) {
                var ret = result.data;
                //console.log(ret);
                _this.sessionService.setSessionData(JSON.stringify(ret));
                _this.router.navigate(['web']);
            }
            else {
                _this.userLoginErrorMessage = result.message;
            }
        }, function (error) {
            _this.userLoginErrorMessage = error;
        });
    };
    LoginComponent = __decorate([
        core_1.Component({
            selector: 'app-login',
            templateUrl: './login.html',
            styleUrls: ['./login.scss'],
            animations: [router_animations_1.routerTransition()]
        })
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.components.js.map