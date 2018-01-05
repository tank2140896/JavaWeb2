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
var user_search_1 = require("../../../models/user/user.search");
var app_routes_1 = require("../../../app.routes");
var UserManageListComponent = /** @class */ (function () {
    function UserManageListComponent(httpService, authService, router, sessionService) {
        this.httpService = httpService;
        this.authService = authService;
        this.router = router;
        this.sessionService = sessionService;
        this.userSearch = new user_search_1.UserSearch();
        this.listData = null;
        this.listUserZone = authService.canShow(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_LSIT_SUFFIX);
        this.addUserZone = authService.canShow(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_ADD_SUFFIX);
        this.deleteUserZone = authService.canShow(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_DELETE_SUFFIX);
        this.modifyUserZone = authService.canShow(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_MODIFY_SUFFIX);
        this.detailUserZone = authService.canShow(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_DETAIL_SUFFIX);
    }
    //初始化获取用户列表
    UserManageListComponent.prototype.ngOnInit = function () {
        this.userSearch.currentPage = 1;
        this.userSearch.pageSize = 3;
        this.userSearchFunction(this.userSearch);
    };
    //搜索按钮
    UserManageListComponent.prototype.searchUser = function (currentPage) {
        this.userSearch.currentPage = currentPage;
        this.userSearch.pageSize = 3;
        this.userSearchFunction(this.userSearch);
    };
    //用户搜索共通方法
    UserManageListComponent.prototype.userSearchFunction = function (userSearch) {
        var _this = this;
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_LSIT, JSON.stringify(userSearch), this.sessionService.getHeadToken()).subscribe(function (result) {
            var ret = result.data;
            _this.listData = ret.data;
            _this.currentPage = ret.currentPage;
            _this.totalPage = ret.totalPage;
        });
    };
    //新增用户
    UserManageListComponent.prototype.addUser = function () {
        //this.router.navigate(['add'],{relativeTo: this.activatedRoute});
        this.router.navigate([app_routes_1.RouteFullPath.UserManageAdd]);
    };
    //修改用户
    UserManageListComponent.prototype.modifyUser = function (userId) {
        //this.router.navigate([RouteFullPath.UserManageModify],{queryParams:{'userId':{'a':10,'b':'30'}}});
        this.router.navigate([app_routes_1.RouteFullPath.UserManageModify], { queryParams: { 'userId': userId } });
    };
    UserManageListComponent = __decorate([
        core_1.Component({
            selector: 'user-manage-list',
            templateUrl: 'user-manage.list.html'
        })
    ], UserManageListComponent);
    return UserManageListComponent;
}());
exports.UserManageListComponent = UserManageListComponent;
//# sourceMappingURL=user-manage.list.component.js.map