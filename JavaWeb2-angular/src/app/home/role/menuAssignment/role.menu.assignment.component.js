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
var RoleMenuAssignmentComponent = /** @class */ (function () {
    function RoleMenuAssignmentComponent(router, activatedRoute, httpService, sessionService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.httpService = httpService;
        this.sessionService = sessionService;
        //保存
        this.emptyArray = [];
        this.roleId = activatedRoute.snapshot.queryParams['roleId'];
    }
    //初始化
    RoleMenuAssignmentComponent.prototype.ngOnInit = function () {
        this.roleModuleInfo();
    };
    RoleMenuAssignmentComponent.prototype.roleModuleInfo = function () {
        var _this = this;
        this.httpService.getJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_ROLE_MODULE_INFO + '/' + this.roleId, true), this.sessionService.getHeadToken()).subscribe(function (result) {
            if (result.code == 200) {
                var ret = result.data;
                _this.roleName = ret.role.roleName;
                _this.roleModuleList = ret.list;
            }
            else {
                _this.router.navigate(['login']);
            }
        }, function (error) {
            _this.router.navigate(['login']);
        });
    };
    //取消
    RoleMenuAssignmentComponent.prototype.cancel = function () {
        this.router.navigate(['../list'], { relativeTo: this.activatedRoute });
    };
    RoleMenuAssignmentComponent.prototype.save = function () {
        var _this = this;
        //console.log(this.roleModuleList);
        this.deepSearch(this.roleModuleList);
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_ROLE_MODULE_ASSIGNMENT + '/' + this.roleId, true), JSON.stringify(this.emptyArray), this.sessionService.getHeadToken()).subscribe(function (result) {
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
    RoleMenuAssignmentComponent.prototype.deepSearch = function (list) {
        if (list != null && list.length != 0) {
            for (var i = 0; i < list.length; i++) {
                var each = list[i];
                if (true == each.checkFlag) {
                    this.emptyArray.push(each.moduleId);
                }
                if (each.list != null && each.list.length > 0) {
                    console.log(each.list);
                    this.deepSearch(each.list);
                }
            }
        }
    };
    RoleMenuAssignmentComponent = __decorate([
        core_1.Component({
            selector: 'role-menuAssignment',
            templateUrl: './role.menu.assignment.html',
            styleUrls: ['./role.menu.assignment.scss']
        })
    ], RoleMenuAssignmentComponent);
    return RoleMenuAssignmentComponent;
}());
exports.RoleMenuAssignmentComponent = RoleMenuAssignmentComponent;
//# sourceMappingURL=role.menu.assignment.component.js.map