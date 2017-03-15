import {Routes} from '@angular/router';

import {RoleListComponent} from './list/role.list.component';

export const ROUTES_ROLE:Routes = [
    {path:'',component:RoleListComponent},
    {path:'list',component:RoleListComponent}
];
