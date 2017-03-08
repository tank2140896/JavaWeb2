import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
import {FormsModule} from '@angular/forms';
//import {APP_BASE_HREF} from '@angular/common';

import {AppComponent} from './app.component';

import {HomeComponent} from './home/home.component';

@NgModule({
    imports: [BrowserModule, HttpModule, FormsModule],
    declarations: [AppComponent,HomeComponent],
    bootstrap: [AppComponent],
    providers: [/**{
        provide: APP_BASE_HREF,
        useValue: '<%=APP_BASE%>'
    }*/]
})

export class AppModule { }