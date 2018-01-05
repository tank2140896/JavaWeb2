"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var app_routes_1 = require("../../app.routes");
var AuthService = /** @class */ (function () {
    function AuthService(sessionService) {
        this.sessionService = sessionService;
    }
    AuthService.prototype.canActivate = function (route, state) {
        var loginSuccessData = this.sessionService.getLoginSuccessData();
        if (loginSuccessData == null) {
            return false;
        }
        var moduleList = loginSuccessData.getModuleList();
        if (moduleList == null /*||authOperateList.length==0*/) {
            return false;
        }
        var url = state.url.split("?")[0]; //获得页面请求的URL
        //console.log(url);
        if (url == app_routes_1.RouteFullPath.Home) {
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
        return false;
    };
    AuthService.prototype.canShow = function (apiUrl) {
        //console.log(apiUrl);
        var loginSuccessData = this.sessionService.getLoginSuccessData();
        if (loginSuccessData == null) {
            return false;
        }
        var authOperateList = loginSuccessData.getAuthOperateList();
        if (authOperateList == null /*||authOperateList.length==0*/) {
            return false;
        }
        for (var _i = 0, authOperateList_1 = authOperateList; _i < authOperateList_1.length; _i++) {
            var i = authOperateList_1[_i];
            if (i.apiUrl == apiUrl) {
                return true;
            }
        }
        return false;
    };
    AuthService = __decorate([
        core_1.Injectable()
    ], AuthService);
    return AuthService;
}());
exports.AuthService = AuthService;
//# sourceMappingURL=AuthService.js.map