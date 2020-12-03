import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {SystemLogListComponent} from './system.log.list.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
  declarations:[SystemLogListComponent],
  exports:[SystemLogListComponent]
})

export class SystemLogListModule {

}
