import {RouterModule} from "@angular/router";

import {LoginComponent} from "./login/login.components";
import {HomeComponent} from "./home/home.component";
import {CenteralComponent} from "./home/centeral/centeral.component";
import {UserManageComponent} from "./home/user-manage/user-manage.component";
import {UserManageListComponent} from "./home/user-manage/list/user-manage.list.component";
import {UserManageAddComponent} from "./home/user-manage/add/user-manage.add.component";
import {UserManageModifyComponent} from "./home/user-manage/modify/user-manage.modify.component";

import {AuthService} from "./service/auth/AuthService";

const APP_ROUTES = [
    {path: '', component: LoginComponent},//默认登录页面
    {path: 'login', component: LoginComponent},//登录页面
    {path: 'home', component: HomeComponent, children: [
        {path: '', component: CenteralComponent},//默认主页面
        {path: 'userManage', component: UserManageComponent, children:[
            {path: '', component: UserManageListComponent, canActivate: [AuthService]},//默认用户管理页面
            {path:'list',component:UserManageListComponent, canActivate: [AuthService]},//用户列表页面
            {path:'add',component:UserManageAddComponent, canActivate: [AuthService]},//用户新增页面
            {path:'modify',component:UserManageModifyComponent, canActivate: [AuthService]}//用户新修改页面
        ], canActivate: [AuthService]},
        {path: '**', redirectTo: '/home', pathMatch: 'full'}
    ], canActivate: [AuthService]},
    {path: '**', redirectTo: '/', pathMatch: 'full'}//访问任何不存在的URL都将跳回登录页面
];

//路由有两种策略，HashLocationStrategy和PathLocationStrategy，我这里用的是HashLocationStrategy
export const AppRoutes = RouterModule.forRoot(APP_ROUTES,{useHash:true});

export const RouteFullPath = {
    'Home':'/home',
    'UserManageList':'/home/userManage/list',
    'UserManageAdd':'/home/userManage/add',
    'UserManageModify':'/home/userManage/modify'
}