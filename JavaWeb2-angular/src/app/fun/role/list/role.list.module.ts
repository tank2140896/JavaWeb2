import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {RoleListComponent} from './role.list.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
  declarations:[RoleListComponent],
  exports:[RoleListComponent]
})

export class RoleListModule {

}
