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
        this.tag = '+';
        this.hiddenFlag = true;
    }
    SidebarRecursionComponent.prototype.tagEvent = function () {
        if (this.hiddenFlag == true) {
            this.tag = '-';
            this.hiddenFlag = false;
        }
        else {
            this.tag = '+';
            this.hiddenFlag = true;
        }
    };
    __decorate([
        core_1.Input('tag')
    ], SidebarRecursionComponent.prototype, "tag");
    __decorate([
        core_1.Input('hiddenFlag')
    ], SidebarRecursionComponent.prototype, "hiddenFlag");
    __decorate([
        core_1.Input()
    ], SidebarRecursionComponent.prototype, "url");
    __decorate([
        core_1.Input()
    ], SidebarRecursionComponent.prototype, "name");
    __decorate([
        core_1.Input()
    ], SidebarRecursionComponent.prototype, "list");
    SidebarRecursionComponent = __decorate([
        core_1.Component({
            selector: 'sidebar-recursion',
            templateUrl: 'sidebar.recursion.html',
            styleUrls: ['sidebar.recursion.css']
        })
    ], SidebarRecursionComponent);
    return SidebarRecursionComponent;
}());
exports.SidebarRecursionComponent = SidebarRecursionComponent;
//# sourceMappingURL=sidebar.recursion.component.js.map