"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var SidebarComponent = /** @class */ (function () {
    function SidebarComponent(sessionService /*,private domSanitizer:DomSanitizer*/) {
        this.sessionService = sessionService; /*,private domSanitizer:DomSanitizer*/
        //获取sidebar菜单列表
        this.menuList = sessionService.getLoginSuccessData().getMenuList();
        console.log(sessionService.getLoginSuccessData());
        //this.htmlWrite=this.domSanitizer.bypassSecurityTrustHtml(this.htmlContent);
    }
    SidebarComponent = __decorate([
        core_1.Component({
            selector: 'home-sidebar',
            templateUrl: 'sidebar.html',
            styleUrls: ['sidebar.css']
        })
    ], SidebarComponent);
    return SidebarComponent;
}());
exports.SidebarComponent = SidebarComponent;
//# sourceMappingURL=sidebar.component.js.map