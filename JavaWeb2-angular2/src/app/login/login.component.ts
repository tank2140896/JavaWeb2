import 'core-js';
import 'zone.js';

import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import {Component} from '@angular/core';
import {NgModule} from '@angular/core';
import {BrowserModule} from "@angular/platform-browser";

@NgModule({
    declarations: [LoginComponent],
    imports: [BrowserModule],
    bootstrap: [LoginComponent]
})

@Component({
    selector: 'web-app',
    templateUrl: 'app/login/login.html',
    styleUrls: ['app/login/login.css']
})
export class LoginComponent{}

platformBrowserDynamic().bootstrapModule(LoginComponent).catch(err => console.error(err));
