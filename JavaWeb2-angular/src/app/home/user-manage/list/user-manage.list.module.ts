import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";

import {UserManageListComponent} from "./user-manage.list.component";

@NgModule({
    imports:[CommonModule,RouterModule,FormsModule],
    declarations:[UserManageListComponent],
    exports:[UserManageListComponent]
})

export class UserManageListModule{

}
