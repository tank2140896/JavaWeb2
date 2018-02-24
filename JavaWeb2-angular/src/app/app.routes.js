"use strict";
exports.__esModule = true;
var router_1 = require("@angular/router");
var AuthService_1 = require("./service/AuthService");
var login_components_1 = require("./login/login.components");
var home_component_1 = require("./home/home.component");
var demo_components_1 = require("./demo/demo.components");
var user_component_1 = require("./home/user/user.component");
var user_list_component_1 = require("./home/user/list/user.list.component");
var centeral_component_1 = require("./home/centeral/centeral.component");
var user_add_component_1 = require("./home/user/add/user.add.component");
var user_modify_component_1 = require("./home/user/modify/user.modify.component");
var user_detail_component_1 = require("./home/user/detail/user.detail.component");
var user_role_assignment_component_1 = require("./home/user/roleAssignment/user.role.assignment.component");
var role_component_1 = require("./home/role/role.component");
var role_list_component_1 = require("./home/role/list/role.list.component");
var role_add_component_1 = require("./home/role/add/role.add.component");
var role_detail_component_1 = require("./home/role/detail/role.detail.component");
var role_modify_component_1 = require("./home/role/modify/role.modify.component");
var role_menu_assignment_component_1 = require("./home/role/menuAssignment/role.menu.assignment.component");
var menu_component_1 = require("./home/menu/menu.component");
var menu_list_component_1 = require("./home/menu/list/menu.list.component");
var menu_add_component_1 = require("./home/menu/add/menu.add.component");
var menu_modify_component_1 = require("./home/menu/modify/menu.modify.component");
var menu_detail_component_1 = require("./home/menu/detail/menu.detail.component");
var APP_ROUTES = [
    { path: '', component: login_components_1.LoginComponent },
    { path: 'login', component: login_components_1.LoginComponent },
    { path: 'web', component: home_component_1.HomeComponent, children: [
            { path: '', component: centeral_component_1.CenteralComponent, canActivate: [AuthService_1.AuthService] },
            { path: 'sys/user', component: user_component_1.UserComponent, children: [
                    { path: 'list', component: user_list_component_1.UserListComponent, canActivate: [AuthService_1.AuthService] },
                    { path: 'add', component: user_add_component_1.UserAddComponent, canActivate: [AuthService_1.AuthService] },
                    { path: 'modify', component: user_modify_component_1.UserModifyComponent, canActivate: [AuthService_1.AuthService] },
                    { path: 'detail', component: user_detail_component_1.UserDetailComponent, canActivate: [AuthService_1.AuthService] },
                    { path: 'roleAssignment', component: user_role_assignment_component_1.UserRoleAssignmentComponent, canActivate: [AuthService_1.AuthService] },
                    { path: '**', redirectTo: '/web', pathMatch: 'full' }
                ], canActivate: [AuthService_1.AuthService] },
            { path: 'sys/role', component: role_component_1.RoleComponent, children: [
                    { path: 'list', component: role_list_component_1.RoleListComponent, canActivate: [AuthService_1.AuthService] },
                    { path: 'add', component: role_add_component_1.RoleAddComponent, canActivate: [AuthService_1.AuthService] },
                    { path: 'modify', component: role_modify_component_1.RoleModifyComponent, canActivate: [AuthService_1.AuthService] },
                    { path: 'detail', component: role_detail_component_1.RoleDetailComponent, canActivate: [AuthService_1.AuthService] },
                    { path: 'menuAssignment', component: role_menu_assignment_component_1.RoleMenuAssignmentComponent, canActivate: [AuthService_1.AuthService] },
                    { path: '**', redirectTo: '/web', pathMatch: 'full' }
                ], canActivate: [AuthService_1.AuthService] },
            { path: 'sys/module', component: menu_component_1.MenuComponent, children: [
                    { path: 'list', component: menu_list_component_1.MenuListComponent, canActivate: [AuthService_1.AuthService] },
                    { path: 'add', component: menu_add_component_1.MenuAddComponent, canActivate: [AuthService_1.AuthService] },
                    { path: 'modify', component: menu_modify_component_1.MenuModifyComponent, canActivate: [AuthService_1.AuthService] },
                    { path: 'detail', component: menu_detail_component_1.MenuDetailComponent, canActivate: [AuthService_1.AuthService] },
                    { path: '**', redirectTo: '/web', pathMatch: 'full' }
                ], canActivate: [AuthService_1.AuthService] },
            { path: '**', redirectTo: '/web', pathMatch: 'full' }
        ], canActivate: [AuthService_1.AuthService] },
    { path: 'demo', component: demo_components_1.DemoComponent },
    { path: '**', redirectTo: '/login', pathMatch: 'full' } //访问任何不存在的URL都将跳回登录页面
];
//路由有两种策略，HashLocationStrategy和PathLocationStrategy，我这里用的是HashLocationStrategy
exports.AppRoutes = router_1.RouterModule.forRoot(APP_ROUTES, { useHash: true });
//# sourceMappingURL=app.routes.js.map