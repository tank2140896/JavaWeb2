import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {UserRoleAssignmentComponent} from './user.userRoleAssignment.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[UserRoleAssignmentComponent],
    exports:[UserRoleAssignmentComponent]
})

export class UserRoleAssignmentModule {

}
