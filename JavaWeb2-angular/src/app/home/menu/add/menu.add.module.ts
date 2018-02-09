import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";

import {MenuAddComponent} from "./menu.add.component";

@NgModule({
    imports:[CommonModule,RouterModule,FormsModule],
    declarations:[MenuAddComponent],
    exports:[MenuAddComponent]
})

export class MenuAddModule {

}
