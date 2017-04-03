import {Routes} from '@angular/router';

import {UserManageListComponent} from "./list/user-manage.list.component";
import {UserManageAddComponent} from "./add/user-manage.add.component";
import {UserManageModifyComponent} from "./modify/user-manage.modify.component";
import {AuthService} from "../../service/auth/AuthService";

export const UserManageRoutes:Routes = [
    /** 这里的配置要与页面的菜单配置路径一致 */
    {path:'userManage/list',component:UserManageListComponent,canActivate:[AuthService]},
    {path:'userManage/add',component:UserManageAddComponent,canActivate:[AuthService]},
    {path:'userManage/modify',component:UserManageModifyComponent,canActivate:[AuthService]}
];