import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {ModuleListComponent} from './module.list.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
  declarations:[ModuleListComponent],
  exports:[ModuleListComponent]
})

export class ModuleListModule {

}
