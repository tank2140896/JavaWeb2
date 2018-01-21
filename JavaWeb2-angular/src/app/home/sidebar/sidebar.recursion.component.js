"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var SidebarRecursionComponent = /** @class */ (function () {
    function SidebarRecursionComponent() {
        this.showMenu = true;
    }
    SidebarRecursionComponent.prototype.addExpandClass = function () {
        if (this.showMenu == true) {
            this.showMenu = false;
        }
        else {
            this.showMenu = true;
        }
    };
    __decorate([
        core_1.Input('showMenu')
    ], SidebarRecursionComponent.prototype, "showMenu");
    __decorate([
        core_1.Input()
    ], SidebarRecursionComponent.prototype, "url");
    __decorate([
        core_1.Input()
    ], SidebarRecursionComponent.prototype, "name");
    __decorate([
        core_1.Input()
    ], SidebarRecursionComponent.prototype, "icon");
    __decorate([
        core_1.Input()
    ], SidebarRecursionComponent.prototype, "list");
    SidebarRecursionComponent = __decorate([
        core_1.Component({
            //encapsulation参考：https://stackoverflow.com/questions/36214546/styles-in-component-for-d3-js-do-not-show-in-angular-2/36226061#36226061
            encapsulation: core_1.ViewEncapsulation.None,
            selector: 'home-sidebar-recursion',
            templateUrl: './sidebar.recursion.html',
            styleUrls: ['./sidebar.component.scss']
        })
    ], SidebarRecursionComponent);
    return SidebarRecursionComponent;
}());
exports.SidebarRecursionComponent = SidebarRecursionComponent;
//# sourceMappingURL=sidebar.recursion.component.js.map