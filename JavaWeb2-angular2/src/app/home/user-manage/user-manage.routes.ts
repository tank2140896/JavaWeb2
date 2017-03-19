import {Routes} from '@angular/router';

import {UserManageListComponent} from "./list/user-manage.list.component";
import {UserManageAddComponent} from "./add/user-manage.add.component";

export const UserManageRoutes:Routes = [
    {path:'userManage/list',component:UserManageListComponent},
    {path:'userManage/add',component:UserManageAddComponent}
];