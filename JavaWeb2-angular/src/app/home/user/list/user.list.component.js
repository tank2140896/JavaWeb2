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
var result_page_1 = require("../../../models/result/result.page");
var UserListComponent = /** @class */ (function () {
    function UserListComponent(router, ngbModal, httpService, authService, sessionService) {
        this.router = router;
        this.ngbModal = ngbModal;
        this.httpService = httpService;
        this.authService = authService;
        this.sessionService = sessionService;
        /** 操作权限 end */
        this.userList = new user_list_1.UserList(); //用户列表搜索条件
        this.resultPage = new result_page_1.ResultPage(); //分页结果初始化
        //this.userListZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_LIST,false));
        this.userDeleteZone = authService.canShow(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_DELETE, false));
    }
    //初始化
    UserListComponent.prototype.ngOnInit = function () {
        /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
        this.userList.currentPage = 1;
        this.userList.pageSize = 5;
        */
        this.userListFunction(this.userList); //初始化用户列表
    };
    //搜索按钮
    UserListComponent.prototype.userSearch = function (currentPage) {
        this.resultPage = new result_page_1.ResultPage(); //对每次搜索进行初始化
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
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_USER_LIST, true), JSON.stringify(userList), this.sessionService.getHeadToken()).subscribe(function (result) {
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
    UserListComponent.prototype.deleteUser = function (userId, content) {
        this.ngbModal.open(content).result.then(function (result) {
            if (result) {
                alert(userId); //TODO 执行删除操作
            }
        }, function (reason) {
            //主要是ModalDismissReasons.ESC和ModalDismissReasons.BACKDROP_CLICK
        });
    };
    UserListComponent = __decorate([
        core_1.Component({
            selector: 'user-list',
            templateUrl: './user.list.html',
            styleUrls: ['./user.list.scss'],
            providers: [/*I18n,*/ { provide: ng_bootstrap_1.NgbDatepickerI18n, useClass: DatepickerI18nService_1.DatepickerI18nService }]
        })
    ], UserListComponent);
    return UserListComponent;
}());
exports.UserListComponent = UserListComponent;
//# sourceMappingURL=user.list.component.js.map