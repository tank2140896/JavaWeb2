import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {OnlineUserComponent} from './online.user.component';

@NgModule({
  imports:[
    CommonModule,RouterModule,FormsModule
  ],
  declarations:[OnlineUserComponent],
  exports:[OnlineUserComponent]
})

export class OnlineUserModule{

}
