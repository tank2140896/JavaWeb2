"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
var platform_browser_1 = require("@angular/platform-browser");
var http_1 = require("@angular/http");
var forms_1 = require("@angular/forms");
var app_routes_1 = require("./app.routes");
var app_component_1 = require("./app.component");
var home_component_1 = require("./home/home.component");
var header_component_1 = require("./home/header/header.component");
var sidebar_component_1 = require("./home/sidebar/sidebar.component");
var centeral_component_1 = require("./home/centeral/centeral.component");
var login_components_1 = require("./login/login.components");
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    core_1.NgModule({
        imports: [platform_browser_1.BrowserModule, http_1.HttpModule, forms_1.FormsModule, app_routes_1.routes],
        declarations: [app_component_1.AppComponent, login_components_1.LoginComponent, home_component_1.HomeComponent, header_component_1.HeaderComponent, sidebar_component_1.SidebarComponent, centeral_component_1.CenteralComponent],
        bootstrap: [app_component_1.AppComponent],
        providers: []
    })
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map