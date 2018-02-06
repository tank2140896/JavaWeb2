"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var common_1 = require("@angular/common");
var router_1 = require("@angular/router");
var forms_1 = require("@angular/forms");
var role_add_component_1 = require("./role.add.component");
var RoleAddModule = /** @class */ (function () {
    function RoleAddModule() {
    }
    RoleAddModule = __decorate([
        core_1.NgModule({
            imports: [common_1.CommonModule, router_1.RouterModule, forms_1.FormsModule],
            declarations: [role_add_component_1.RoleAddComponent],
            exports: [role_add_component_1.RoleAddComponent]
        })
    ], RoleAddModule);
    return RoleAddModule;
}());
exports.RoleAddModule = RoleAddModule;
//# sourceMappingURL=role.add.module.js.map