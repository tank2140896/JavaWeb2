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
var UserRoleAssignmentComponent = /** @class */ (function () {
    function UserRoleAssignmentComponent(router, activatedRoute, httpService, sessionService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.httpService = httpService;
        this.sessionService = sessionService;
        this.userId = activatedRoute.snapshot.queryParams['userId'];
    }
    //初始化
    UserRoleAssignmentComponent.prototype.ngOnInit = function () {
        this.userRoleInfo(); //获取用户角色信息
    };
    //获取用户角色信息
    UserRoleAssignmentComponent.prototype.userRoleInfo = function () {
        var _this = this;
        this.httpService.getJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_ROLE_INFO + '/' + this.userId, true), this.sessionService.getHeadToken()).subscribe(function (result) {
            if (result.code == 200) {
                _this.roleInfo = result.data;
            }
            else {
                _this.router.navigate(['login']);
            }
        }, function (error) {
            _this.router.navigate(['login']);
        });
    };
    //取消
    UserRoleAssignmentComponent.prototype.cancel = function () {
        this.router.navigate(['../list'], { relativeTo: this.activatedRoute });
    };
    //保存
    UserRoleAssignmentComponent.prototype.save = function () {
        var _this = this;
        var postRoleInfo = this.roleInfo;
        var emptyArray = [];
        for (var i = 0; i < postRoleInfo.length; i++) {
            var each = postRoleInfo[i];
            if (true == each.checkFlag) {
                emptyArray.push(each.roleId);
            }
        }
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_ROLE_ASSIGNMENT + '/' + this.userId, true), JSON.stringify(emptyArray), this.sessionService.getHeadToken()).subscribe(function (result) {
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
    UserRoleAssignmentComponent = __decorate([
        core_1.Component({
            selector: 'user-roleAssignment',
            templateUrl: './user.role.assignment.html',
            styleUrls: ['./user.role.assignment.scss']
        })
    ], UserRoleAssignmentComponent);
    return UserRoleAssignmentComponent;
}());
exports.UserRoleAssignmentComponent = UserRoleAssignmentComponent;
//# sourceMappingURL=user.role.assignment.component.js.map