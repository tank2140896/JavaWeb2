import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {OnlineChatComponent} from './online.chat.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  imports:[
    CommonModule,RouterModule,FormsModule
  ],
  declarations:[OnlineChatComponent],
  exports:[OnlineChatComponent]
})

export class OnlineChatModule{

}
