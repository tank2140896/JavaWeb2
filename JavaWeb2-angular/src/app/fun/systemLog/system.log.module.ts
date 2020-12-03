import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import {SystemLogComponent} from './system.log.component';
import {SystemLogListModule} from './list/system.log.list.module';

@NgModule({
  imports:[
    CommonModule,RouterModule,
    SystemLogListModule
  ],
  declarations:[SystemLogComponent],
  exports:[SystemLogComponent]
})

export class SystemLogModule{

}
