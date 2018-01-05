"use strict";
exports.__esModule = true;
var router_1 = require("@angular/router");
var login_components_1 = require("./login/login.components");
var home_component_1 = require("./home/home.component");
var centeral_component_1 = require("./home/centeral/centeral.component");
var user_manage_component_1 = require("./home/user-manage/user-manage.component");
var user_manage_list_component_1 = require("./home/user-manage/list/user-manage.list.component");
var user_manage_add_component_1 = require("./home/user-manage/add/user-manage.add.component");
var user_manage_modify_component_1 = require("./home/user-manage/modify/user-manage.modify.component");
var AuthService_1 = require("./service/auth/AuthService");
var APP_ROUTES = [
    { path: '', component: login_components_1.LoginComponent },
    { path: 'login', component: login_components_1.LoginComponent },
    { path: 'home', component: home_component_1.HomeComponent, children: [
            { path: '', component: centeral_component_1.CenteralComponent },
            { path: 'userManage', component: user_manage_component_1.UserManageComponent, children: [
                    { path: '', component: user_manage_list_component_1.UserManageListComponent, canActivate: [AuthService_1.AuthService] },
                    { path: 'list', component: user_manage_list_component_1.UserManageListComponent, canActivate: [AuthService_1.AuthService] },
                    { path: 'add', component: user_manage_add_component_1.UserManageAddComponent, canActivate: [AuthService_1.AuthService] },
                    { path: 'modify', component: user_manage_modify_component_1.UserManageModifyComponent, canActivate: [AuthService_1.AuthService] } //用户新修改页面
                ], canActivate: [AuthService_1.AuthService] },
            { path: '**', redirectTo: '/home', pathMatch: 'full' }
        ], canActivate: [AuthService_1.AuthService] },
    { path: '**', redirectTo: '/', pathMatch: 'full' } //访问任何不存在的URL都将跳回登录页面
];
//路由有两种策略，HashLocationStrategy和PathLocationStrategy，我这里用的是HashLocationStrategy
exports.AppRoutes = router_1.RouterModule.forRoot(APP_ROUTES, { useHash: true });
exports.RouteFullPath = {
    'Home': '/home',
    'UserManageList': '/home/userManage/list',
    'UserManageAdd': '/home/userManage/add',
    'UserManageModify': '/home/userManage/modify'
};
//# sourceMappingURL=app.routes.js.map