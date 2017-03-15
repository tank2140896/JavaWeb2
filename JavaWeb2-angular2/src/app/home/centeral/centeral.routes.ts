import {Routes} from '@angular/router';

import {UserListComponent} from './sys_manager/user_manager/user.list.component';
import {RoleListComponent} from './sys_manager/role_manager/role.list.component';
import {CenteralComponent} from "./centeral.component";

export const ROUTES_CENTERAL:Routes = [
    {path:'',component:CenteralComponent},
    {path:'sysManager/userManger/list',component:UserListComponent},
    {path:'sysManager/roleManger/list',component:RoleListComponent}
];

