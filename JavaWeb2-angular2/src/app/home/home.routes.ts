import {Routes} from "@angular/router";

import {HomeComponent} from "./home.component";
import {CenteralRoutes} from "./centeral/centeral.routes";
import {UserManageRoutes} from "./user-manage/user-manage.routes";
import {AuthService} from "../service/auth/AuthService";

export const HomeRoutes:Routes = [
    {path:'home',component:HomeComponent,children:[
        ...CenteralRoutes,...UserManageRoutes
    ],canActivate:[AuthService]}
];