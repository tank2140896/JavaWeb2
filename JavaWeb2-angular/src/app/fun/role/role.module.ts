import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import {RoleComponent} from './role.component';
import {RoleListModule} from './list/role.list.module';
import {RoleAddModule} from './add/role.add.module';
import {RoleDetailModule} from './detail/role.detail.module';
import {RoleModifyModule} from './modify/role.modify.module';
import {RoleModuleAssignmentModule} from './roleModuleAssignment/role.roleModuleAssignment.module';

@NgModule({
  imports:[
    CommonModule,RouterModule,
    RoleListModule,RoleAddModule,RoleDetailModule,RoleModifyModule,RoleModuleAssignmentModule
  ],
  declarations:[RoleComponent],
  exports:[RoleComponent]
})

export class RoleModule{

}
