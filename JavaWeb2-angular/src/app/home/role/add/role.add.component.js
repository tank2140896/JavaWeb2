"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var role_add_1 = require("../../../models/role/role.add");
var HttpRequestUrl_1 = require("../../../constant/HttpRequestUrl");
var RoleAddComponent = /** @class */ (function () {
    function RoleAddComponent(router, activatedRoute, httpService, sessionService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.httpService = httpService;
        this.sessionService = sessionService;
        this.roleAdd = new role_add_1.RoleAdd(); //角色新增请求参数
    }
    //初始化
    RoleAddComponent.prototype.ngOnInit = function () {
    };
    //重置
    RoleAddComponent.prototype.reset = function () {
        this.roleAdd = new role_add_1.RoleAdd();
    };
    //取消
    RoleAddComponent.prototype.cancel = function () {
        this.router.navigate(['../list'], { relativeTo: this.activatedRoute });
    };
    //保存
    RoleAddComponent.prototype.save = function () {
        var _this = this;
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_ROLE_ADD, true), JSON.stringify(this.roleAdd), this.sessionService.getHeadToken()).subscribe(function (result) {
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
    RoleAddComponent = __decorate([
        core_1.Component({
            selector: 'role-add',
            templateUrl: './role.add.html',
            styleUrls: ['./role.add.scss']
        })
    ], RoleAddComponent);
    return RoleAddComponent;
}());
exports.RoleAddComponent = RoleAddComponent;
//# sourceMappingURL=role.add.component.js.map