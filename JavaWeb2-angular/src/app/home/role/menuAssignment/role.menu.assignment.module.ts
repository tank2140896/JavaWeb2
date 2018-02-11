import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";

import {RoleMenuAssignmentComponent} from "./role.menu.assignment.component";
import {RoleMenuAssignmentRecursionComponent} from "./role.menu.assignment.recursion.component";

@NgModule({
    imports:[CommonModule,RouterModule,FormsModule],
    declarations:[RoleMenuAssignmentComponent,RoleMenuAssignmentRecursionComponent],
    exports:[RoleMenuAssignmentComponent]
})

export class RoleMenuAssignmentModule {

}
