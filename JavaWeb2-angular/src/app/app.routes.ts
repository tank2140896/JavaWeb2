import {RouterModule} from "@angular/router";

import {AuthService} from "./service/AuthService";

import {LoginComponent} from "./login/login.components";
import {HomeComponent} from "./home/home.component";
import {UserComponent} from "./home/user/user.component";
import {UserListComponent} from "./home/user/list/user.list.component";
import {CenteralComponent} from "./home/centeral/centeral.component";

const APP_ROUTES = [
    {path:'',component:LoginComponent},//默认登录页面
    {path:'login',component:LoginComponent},//登录页面
    {path:'web',component:HomeComponent,children:[
        {path:'',component:CenteralComponent,canActivate:[AuthService]},
        {path:'sys/user',component:UserComponent, children:[
            {path:'',component:UserListComponent,canActivate:[AuthService]},//默认用户管理页面
            {path:'list',component:UserListComponent,canActivate:[AuthService]},//用户列表页面
            {path:'**',redirectTo:'/web',pathMatch:'full'}
        ],canActivate:[AuthService]},
        {path:'**',redirectTo: '/web', pathMatch: 'full'}
    ],canActivate:[AuthService]},//home页面
    {path:'**',redirectTo: '/', pathMatch: 'full'}//访问任何不存在的URL都将跳回登录页面
];

//路由有两种策略，HashLocationStrategy和PathLocationStrategy，我这里用的是HashLocationStrategy
export const AppRoutes = RouterModule.forRoot(APP_ROUTES,{useHash:true});
