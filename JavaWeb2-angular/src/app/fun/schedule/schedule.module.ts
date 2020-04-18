import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import {ScheduleComponent} from './schedule.component';
import {ScheduleListModule} from './list/schedule.list.module';

@NgModule({
  imports:[
    CommonModule,RouterModule,
    ScheduleListModule
  ],
  declarations:[ScheduleComponent],
  exports:[ScheduleComponent]
})

export class ScheduleModule{

}
