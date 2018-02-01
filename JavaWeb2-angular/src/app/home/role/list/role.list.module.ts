import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";

import {RoleListComponent} from "./role.list.component";

@NgModule({
    imports:[CommonModule,RouterModule,FormsModule,NgbModule.forRoot()],
    declarations:[RoleListComponent],
    exports:[RoleListComponent]
})

export class RoleListModule {

}
