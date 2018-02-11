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
var menu_modify_1 = require("../../../models/menu/menu.modify");
var MenuModifyComponent = /** @class */ (function () {
    function MenuModifyComponent(router, activatedRoute, httpService, sessionService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.httpService = httpService;
        this.sessionService = sessionService;
        this.moduleTypeList = [
            { 'moduleTypeKey': '0', 'moduleTypeValue': '未定义' },
            { 'moduleTypeKey': '1', 'moduleTypeValue': '菜单' },
            { 'moduleTypeKey': '2', 'moduleTypeValue': '操作' },
        ]; //初始化模块类型下拉列表
        //控制图标框是否能填
        this.disableStyle = true;
        this.moduleId = activatedRoute.snapshot.queryParams['moduleId'];
        this.moduleList = sessionService.getSessionData().moduleList;
        this.menuList = sessionService.getSessionData().menuList;
        this.authOperateList = sessionService.getSessionData().authOperateList;
    }
    //初始化
    MenuModifyComponent.prototype.ngOnInit = function () {
        this.menuModify = new menu_modify_1.MenuModify();
        this.showList = this.moduleList;
        this.detail(); //获取模块详细信息
    };
    //取消
    MenuModifyComponent.prototype.cancel = function () {
        this.router.navigate(['../list'], { relativeTo: this.activatedRoute });
    };
    //修改
    MenuModifyComponent.prototype.modify = function () {
        var _this = this;
        this.httpService.putJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_MODULE_MODIFY, true), JSON.stringify(this.menuModify), this.sessionService.getHeadToken()).subscribe(function (result) {
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
    MenuModifyComponent.prototype.detail = function () {
        var _this = this;
        this.httpService.getJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_MODULE_DETAIL + '/' + this.moduleId, true), this.sessionService.getHeadToken()).subscribe(function (result) {
            if (result.code == 200) {
                var data = result.data;
                if (data != null) {
                    _this.menuModify = data;
                }
            }
            else {
                _this.router.navigate(['login']);
            }
        }, function (error) {
            _this.router.navigate(['login']);
        });
    };
    //模块类型切换
    MenuModifyComponent.prototype.moduleTypeChange = function () {
        var getModuleType = this.menuModify.moduleType; //模块类型(0:未定义模块类型；1：菜单；2：操作)
        if (getModuleType == '0') {
            this.menuModify.icon = '';
            this.disableStyle = true;
            this.showList = this.moduleList;
            this.menuModify.parentId = undefined;
        }
        else if (getModuleType == '1') {
            this.disableStyle = false;
            this.showList = this.menuList;
        }
        else if (getModuleType == '2') {
            this.menuModify.icon = '';
            this.disableStyle = true;
            this.showList = this.authOperateList;
        }
    };
    MenuModifyComponent = __decorate([
        core_1.Component({
            selector: 'menu-modify',
            templateUrl: './menu.modify.html',
            styleUrls: ['./menu.modify.scss']
        })
    ], MenuModifyComponent);
    return MenuModifyComponent;
}());
exports.MenuModifyComponent = MenuModifyComponent;
//# sourceMappingURL=menu.modify.component.js.map