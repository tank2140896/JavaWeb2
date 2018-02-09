import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

import {MenuComponent} from "./menu.component";
import {MenuListModule} from "./list/menu.list.module";
import {MenuAddModule} from "./add/menu.add.module";

@NgModule({
    imports:[CommonModule,RouterModule,MenuListModule,MenuAddModule],
    declarations:[MenuComponent],
    exports:[MenuComponent]
})

export class MenuModule{

}
