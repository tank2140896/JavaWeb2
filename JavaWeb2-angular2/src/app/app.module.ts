import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
import {FormsModule} from '@angular/forms';

import {routes} from './app.routes';

import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {HeaderComponent} from './home/header/header.component';
import {SidebarComponent} from './home/sidebar/sidebar.component';
import {CenteralAppComponent} from './home/centeral/centeral.app.component';
import {LoginComponent} from "./login/login.components";
import {UserListComponent} from "./home/centeral/sys_manager/user_manager/user.list.component";
import {RoleListComponent} from "./home/centeral/sys_manager/role_manager/role.list.component";
import {CenteralComponent} from "./home/centeral/centeral.component";

@NgModule({
    imports: [BrowserModule, HttpModule, FormsModule, routes],
    declarations: [
        AppComponent,
            LoginComponent,
            HomeComponent,
                HeaderComponent,
                SidebarComponent,
                CenteralAppComponent,
                    CenteralComponent,
                        UserListComponent,
                        RoleListComponent
    ],
    bootstrap: [AppComponent],
    providers: [ ]
})

export class AppModule { }