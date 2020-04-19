import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './AppRoutingModule';
import {AppComponent} from './AppComponent';
import {LoginModule} from './fun/login/login.module';
import {HttpService} from './service/HttpService';
import {HttpClientModule} from '@angular/common/http';
import {SessionService} from './service/SessionService';
import {AuthService} from './service/AuthService';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CommonModule} from '@angular/common';
import {HomeModule} from './fun/home/home.module';
import {FormsModule} from '@angular/forms';
import {DemoModule} from './fun/demo/demo.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    CommonModule,FormsModule,BrowserModule,BrowserAnimationsModule,HttpClientModule,
    AppRoutingModule,
    LoginModule,HomeModule,DemoModule
  ],
  providers: [HttpService,SessionService,AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
