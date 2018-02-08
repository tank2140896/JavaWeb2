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
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var router_1 = require("@angular/router");
var home_component_1 = require("./home.component");
var header_module_1 = require("./header/header.module");
var sidebar_module_1 = require("./sidebar/sidebar.module");
var centeral_module_1 = require("./centeral/centeral.module");
var user_module_1 = require("./user/user.module");
var role_module_1 = require("./role/role.module");
var menu_module_1 = require("./menu/menu.module");
var HomeModule = /** @class */ (function () {
    function HomeModule() {
    }
    HomeModule = __decorate([
        core_1.NgModule({
            imports: [
                common_1.CommonModule, router_1.RouterModule, ng_bootstrap_1.NgbDropdownModule.forRoot(),
                header_module_1.HeaderModule, sidebar_module_1.SidebarModule, centeral_module_1.CenteralModule,
                user_module_1.UserModule, role_module_1.RoleModule, menu_module_1.MenuModule
            ],
            declarations: [home_component_1.HomeComponent],
            exports: [home_component_1.HomeComponent]
        })
    ], HomeModule);
    return HomeModule;
}());
exports.HomeModule = HomeModule;
//# sourceMappingURL=home.module.js.map