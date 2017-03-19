import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

import {UserManageListComponent} from "./user-manage.list.component";

@NgModule({
    imports:[CommonModule,RouterModule],
    declarations:[UserManageListComponent],
    exports:[UserManageListComponent]
})

export class UserManageListModule{

}
