import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import {OperationLogComponent} from './operation.log.component';
import {OperationLogListModule} from './list/operation.log.list.module';

@NgModule({
  imports:[
    CommonModule,RouterModule,
    OperationLogListModule
  ],
  declarations:[OperationLogComponent],
  exports:[OperationLogComponent]
})

export class OperationLogModule{

}
