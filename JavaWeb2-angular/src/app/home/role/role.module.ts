import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

import {RoleListModule} from "./list/role.list.module";
import {RoleComponent} from "./role.component";
import {RoleAddModule} from "./add/role.add.module";
import {RoleDetailModule} from "./detail/role.detail.module";
import {RoleModifyModule} from "./modify/role.modify.module";

@NgModule({
    imports:[CommonModule,RouterModule,RoleListModule,RoleAddModule,RoleDetailModule,RoleModifyModule],
    declarations:[RoleComponent],
    exports:[RoleComponent]
})

export class RoleModule{

}
