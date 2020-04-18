import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import {UserComponent} from './user.component';
import {UserListModule} from './list/user.list.module';
import {UserAddModule} from './add/user.add.module';
import {UserDetailModule} from './detail/user.detail.module';
import {UserModifyModule} from './modify/user.modify.module';
import {UserRoleAssignmentModule} from './userRoleAssignment/user.userRoleAssignment.module';
import {UserModuleAssignmentModule} from './userModuleAssignment/user.userModuleAssignment.module';

@NgModule({
  imports:[
    CommonModule,RouterModule,
    UserListModule,UserAddModule,UserDetailModule,UserModifyModule,UserRoleAssignmentModule,UserModuleAssignmentModule
  ],
  declarations:[UserComponent],
  exports:[UserComponent]
})

export class UserModule{

}
