import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
import {FormsModule} from '@angular/forms';
//import {APP_BASE_HREF} from '@angular/common';

import {AppComponent} from './app.component';

import {HomeComponent} from './home/home.component';
import {HeaderComponent} from './home/header/header.component';
import {SidebarComponent} from './home/sidebar/sidebar.component';
import {CenteralComponent} from './home/centeral/centeral.component';

@NgModule({
    imports: [BrowserModule, HttpModule, FormsModule],
    declarations: [AppComponent,HomeComponent,HeaderComponent,SidebarComponent,CenteralComponent],
    bootstrap: [AppComponent],
    providers: [/**{
        provide: APP_BASE_HREF,
        useValue: '<%=APP_BASE%>'
    }*/]
})

export class AppModule { }