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
var role_modify_1 = require("../../../models/role/role.modify");
var RoleModifyComponent = /** @class */ (function () {
    function RoleModifyComponent(router, activatedRoute, httpService, sessionService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.httpService = httpService;
        this.sessionService = sessionService;
        this.roleId = activatedRoute.snapshot.queryParams['roleId'];
    }
    //初始化
    RoleModifyComponent.prototype.ngOnInit = function () {
        this.roleModify = new role_modify_1.RoleModify();
        this.detail(); //获取角色详细信息
    };
    //取消
    RoleModifyComponent.prototype.cancel = function () {
        this.router.navigate(['../list'], { relativeTo: this.activatedRoute });
    };
    //修改
    RoleModifyComponent.prototype.modify = function () {
        var _this = this;
        this.httpService.putJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_ROLE_MODIFY, true), JSON.stringify(this.roleModify), this.sessionService.getHeadToken()).subscribe(function (result) {
            var getResult = result;
            if (getResult.code == 200) {
                _this.cancel();
            }
            else {
                _this.router.navigate(['login']);
            }
        }, function (error) {
            _this.router.navigate(['login']);
        });
    };
    RoleModifyComponent.prototype.detail = function () {
        var _this = this;
        this.httpService.getJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_ROLE_DETAIL + '/' + this.roleId, true), this.sessionService.getHeadToken()).subscribe(function (result) {
            var getResult = result;
            if (getResult.code == 200) {
                var data = getResult.data;
                if (data != null) {
                    _this.roleModify.roleId = data.roleId;
                    _this.roleModify.roleName = data.roleName;
                    _this.roleModify.remark = data.remark;
                }
            }
            else {
                _this.router.navigate(['login']);
            }
        }, function (error) {
            _this.router.navigate(['login']);
        });
    };
    RoleModifyComponent = __decorate([
        core_1.Component({
            selector: 'role-modify',
            templateUrl: './role.modify.html',
            styleUrls: ['./role.modify.scss']
        })
    ], RoleModifyComponent);
    return RoleModifyComponent;
}());
exports.RoleModifyComponent = RoleModifyComponent;
//# sourceMappingURL=role.modify.component.js.map