import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule,HttpClient} from '@angular/common/http';
import {TranslateModule,TranslateLoader} from '@ngx-translate/core';
import {TranslateHttpLoader } from '@ngx-translate/http-loader';

import {AppRoutes} from './app.routes';
import {AppComponent} from './app.component';

import {AuthService} from "./service/auth/AuthService";
import {HttpService} from "./service/http/HttpService";

import {HomeModule} from "./home/home.module";
import {LoginModule} from "./login/login.module";
import {SessionService} from "./service/session/SessionService";

export function createTranslateLoader(http: HttpClient) {
    return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: createTranslateLoader,
                deps: [HttpClient]
            }
        }),
        AppRoutes,
        LoginModule,
        HomeModule
    ],
    declarations: [AppComponent],
    bootstrap: [AppComponent],
    providers: [AuthService,HttpService,SessionService]
})

export class AppModule { }
