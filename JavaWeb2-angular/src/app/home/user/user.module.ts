import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

import {UserComponent} from "./user.component";
import {UserListModule} from "./list/user.list.module";

@NgModule({
    imports:[CommonModule,RouterModule,UserListModule],
    declarations:[UserComponent],
    exports:[UserComponent]
})

export class UserModule{

}
