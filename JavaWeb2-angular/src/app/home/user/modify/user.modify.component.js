"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var HttpRequestUrl_1 = require("../../../constant/HttpRequestUrl");
var user_modify_1 = require("../../../models/user/user.modify");
var UserModifyComponent = /** @class */ (function () {
    //详情
    function UserModifyComponent(router, activatedRoute, httpService, sessionService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.httpService = httpService;
        this.sessionService = sessionService;
        this.userId = activatedRoute.snapshot.queryParams['userId'];
    }
    //初始化
    UserModifyComponent.prototype.ngOnInit = function () {
        this.userModify = new user_modify_1.UserModify();
        this.detail(); //获取用户详细信息
    };
    //取消
    UserModifyComponent.prototype.cancel = function () {
        this.router.navigate(['../list'], { relativeTo: this.activatedRoute });
    };
    //修改
    UserModifyComponent.prototype.modify = function () {
        var _this = this;
        this.httpService.putJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_MODIFY, true), JSON.stringify(this.userModify), this.sessionService.getHeadToken()).subscribe(function (result) {
            if (result.code == 200) {
                _this.cancel();
            }
            else {
                _this.router.navigate(['login']);
            }
        }, function (error) {
            _this.router.navigate(['login']);
        });
    };
    UserModifyComponent.prototype.detail = function () {
        var _this = this;
        this.httpService.getJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_DETAIL + '/' + this.userId, true), this.sessionService.getHeadToken()).subscribe(function (result) {
            if (result.code == 200) {
                var data = result.data;
                if (data != null) {
                    _this.userModify.userId = data.userId;
                    _this.userModify.userName = data.userName;
                    _this.userModify.personName = data.personName;
                    _this.userModify.email = data.email;
                    _this.userModify.phone = data.phone;
                    _this.userModify.remark = data.remark;
                }
            }
            else {
                _this.router.navigate(['login']);
            }
        }, function (error) {
            _this.router.navigate(['login']);
        });
    };
    UserModifyComponent = __decorate([
        core_1.Component({
            selector: 'user-modify',
            templateUrl: './user.modify.html',
            styleUrls: ['./user.modify.scss']
        })
    ], UserModifyComponent);
    return UserModifyComponent;
}());
exports.UserModifyComponent = UserModifyComponent;
//# sourceMappingURL=user.modify.component.js.map