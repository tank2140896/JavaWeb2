import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";

import {MenuModifyComponent} from "./menu.modify.component";

@NgModule({
    imports:[CommonModule,RouterModule,FormsModule],
    declarations:[MenuModifyComponent],
    exports:[MenuModifyComponent]
})

export class MenuModifyModule {

}
