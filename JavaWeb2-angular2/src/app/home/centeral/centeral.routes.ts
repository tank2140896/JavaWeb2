import {Routes} from '@angular/router';

import {UserComponent} from './sys_manager/user_manager/user.component';
import {RoleComponent} from './sys_manager/role_manager/role.component';
import {CenteralComponent} from "./centeral.component";

import {ROUTES_USER} from "./sys_manager/user_manager/user.routes";
import {ROUTES_ROLE} from "./sys_manager/role_manager/role.routes";

export const ROUTES_CENTERAL:Routes = [
    {path:'',component:CenteralComponent},
    {path:'sysManager/userManger',component:UserComponent,children:ROUTES_USER},
    {path:'sysManager/roleManger',component:RoleComponent,children:ROUTES_ROLE}
];

