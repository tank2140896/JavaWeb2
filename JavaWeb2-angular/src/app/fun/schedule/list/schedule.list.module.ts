import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {ScheduleListComponent} from './schedule.list.component';


@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[ScheduleListComponent],
    exports:[ScheduleListComponent]
})

export class ScheduleListModule {

}
