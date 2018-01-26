import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

import {UserComponent} from "./user.component";
import {UserListModule} from "./list/user.list.module";
import {UserAddModule} from "./add/user.add.module";

@NgModule({
    imports:[CommonModule,RouterModule,UserListModule,UserAddModule],
    declarations:[UserComponent],
    exports:[UserComponent]
})

export class UserModule{

}
