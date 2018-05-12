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
var DatepickerI18nService_1 = require("../../../service/DatepickerI18nService");
var result_page_1 = require("../../../models/result/result.page");
var menu_list_1 = require("../../../models/menu/menu.list");
var HttpRequestUrl_1 = require("../../../constant/HttpRequestUrl");
var DateUtil_1 = require("../../../util/DateUtil");
var MenuListComponent = /** @class */ (function () {
    function MenuListComponent(router, activatedRoute, ngbModal, httpService, authService, sessionService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.ngbModal = ngbModal;
        this.httpService = httpService;
        this.authService = authService;
        this.sessionService = sessionService;
        /** 操作权限 end */
        this.moduleTypeList = [
            { 'moduleTypeKey': '0', 'moduleTypeValue': '所有' },
            { 'moduleTypeKey': '1', 'moduleTypeValue': '菜单' },
            { 'moduleTypeKey': '2', 'moduleTypeValue': '操作' },
        ]; //初始化模块类型下拉列表
        this.menuList = new menu_list_1.MenuList(); //模块列表搜索条件
        this.resultPage = new result_page_1.ResultPage(); //分页结果初始化
        //this.menuListZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_MODULE_LIST,false));
        this.menuDeleteZone = authService.canShow(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_MODULE_DELETE, false));
        this.menuAddZone = authService.canShow(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_MODULE_ADD, false));
        this.menuModifyZone = authService.canShow(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_MODULE_MODIFY, false));
        this.menuDetailZone = authService.canShow(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_MODULE_DETAIL, false));
    }
    //初始化
    MenuListComponent.prototype.ngOnInit = function () {
        /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
         this.menuList.currentPage = 1;
         this.menuList.pageSize = 5;
         */
        this.menuListFunction(this.menuList); //初始化模块列表
    };
    //搜索按钮
    MenuListComponent.prototype.menuSearch = function (currentPage) {
        this.resultPage = new result_page_1.ResultPage(); //对每次搜索进行初始化
        this.menuList.currentPage = currentPage;
        /** start 针对日期插件的特殊处理 */
        var createStartDate = this.menuList.createStartDate;
        var createEndDate = this.menuList.createEndDate;
        if (createStartDate != null && createStartDate != '') {
            this.menuList.createStartDate = DateUtil_1.DateUtil.formatDate(createStartDate);
        }
        if (createEndDate != null && createEndDate != '') {
            this.menuList.createEndDate = DateUtil_1.DateUtil.formatDate(createEndDate);
        }
        /** end 针对日期插件的特殊处理 */
        this.menuListFunction(this.menuList);
        /** start 针对日期插件的特殊处理 */
        this.menuList.createStartDate = createStartDate;
        this.menuList.createEndDate = createEndDate;
        /** end 针对日期插件的特殊处理 */
    };
    //重置
    MenuListComponent.prototype.reset = function () {
        this.menuList = new menu_list_1.MenuList();
    };
    //模块搜索共通方法
    MenuListComponent.prototype.menuListFunction = function (menuList) {
        var _this = this;
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_MODULE_LIST, true), JSON.stringify(menuList), this.sessionService.getHeadToken()).subscribe(function (result) {
            var getResult = result;
            if (getResult.code == 200) {
                var ret = getResult.data;
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
    //删除模块
    MenuListComponent.prototype.deleteMenu = function (moduleId, content) {
        var _this = this;
        this.ngbModal.open(content).result.then(function (result) {
            if (result) {
                _this.httpService.deleteData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_MODULE_DELETE + '/' + moduleId, true), _this.sessionService.getHeadToken()).subscribe(function (result) {
                    //删除即使失败这里也暂不做任何处理
                    /** 删除成功重新刷新列表 */
                    _this.resultPage = new result_page_1.ResultPage();
                    _this.menuListFunction(_this.menuList);
                }, function (error) {
                    _this.router.navigate(['login']);
                });
            }
        }, function (reason) {
            //主要是ModalDismissReasons.ESC和ModalDismissReasons.BACKDROP_CLICK
        });
    };
    //新增模块
    MenuListComponent.prototype.addMenu = function () {
        this.router.navigate(['../add'], { relativeTo: this.activatedRoute });
    };
    //修改模块
    MenuListComponent.prototype.modifyMenu = function (moduleId) {
        this.router.navigate(['../modify'], { relativeTo: this.activatedRoute, queryParams: { 'moduleId': moduleId } });
    };
    //模块详情
    MenuListComponent.prototype.menuDetail = function (moduleId) {
        this.router.navigate(['../detail'], { relativeTo: this.activatedRoute, queryParams: { 'moduleId': moduleId } });
    };
    MenuListComponent = __decorate([
        core_1.Component({
            selector: 'menu-list',
            templateUrl: './menu.list.html',
            styleUrls: ['./menu.list.scss'],
            providers: [/*I18n,*/ { provide: ng_bootstrap_1.NgbDatepickerI18n, useClass: DatepickerI18nService_1.DatepickerI18nService }]
        })
    ], MenuListComponent);
    return MenuListComponent;
}());
exports.MenuListComponent = MenuListComponent;
//# sourceMappingURL=menu.list.component.js.map