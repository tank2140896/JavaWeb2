import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {RoleDetailComponent} from './role.detail.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[RoleDetailComponent],
    exports:[RoleDetailComponent]
})

export class RoleDetailModule {

}
