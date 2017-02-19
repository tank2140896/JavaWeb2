"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
require("core-js");
require("zone.js");
var platform_browser_dynamic_1 = require("@angular/platform-browser-dynamic");
var core_1 = require("@angular/core");
var core_2 = require("@angular/core");
var platform_browser_1 = require("@angular/platform-browser");
var LoginComponent = LoginComponent_1 = (function () {
    function LoginComponent() {
    }
    return LoginComponent;
}());
LoginComponent = LoginComponent_1 = __decorate([
    core_2.NgModule({
        declarations: [LoginComponent_1],
        imports: [platform_browser_1.BrowserModule],
        bootstrap: [LoginComponent_1]
    }),
    core_1.Component({
        selector: 'web-app',
        templateUrl: 'app/login/login.html',
        styleUrls: ['app/login/login.css']
    })
], LoginComponent);
exports.LoginComponent = LoginComponent;
platform_browser_dynamic_1.platformBrowserDynamic().bootstrapModule(LoginComponent); //.catch(err => console.error(err));
var LoginComponent_1;
//# sourceMappingURL=login.component.js.map