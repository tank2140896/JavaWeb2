import {Routes} from "@angular/router";

import {HomeComponent} from "./home.component";
import {CenteralRoutes} from "./centeral/centeral.routes";
import {UserManageRoutes} from "./user-manage/user-manage.routes";

export const HomeRoutes:Routes = [
    {path:'home',component:HomeComponent,children:[
        ...CenteralRoutes,...UserManageRoutes
    ]}
];