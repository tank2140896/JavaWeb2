"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var login_success_data_1 = require("../../models/login/login.success.data");
var head_token_1 = require("../../models/token/head.token");
var SessionService = /** @class */ (function () {
    function SessionService() {
    }
    SessionService.prototype.setLoginSuccessData = function (loginSuccessData) {
        window.sessionStorage.setItem('sessionData', loginSuccessData);
    };
    SessionService.prototype.getLoginSuccessData = function () {
        var getData = window.sessionStorage.getItem('sessionData');
        if (getData == null || getData == '') {
            return null;
        }
        var loginSuccessData = new login_success_data_1.LoginSuccessData();
        getData = JSON.parse(getData);
        loginSuccessData.setToken(getData.token);
        loginSuccessData.setUser(getData.user);
        loginSuccessData.setModuleList(getData.moduleList);
        loginSuccessData.setMenuList(getData.menuList);
        loginSuccessData.setAuthOperateList(getData.authOperateList);
        return loginSuccessData;
    };
    SessionService.prototype.getHeadToken = function () {
        var loginSuccessData = this.getLoginSuccessData();
        var headToken = new head_token_1.HeadToken();
        headToken.userId = loginSuccessData.getUser().userId;
        headToken.token = loginSuccessData.getToken();
        return headToken;
    };
    SessionService = __decorate([
        core_1.Injectable()
    ], SessionService);
    return SessionService;
}());
exports.SessionService = SessionService;
//# sourceMappingURL=SessionService.js.map