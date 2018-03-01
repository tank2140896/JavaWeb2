"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var RoleMenuAssignmentRecursionComponent = /** @class */ (function () {
    function RoleMenuAssignmentRecursionComponent() {
    }
    __decorate([
        core_1.Input()
    ], RoleMenuAssignmentRecursionComponent.prototype, "checkFlag");
    __decorate([
        core_1.Input()
    ], RoleMenuAssignmentRecursionComponent.prototype, "moduleName");
    __decorate([
        core_1.Input()
    ], RoleMenuAssignmentRecursionComponent.prototype, "moduleId");
    __decorate([
        core_1.Input()
    ], RoleMenuAssignmentRecursionComponent.prototype, "list");
    RoleMenuAssignmentRecursionComponent = __decorate([
        core_1.Component({
            //encapsulation参考：https://stackoverflow.com/questions/36214546/styles-in-component-for-d3-js-do-not-show-in-angular-2/36226061#36226061
            encapsulation: core_1.ViewEncapsulation.None,
            selector: 'role-menuAssignmentRecursion',
            templateUrl: './role.menu.assignment.recursion.html',
            styleUrls: ['./role.menu.assignment.scss']
        })
    ], RoleMenuAssignmentRecursionComponent);
    return RoleMenuAssignmentRecursionComponent;
}());
exports.RoleMenuAssignmentRecursionComponent = RoleMenuAssignmentRecursionComponent;
//# sourceMappingURL=role.menu.assignment.recursion.component.js.map