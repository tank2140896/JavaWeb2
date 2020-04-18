import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {RoleModuleAssignmentComponent} from './role.roleModuleAssignment.component';
import {RoleModuleAssignmentModuleRecursionComponent} from './role.roleModuleAssignment.module.recursion.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[RoleModuleAssignmentComponent,RoleModuleAssignmentModuleRecursionComponent],
    exports:[RoleModuleAssignmentComponent]
})

export class RoleModuleAssignmentModule {

}
