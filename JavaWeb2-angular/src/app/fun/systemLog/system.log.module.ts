import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {SystemLogComponent} from './system.log.component';

@NgModule({
  imports:[
    CommonModule,RouterModule,FormsModule
  ],
  declarations:[SystemLogComponent],
  exports:[SystemLogComponent]
})

export class SystemLogModule{

}
