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
var menu_detail_1 = require("../../../models/menu/menu.detail");
var MenuDetailComponent = /** @class */ (function () {
    function MenuDetailComponent(router, activatedRoute, httpService, sessionService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.httpService = httpService;
        this.sessionService = sessionService;
        this.moduleTypeList = [
            { 'moduleTypeKey': '0', 'moduleTypeValue': '未定义' },
            { 'moduleTypeKey': '1', 'moduleTypeValue': '菜单' },
            { 'moduleTypeKey': '2', 'moduleTypeValue': '操作' },
        ]; //初始化模块类型下拉列表
        this.moduleId = activatedRoute.snapshot.queryParams['moduleId'];
        this.moduleList = sessionService.getSessionData().moduleList;
    }
    //初始化
    MenuDetailComponent.prototype.ngOnInit = function () {
        this.menuDetail = new menu_detail_1.MenuDetail();
        this.showList = this.moduleList;
        this.detail(); //获取模块详细信息
    };
    //返回
    MenuDetailComponent.prototype.back = function () {
        this.router.navigate(['../list'], { relativeTo: this.activatedRoute });
    };
    //详情
    MenuDetailComponent.prototype.detail = function () {
        var _this = this;
        this.httpService.getJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_MODULE_DETAIL + '/' + this.moduleId, true), this.sessionService.getHeadToken()).subscribe(function (result) {
            var getResult = result;
            if (getResult.code == 200) {
                var data = getResult.data;
                if (data != null) {
                    _this.menuDetail = data;
                }
            }
            else {
                _this.router.navigate(['login']);
            }
        }, function (error) {
            _this.router.navigate(['login']);
        });
    };
    MenuDetailComponent = __decorate([
        core_1.Component({
            selector: 'menu-detail',
            templateUrl: './menu.detail.html',
            styleUrls: ['./menu.detail.scss']
        })
    ], MenuDetailComponent);
    return MenuDetailComponent;
}());
exports.MenuDetailComponent = MenuDetailComponent;
//# sourceMappingURL=menu.detail.component.js.map