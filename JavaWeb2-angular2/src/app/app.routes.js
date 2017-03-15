"use strict";
var router_1 = require("@angular/router");
var home_component_1 = require("./home/home.component");
var login_components_1 = require("./login/login.components");
var centeral_routes_1 = require("./home/centeral/centeral.routes");
var APP_ROUTES = [
    { path: '', component: login_components_1.LoginComponent },
    { path: 'home', component: home_component_1.HomeComponent, children: centeral_routes_1.ROUTES_CENTERAL },
    { path: '**', redirectTo: '/', pathMatch: 'full' } //访问任何不存在的URL都将跳回登录页面
];
/*
 路由有两种策略，HashLocationStrategy和PathLocationStrategy，我这里用的是HashLocationStrategy
 如果用PathLocationStrategy的话可能需要在app.module.ts
 1、import {APP_BASE_HREF} from '@angular/common'
 2、providers: [{provide: APP_BASE_HREF,useValue: '/'}]
 然后再进行其它的处理......
*/
exports.routes = router_1.RouterModule.forRoot(APP_ROUTES, { useHash: true });
//# sourceMappingURL=app.routes.js.map