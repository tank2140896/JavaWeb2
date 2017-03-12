import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
import {FormsModule} from '@angular/forms';

import {routes} from './app.routes';

import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {HeaderComponent} from './home/header/header.component';
import {SidebarComponent} from './home/sidebar/sidebar.component';
import {CenteralComponent} from './home/centeral/centeral.component';
import {LoginComponent} from "./login/login.components";

@NgModule({
    imports: [BrowserModule, HttpModule, FormsModule, routes],
    declarations: [AppComponent,LoginComponent,HomeComponent,HeaderComponent,SidebarComponent,CenteralComponent],
    bootstrap: [AppComponent],
    providers: [ ]
})

export class AppModule { }