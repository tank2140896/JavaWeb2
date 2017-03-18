import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
import {FormsModule} from '@angular/forms';

import {routes} from './app.routes';

import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {HeaderComponent} from './home/header/header.component';
import {SidebarComponent} from './home/sidebar/sidebar.component';
import {LoginComponent} from "./login/login.components";
import {UserListComponent} from "./home/centeral/sys_manager/user_manager/list/user.list.component";
import {UserAddComponent} from "./home/centeral/sys_manager/user_manager/add/user.add.component";
import {RoleListComponent} from "./home/centeral/sys_manager/role_manager/list/role.list.component";
import {CenteralComponent} from "./home/centeral/centeral.component";
import {UserComponent} from "./home/centeral/sys_manager/user_manager/user.component";
import {RoleComponent} from "./home/centeral/sys_manager/role_manager/role.component";
import {AuthService} from "./service/auth/AuthService";
import {HttpService} from "./service/http/HttpService";

@NgModule({
    imports: [BrowserModule, HttpModule, FormsModule, routes],
    declarations: [
        AppComponent,
            LoginComponent,
            HomeComponent,
                HeaderComponent,
                SidebarComponent,
                CenteralComponent,
                    UserComponent,
                        UserListComponent,
                        UserAddComponent,
                    RoleComponent,
                        RoleListComponent
    ],
    bootstrap: [AppComponent],
    providers: [AuthService,HttpService]
})

export class AppModule { }