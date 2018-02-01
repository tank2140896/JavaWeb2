import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

import {RoleListModule} from "./list/role.list.module";
import {RoleComponent} from "./role.component";

@NgModule({
    imports:[CommonModule,RouterModule,RoleListModule],
    declarations:[RoleComponent],
    exports:[RoleComponent]
})

export class RoleModule{

}
