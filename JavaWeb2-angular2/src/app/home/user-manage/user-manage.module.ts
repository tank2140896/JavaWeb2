import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

import {UserManageComponent} from "./user-manage.component";
import {UserManageAddModule} from "./add/user-manage.add.module";
import {UserManageListModule} from "./list/user-manage.list.module";

@NgModule({
    imports:[CommonModule,RouterModule,UserManageListModule,UserManageAddModule],
    declarations:[UserManageComponent],
    exports:[UserManageComponent]
})

export class UserManageModule{

}
