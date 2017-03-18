import {Routes,RouterModule} from '@angular/router';

import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.components";
import {ROUTES_CENTERAL} from "./home/centeral/centeral.routes";

import {AuthService} from "./service/auth/AuthService";

const APP_ROUTES:Routes = [
    {path:'',component:LoginComponent},//任何人都可以访问登录界面
    {path:'home',component:HomeComponent,children:ROUTES_CENTERAL,canActivate:[AuthService]},//只有登录成功后才能访问
    {path:'**',redirectTo:'/',pathMatch:'full'}//访问任何不存在的URL都将跳回登录页面
];

/*
 路由有两种策略，HashLocationStrategy和PathLocationStrategy，我这里用的是HashLocationStrategy
 如果用PathLocationStrategy的话可能需要在app.module.ts
 1、import {APP_BASE_HREF} from '@angular/common'
 2、providers: [{provide: APP_BASE_HREF,useValue: '/'}]
 然后再进行其它的处理......
*/
export const routes = RouterModule.forRoot(APP_ROUTES,{useHash:true});