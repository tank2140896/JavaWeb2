"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var HttpRequestUrl_1 = require("../../constant/HttpRequestUrl");
var head_token_1 = require("../../models/token/head.token");
var HeaderComponent = /** @class */ (function () {
    function HeaderComponent(sessionService, router, httpService) {
        this.sessionService = sessionService;
        this.router = router;
        this.httpService = httpService;
    }
    HeaderComponent.prototype.logout = function () {
        var _this = this;
        var loginSuccessData = this.sessionService.getLoginSuccessData();
        var headToken = new head_token_1.HeadToken();
        headToken.userId = loginSuccessData.getUser().userId;
        headToken.token = loginSuccessData.getToken();
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.LOGOUT, null, headToken).subscribe(function (result) {
            _this.sessionService.setLoginSuccessData(null);
            _this.router.navigate(['/']);
        });
    };
    HeaderComponent = __decorate([
        core_1.Component({
            selector: 'home-header',
            templateUrl: 'header.html',
            styleUrls: ['header.css']
        })
    ], HeaderComponent);
    return HeaderComponent;
}());
exports.HeaderComponent = HeaderComponent;
//# sourceMappingURL=header.component.js.map