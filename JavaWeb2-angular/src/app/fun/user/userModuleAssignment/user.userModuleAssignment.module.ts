import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {UserModuleAssignmentComponent} from './user.userModuleAssignment.component';
import {UserModuleAssignmentModuleRecursionComponent} from './user.userModuleAssignment.module.recursion.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[UserModuleAssignmentComponent,UserModuleAssignmentModuleRecursionComponent],
    exports:[UserModuleAssignmentComponent]
})

export class UserModuleAssignmentModule {

}
