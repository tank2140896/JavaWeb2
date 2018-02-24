import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from "@angular/common/http";

import {AppRoutes} from './app.routes';
import {AppComponent} from './app.component';
import {LoginModule} from "./login/login.module";
import {HomeModule} from "./home/home.module";
import {DemoModule} from "./demo/demo.module";
import {HttpService} from "./service/HttpService";
import {SessionService} from "./service/SessionService";
import {AuthService} from "./service/AuthService";

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        AppRoutes,
        LoginModule,
        HomeModule,
        DemoModule
    ],
    declarations: [AppComponent],
    bootstrap: [AppComponent],
    providers: [HttpService,SessionService,AuthService]
})

export class AppModule { }
