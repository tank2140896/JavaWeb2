import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";

import {UserManageAddComponent} from "./user-manage.add.component";

@NgModule({
    imports:[CommonModule,RouterModule,FormsModule],
    declarations:[UserManageAddComponent],
    exports:[UserManageAddComponent]
})

export class UserManageAddModule{

}
