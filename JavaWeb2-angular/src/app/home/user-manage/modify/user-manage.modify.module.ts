import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {UserManageModifyComponent} from "./user-manage.modify.component";


@NgModule({
    imports:[CommonModule,RouterModule],
    declarations:[UserManageModifyComponent],
    exports:[UserManageModifyComponent]
})

export class UserManageModifyModule{

}
