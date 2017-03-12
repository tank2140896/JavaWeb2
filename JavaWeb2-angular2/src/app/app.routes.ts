import {Routes,RouterModule} from '@angular/router';

import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.components";

const APP_ROUTES:Routes = [
    {path:'',component:LoginComponent},
    {path:'home',component:HomeComponent}
];

/*
 路由有两种策略，HashLocationStrategy和PathLocationStrategy，我这里用的是HashLocationStrategy
 如果用PathLocationStrategy的话可能需要在app.module.ts
 1、import {APP_BASE_HREF} from '@angular/common'
 2、providers: [{provide: APP_BASE_HREF,useValue: '/'}]
 然后再进行其它的处理......
*/
export const routes = RouterModule.forRoot(APP_ROUTES,{useHash:true});