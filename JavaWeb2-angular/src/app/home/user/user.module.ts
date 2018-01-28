import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

import {UserComponent} from "./user.component";
import {UserListModule} from "./list/user.list.module";
import {UserAddModule} from "./add/user.add.module";
import {UserModifyModule} from "./modify/user.modify.module";
import {UserDetailModule} from "./detail/user.detail.module";
import {UserRoleAssignmentModule} from "./roleAssignment/user.role.assignment.module";

@NgModule({
    imports:[CommonModule,RouterModule,UserListModule,UserAddModule,UserModifyModule,UserDetailModule,UserRoleAssignmentModule],
    declarations:[UserComponent],
    exports:[UserComponent]
})

export class UserModule{

}
