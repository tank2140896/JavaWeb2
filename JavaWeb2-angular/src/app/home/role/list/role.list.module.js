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
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var role_list_component_1 = require("./role.list.component");
var RoleListModule = /** @class */ (function () {
    function RoleListModule() {
    }
    RoleListModule = __decorate([
        core_1.NgModule({
            imports: [common_1.CommonModule, router_1.RouterModule, forms_1.FormsModule, ng_bootstrap_1.NgbModule.forRoot()],
            declarations: [role_list_component_1.RoleListComponent],
            exports: [role_list_component_1.RoleListComponent]
        })
    ], RoleListModule);
    return RoleListModule;
}());
exports.RoleListModule = RoleListModule;
//# sourceMappingURL=role.list.module.js.map