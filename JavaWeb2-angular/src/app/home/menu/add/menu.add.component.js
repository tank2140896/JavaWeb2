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
var menu_add_1 = require("../../../models/menu/menu.add");
var MenuAddComponent = /** @class */ (function () {
    function MenuAddComponent(router, activatedRoute, httpService, sessionService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.httpService = httpService;
        this.sessionService = sessionService;
        this.moduleTypeList = [
            { 'moduleTypeKey': '0', 'moduleTypeValue': '未定义' },
            { 'moduleTypeKey': '1', 'moduleTypeValue': '菜单' },
            { 'moduleTypeKey': '2', 'moduleTypeValue': '操作' },
        ]; //初始化模块类型下拉列表
        this.menuAdd = new menu_add_1.MenuAdd(); //模块新增请求参数
        //控制图标框是否能填
        this.disableStyle = true;
        this.moduleList = sessionService.getSessionData().moduleList;
        this.menuList = sessionService.getSessionData().menuList;
        this.authOperateList = sessionService.getSessionData().authOperateList;
    }
    //初始化
    MenuAddComponent.prototype.ngOnInit = function () {
        this.showList = this.moduleList;
    };
    //重置
    MenuAddComponent.prototype.reset = function () {
        this.showList = this.moduleList;
        this.menuAdd = new menu_add_1.MenuAdd();
    };
    //取消
    MenuAddComponent.prototype.cancel = function () {
        this.router.navigate(['../list'], { relativeTo: this.activatedRoute });
    };
    //保存
    MenuAddComponent.prototype.save = function () {
        var _this = this;
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.getPath(HttpRequestUrl_1.HttpRequestUrl.SYS_MODULE_ADD, true), JSON.stringify(this.menuAdd), this.sessionService.getHeadToken()).subscribe(function (result) {
            var getResult = result;
            if (getResult.code == 200) {
                _this.cancel();
            }
            else {
                _this.router.navigate(['login']);
            }
        }, function (error) {
            _this.router.navigate(['login']);
        });
    };
    //模块类型切换
    MenuAddComponent.prototype.moduleTypeChange = function () {
        var getModuleType = this.menuAdd.moduleType; //模块类型(0:未定义模块类型；1：菜单；2：操作)
        if (getModuleType == '0') { //未定义模块类型
            this.menuAdd.icon = '';
            this.disableStyle = true;
            this.showList = this.moduleList;
            this.menuAdd.parentId = undefined;
        }
        else if (getModuleType == '1') { //菜单
            this.disableStyle = false;
            this.showList = this.menuList;
        }
        else if (getModuleType == '2') { //操作
            this.menuAdd.icon = '';
            this.disableStyle = true;
            this.showList = this.authOperateList;
        }
    };
    MenuAddComponent = __decorate([
        core_1.Component({
            selector: 'menu-add',
            templateUrl: './menu.add.html',
            styleUrls: ['./menu.add.scss']
        })
    ], MenuAddComponent);
    return MenuAddComponent;
}());
exports.MenuAddComponent = MenuAddComponent;
//# sourceMappingURL=menu.add.component.js.map