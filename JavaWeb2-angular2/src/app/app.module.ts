import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutes} from './app.routes';
import {AppComponent} from './app.component';

import {AuthService} from "./service/auth/AuthService";
import {HttpService} from "./service/http/HttpService";

import {HomeModule} from "./home/home.module";
import {LoginModule} from "./login/login.module";

@NgModule({
    imports: [BrowserModule, AppRoutes, LoginModule, HomeModule],
    declarations: [AppComponent],
    bootstrap: [AppComponent],
    providers: [AuthService,HttpService]
})

export class AppModule { }