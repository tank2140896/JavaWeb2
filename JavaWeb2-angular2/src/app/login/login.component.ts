import 'core-js';
import 'zone.js';

import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import {Component} from '@angular/core';
import {NgModule} from '@angular/core';
import {HttpModule} from '@angular/http';
import {BrowserModule} from "@angular/platform-browser";
//import {Component,NgModule} from '@angular/core';

@NgModule({
    declarations: [LoginComponent],
    imports: [BrowserModule,HttpModule],
    bootstrap: [LoginComponent]
})

@Component({
    selector: 'web-app',
    templateUrl: 'app/login/login.html',
    styleUrls: ['app/login/login.css']
})
export class LoginComponent{}

platformBrowserDynamic().bootstrapModule(LoginComponent).catch(err => console.error(err));
