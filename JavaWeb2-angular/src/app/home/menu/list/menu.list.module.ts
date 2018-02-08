import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";

import {MenuListComponent} from "./menu.list.component";

@NgModule({
    imports:[CommonModule,RouterModule,FormsModule,NgbModule.forRoot()],
    declarations:[MenuListComponent],
    exports:[MenuListComponent]
})

export class MenuListModule {

}
