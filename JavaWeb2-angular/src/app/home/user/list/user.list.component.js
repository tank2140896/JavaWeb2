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
var user_list_1 = require("../../../models/user/user.list");
var HttpRequestUrl_1 = require("../../../constant/HttpRequestUrl");
var DateUtil_1 = require("../../../util/DateUtil");
var DatepickerI18nService_1 = require("../../../service/DatepickerI18nService");
var result_page_1 = require("../../../models/page/result.page");
var UserListComponent = /** @class */ (function () {
    function UserListComponent(router, ngbModal, httpService, authService, sessionService) {
        this.router = router;
        this.ngbModal = ngbModal;
        this.httpService = httpService;
        this.authService = authService;
        this.sessionService = sessionService;
        //listUserZone:any;//用户列表
        //addUserZone:any;//用户新增
        //deleteUserZone:any;//用户删除
        //modifyUserZone:any;//用户修改
        //detailUserZone:any;//用户详情
        this.userList = new user_list_1.UserList(); //用户列表搜索条件
        this.resultPage = new result_page_1.ResultPage({}); //分页结果初始化
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
        this.resultPage.data = "loading";
        this.userList.currentPage = currentPage;
        /** start 针对日期插件的特殊处理 */
        var createStartDate = this.userList.createStartDate;
        var createEndDate = this.userList.createEndDate;
        if (createStartDate != null && createStartDate != '') {
            this.userList.createStartDate = DateUtil_1.DateUtil.formatDate(createStartDate);
        }
        if (createEndDate != null && createEndDate != '') {
            this.userList.createEndDate = DateUtil_1.DateUtil.formatDate(createEndDate);
        }
        /** end 针对日期插件的特殊处理 */
        this.userListFunction(this.userList);
        /** start 针对日期插件的特殊处理 */
        this.userList.createStartDate = createStartDate;
        this.userList.createEndDate = createEndDate;
        /** end 针对日期插件的特殊处理 */
    };
    //重置
    UserListComponent.prototype.reset = function () {
        this.userList = new user_list_1.UserList();
    };
    //用户搜索共通方法
    UserListComponent.prototype.userListFunction = function (userList) {
        var _this = this;
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_LIST, JSON.stringify(userList), this.sessionService.getHeadToken()).subscribe(function (result) {
            if (result.code == 200) {
                var ret = result.data;
                //console.log(ret);
                _this.resultPage = new result_page_1.ResultPage(ret);
            }
            else if (result.code == 500) {
                _this.resultPage.data = null;
            }
            else {
                _this.router.navigate(['login']);
            }
        }, function (error) {
            _this.resultPage.data = null;
        });
    };
    //删除用户
    UserListComponent.prototype.deleteUser = function (userId, content) {
        this.ngbModal.open(content).result.then(function (result) {
            if (result) {
                alert(userId); //TODO 执行删除操作
            }
            //this.closeResult = `Closed with: ${result}`;
        }, function (reason) {
            /*
            this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
            private getDismissReason(reason: any): string {
                if (reason === ModalDismissReasons.ESC) {
                    return 'by pressing ESC';
                } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
                    return 'by clicking on a backdrop';
                } else {
                    return  `with: ${reason}`;
                }
            }
            */
        });
    };
    UserListComponent = __decorate([
        core_1.Component({
            selector: 'user-list',
            templateUrl: './user.list.html',
            styleUrls: ['./user.list.scss'],
            providers: [{ provide: ng_bootstrap_1.NgbDatepickerI18n, useClass: DatepickerI18nService_1.DatepickerI18nService }]
            //providers:[I18n,{provide:NgbDatepickerI18n,useClass:DatepickerI18nService}]
        })
    ], UserListComponent);
    return UserListComponent;
}());
exports.UserListComponent = UserListComponent;
//# sourceMappingURL=user.list.component.js.map