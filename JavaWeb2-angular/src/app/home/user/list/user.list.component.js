"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var user_list_1 = require("../../../models/user/user.list");
var HttpRequestUrl_1 = require("../../../constant/HttpRequestUrl");
var UserListComponent = /** @class */ (function () {
    //listUserZone:any;//用户列表
    //addUserZone:any;//用户新增
    //deleteUserZone:any;//用户删除
    //modifyUserZone:any;//用户修改
    //detailUserZone:any;//用户详情
    function UserListComponent(httpService, authService, router, sessionService) {
        this.httpService = httpService;
        this.authService = authService;
        this.router = router;
        this.sessionService = sessionService;
        this.userList = new user_list_1.UserList(); //用户列表搜索条件
        this.data = null;
        //this.listUserZone = authService.canShow(HttpRequestUrl.SYS_USER_LSIT_SUFFIX);
        //this.addUserZone = authService.canShow(HttpRequestUrl.SYS_USER_ADD_SUFFIX);
        //this.deleteUserZone = authService.canShow(HttpRequestUrl.SYS_USER_DELETE_SUFFIX);
        //this.modifyUserZone = authService.canShow(HttpRequestUrl.SYS_USER_MODIFY_SUFFIX);
        //this.detailUserZone = authService.canShow(HttpRequestUrl.SYS_USER_DETAIL_SUFFIX);
    }
    //初始化获取用户列表
    UserListComponent.prototype.ngOnInit = function () {
        /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
        this.userList.currentPage = 1;
        this.userList.pageSize = 5;
        */
        this.userListFunction(this.userList); //初始化用户列表
    };
    //搜索按钮
    UserListComponent.prototype.userSearch = function (currentPage) {
        this.userList.currentPage = currentPage;
        this.userListFunction(this.userList);
    };
    //用户搜索共通方法
    UserListComponent.prototype.userListFunction = function (userList) {
        var _this = this;
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_LIST, JSON.stringify(userList), this.sessionService.getHeadToken()).subscribe(function (result) {
            if (result.code == 200) {
                var ret = result.data;
                //console.log(ret);
                _this.data = ret.data;
                _this.currentPage = ret.currentPage;
                _this.totalPage = ret.totalPage;
            }
            else {
            }
        }, function (error) {
        });
    };
    UserListComponent = __decorate([
        core_1.Component({
            selector: 'user-list',
            templateUrl: './user.list.html',
            styleUrls: ['./user.list.scss']
        })
    ], UserListComponent);
    return UserListComponent;
}());
exports.UserListComponent = UserListComponent;
//# sourceMappingURL=user.list.component.js.map