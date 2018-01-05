import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";

import {HomeComponent} from "./home.component";
import {HeaderModule} from "./header/header.module";
import {SidebarModule} from "./sidebar/sidebar.module";
import {CenteralModule} from "./centeral/centeral.module";
import {UserManageModule} from "./user-manage/user-manage.module";
import {RouterModule} from "@angular/router";

@NgModule({
    imports:[CommonModule,RouterModule,HeaderModule,SidebarModule,CenteralModule,UserManageModule],
    declarations:[HomeComponent],
    exports:[HomeComponent]
})

export class HomeModule{

}
