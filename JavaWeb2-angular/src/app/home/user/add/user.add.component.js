"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var user_add_1 = require("../../../models/user/user.add");
var HttpRequestUrl_1 = require("../../../constant/HttpRequestUrl");
var UserAddComponent = /** @class */ (function () {
    function UserAddComponent(router, activatedRoute, httpService, sessionService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.httpService = httpService;
        this.sessionService = sessionService;
        this.userAdd = new user_add_1.UserAdd(); //用户新增请求参数
    }
    //初始化
    UserAddComponent.prototype.ngOnInit = function () {
    };
    //重置
    UserAddComponent.prototype.reset = function () {
        this.userAdd = new user_add_1.UserAdd();
    };
    //取消
    UserAddComponent.prototype.cancel = function () {
        this.router.navigate(['../list'], { relativeTo: this.activatedRoute });
    };
    //保存
    UserAddComponent.prototype.save = function () {
        var _this = this;
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_ADD, true), JSON.stringify(this.userAdd), this.sessionService.getHeadToken()).subscribe(function (result) {
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
    UserAddComponent = __decorate([
        core_1.Component({
            selector: 'user-add',
            templateUrl: './user.add.html',
            styleUrls: ['./user.add.scss']
        })
    ], UserAddComponent);
    return UserAddComponent;
}());
exports.UserAddComponent = UserAddComponent;
//# sourceMappingURL=user.add.component.js.map