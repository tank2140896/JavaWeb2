import 'core-js';
import 'zone.js';

import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
import {APP_BASE_HREF} from '@angular/common';

import {AppComponent} from './app.component';

@NgModule({
    imports: [BrowserModule, HttpModule],
    declarations: [AppComponent],
    bootstrap: [AppComponent],
    providers: [{
        provide: APP_BASE_HREF,
        useValue: '<%=APP_BASE%>'
    }]
})

export class AppModule { }