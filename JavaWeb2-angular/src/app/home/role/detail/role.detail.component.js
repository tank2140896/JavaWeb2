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
var role_detail_1 = require("../../../models/role/role.detail");
var RoleDetailComponent = /** @class */ (function () {
    function RoleDetailComponent(router, activatedRoute, httpService, sessionService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.httpService = httpService;
        this.sessionService = sessionService;
        this.roleId = activatedRoute.snapshot.queryParams['roleId'];
    }
    //初始化
    RoleDetailComponent.prototype.ngOnInit = function () {
        this.roleDetail = new role_detail_1.RoleDetail();
        this.detail(); //详情
    };
    //返回
    RoleDetailComponent.prototype.back = function () {
        this.router.navigate(['../list'], { relativeTo: this.activatedRoute });
    };
    //详情
    RoleDetailComponent.prototype.detail = function () {
        var _this = this;
        this.httpService.getJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_ROLE_DETAIL + '/' + this.roleId, true), this.sessionService.getHeadToken()).subscribe(function (result) {
            var getResult = result;
            if (getResult.code == 200) {
                var data = getResult.data;
                if (data != null) {
                    _this.roleDetail.roleName = data.roleName;
                    _this.roleDetail.remark = data.remark;
                }
            }
            else {
                _this.router.navigate(['login']);
            }
        }, function (error) {
            _this.router.navigate(['login']);
        });
    };
    RoleDetailComponent = __decorate([
        core_1.Component({
            selector: 'role-detail',
            templateUrl: './role.detail.html',
            styleUrls: ['./role.detail.scss']
        })
    ], RoleDetailComponent);
    return RoleDetailComponent;
}());
exports.RoleDetailComponent = RoleDetailComponent;
//# sourceMappingURL=role.detail.component.js.map