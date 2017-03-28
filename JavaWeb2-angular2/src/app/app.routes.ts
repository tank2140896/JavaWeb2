///<reference path="login/login.routes.ts"/>
import {Routes,RouterModule} from '@angular/router';

import {LoginRoutes} from "./login/login.routes";
import {HomeRoutes} from "./home/home.routes";
import {LoginComponent} from "./login/login.components";

const APP_ROUTES:Routes = [
    ...LoginRoutes,...HomeRoutes,//App主入口可以路由到2个不同结构的页面（登录页面和首页）
    {path:'',component:LoginComponent},//默认登录页面
    {path:'**',redirectTo:'/',pathMatch:'full'}//访问任何不存在的URL都将跳回登录页面
];

//路由有两种策略，HashLocationStrategy和PathLocationStrategy，我这里用的是HashLocationStrategy
export const AppRoutes = RouterModule.forRoot(APP_ROUTES,{useHash:true});