import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

import {MenuComponent} from "./menu.component";
import {MenuListModule} from "./list/menu.list.module";

@NgModule({
    imports:[CommonModule,RouterModule,MenuListModule],
    declarations:[MenuComponent],
    exports:[MenuComponent]
})

export class MenuModule{

}
