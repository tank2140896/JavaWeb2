"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var app_routes_1 = require("../../../app.routes");
var user_add_1 = require("../../../models/user/user.add");
var UserManageAddComponent = /** @class */ (function () {
    //校验可以参考：https://github.com/UltimateAngular/ngxerrors
    function UserManageAddComponent(router) {
        this.router = router;
        this.userAdd = new user_add_1.UserAdd();
    }
    //取消
    UserManageAddComponent.prototype.cancel = function () {
        this.router.navigate([app_routes_1.RouteFullPath.UserManageList]);
    };
    //保存
    UserManageAddComponent.prototype.save = function () {
        console.log(this.userAdd);
    };
    UserManageAddComponent = __decorate([
        core_1.Component({
            selector: 'user-manage-add',
            templateUrl: 'user-manage.add.html'
        })
    ], UserManageAddComponent);
    return UserManageAddComponent;
}());
exports.UserManageAddComponent = UserManageAddComponent;
//# sourceMappingURL=user-manage.add.component.js.map