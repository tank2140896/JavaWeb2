import {Routes} from '@angular/router';

import {UserListComponent} from './list/user.list.component';
import {UserAddComponent} from './add/user.add.component';

export const ROUTES_USER:Routes = [
    {path:'',component:UserListComponent},
    {path:'list',component:UserListComponent},
    {path:'add',component:UserAddComponent}
];
