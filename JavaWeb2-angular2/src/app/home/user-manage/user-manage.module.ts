import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

import {UserManageComponent} from "./user-manage.component";
import {UserManageAddModule} from "./add/user-manage.add.module";
import {UserManageListModule} from "./list/user-manage.list.module";
import {UserManageModifyModule} from "./modify/user-manage.modify.module";

@NgModule({
    imports:[CommonModule,RouterModule,UserManageListModule,UserManageAddModule,UserManageModifyModule],
    declarations:[UserManageComponent],
    exports:[UserManageComponent]
})

export class UserManageModule{

}
