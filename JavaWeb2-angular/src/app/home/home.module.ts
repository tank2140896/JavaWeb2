import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NgbDropdownModule} from "@ng-bootstrap/ng-bootstrap";
import {RouterModule} from "@angular/router";

import {HomeComponent} from "./home.component";

import {HeaderModule} from "./header/header.module";
import {SidebarModule} from "./sidebar/sidebar.module";
import {CenteralModule} from "./centeral/centeral.module";
import {UserModule} from "./user/user.module";
import {RoleModule} from "./role/role.module";
import {MenuModule} from "./menu/menu.module";

@NgModule({
    imports:[
                CommonModule,RouterModule,NgbDropdownModule.forRoot(),
                HeaderModule,SidebarModule,CenteralModule,
                UserModule,RoleModule,MenuModule
    ],
    declarations:[HomeComponent],
    exports:[HomeComponent]
})

export class HomeModule {

}
