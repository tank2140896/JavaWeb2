import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

import {UserManageAddComponent} from "./user-manage.add.component";

@NgModule({
    imports:[CommonModule,RouterModule],
    declarations:[UserManageAddComponent],
    exports:[UserManageAddComponent]
})

export class UserManageAddModule{

}
