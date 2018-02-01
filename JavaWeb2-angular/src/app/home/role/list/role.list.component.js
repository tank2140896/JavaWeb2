"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var HttpRequestUrl_1 = require("../../../constant/HttpRequestUrl");
var DateUtil_1 = require("../../../util/DateUtil");
var DatepickerI18nService_1 = require("../../../service/DatepickerI18nService");
var result_page_1 = require("../../../models/result/result.page");
var role_list_1 = require("../../../models/role/role.list");
var RoleListComponent = /** @class */ (function () {
    function RoleListComponent(router, activatedRoute, ngbModal, httpService, authService, sessionService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.ngbModal = ngbModal;
        this.httpService = httpService;
        this.authService = authService;
        this.sessionService = sessionService;
        /** 操作权限 start */
        //userListZone:boolean;//用户列表
        /**
        userDeleteZone:boolean;//删除用户
        userAddZone:boolean;//新增用户
        userModifyZone:boolean;//修改用户
        userDetailZone:boolean;//用户详情
        userRoleAssignmentZone:boolean;//用户角色分配
        */
        /** 操作权限 end */
        this.roleList = new role_list_1.RoleList(); //用户列表搜索条件
        this.resultPage = new result_page_1.ResultPage(); //分页结果初始化
        //this.userListZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_LIST,false));
        /**
        this.userDeleteZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_DELETE,false));
        this.userAddZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_ADD,false));
        this.userModifyZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_MODIFY,false));
        this.userDetailZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_DETAIL,false));
        this.userRoleAssignmentZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_ROLE_ASSIGNMENT,false));
        */
    }
    //初始化
    RoleListComponent.prototype.ngOnInit = function () {
        /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
        this.userList.currentPage = 1;
        this.userList.pageSize = 5;
        */
        this.roleListFunction(this.roleList); //初始化角色列表
    };
    //搜索按钮
    RoleListComponent.prototype.roleSearch = function (currentPage) {
        this.resultPage = new result_page_1.ResultPage(); //对每次搜索进行初始化
        this.roleList.currentPage = currentPage;
        /** start 针对日期插件的特殊处理 */
        var createStartDate = this.roleList.createStartDate;
        var createEndDate = this.roleList.createEndDate;
        if (createStartDate != null && createStartDate != '') {
            this.roleList.createStartDate = DateUtil_1.DateUtil.formatDate(createStartDate);
        }
        if (createEndDate != null && createEndDate != '') {
            this.roleList.createEndDate = DateUtil_1.DateUtil.formatDate(createEndDate);
        }
        /** end 针对日期插件的特殊处理 */
        this.roleListFunction(this.roleList);
        /** start 针对日期插件的特殊处理 */
        this.roleList.createStartDate = createStartDate;
        this.roleList.createEndDate = createEndDate;
        /** end 针对日期插件的特殊处理 */
    };
    //重置
    RoleListComponent.prototype.reset = function () {
        this.roleList = new role_list_1.RoleList();
    };
    //角色搜索共通方法
    RoleListComponent.prototype.roleListFunction = function (roleList) {
        var _this = this;
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_ROLE_LIST, true), JSON.stringify(roleList), this.sessionService.getHeadToken()).subscribe(function (result) {
            if (result.code == 200) {
                var ret = result.data;
                //console.log(ret);
                _this.resultPage = new result_page_1.ResultPage(ret);
            }
            else {
                _this.router.navigate(['login']);
            }
        }, function (error) {
            _this.router.navigate(['login']);
        });
    };
    //删除用户
    RoleListComponent.prototype.deleteUser = function (userId, content) {
        var _this = this;
        this.ngbModal.open(content).result.then(function (result) {
            if (result) {
                _this.httpService.deleteData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_DELETE + '/' + userId, true), _this.sessionService.getHeadToken()).subscribe(function (result) {
                    //删除即使失败这里也暂不做任何处理
                    /** 删除成功重新刷新列表 */
                    _this.resultPage = new result_page_1.ResultPage();
                    _this.roleListFunction(_this.roleList);
                }, function (error) {
                    _this.router.navigate(['login']);
                });
            }
        }, function (reason) {
            //主要是ModalDismissReasons.ESC和ModalDismissReasons.BACKDROP_CLICK
        });
    };
    //新增用户
    RoleListComponent.prototype.addUser = function () {
        this.router.navigate(['../add'], { relativeTo: this.activatedRoute });
    };
    //修改用户
    RoleListComponent.prototype.modifyUser = function (userId) {
        this.router.navigate(['../modify'], { relativeTo: this.activatedRoute, queryParams: { 'userId': userId } });
    };
    //用户详情
    RoleListComponent.prototype.userDetail = function (userId) {
        this.router.navigate(['../detail'], { relativeTo: this.activatedRoute, queryParams: { 'userId': userId } });
    };
    //用户角色分配
    RoleListComponent.prototype.userRoleAssignment = function (userId) {
        this.router.navigate(['../roleAssignment'], { relativeTo: this.activatedRoute, queryParams: { 'userId': userId } });
    };
    RoleListComponent = __decorate([
        core_1.Component({
            selector: 'role-list',
            templateUrl: './role.list.html',
            styleUrls: ['./role.list.scss'],
            providers: [/*I18n,*/ { provide: ng_bootstrap_1.NgbDatepickerI18n, useClass: DatepickerI18nService_1.DatepickerI18nService }]
        })
    ], RoleListComponent);
    return RoleListComponent;
}());
exports.RoleListComponent = RoleListComponent;
//# sourceMappingURL=role.list.component.js.map