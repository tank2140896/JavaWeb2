import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {RoleModifyComponent} from './role.modify.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[RoleModifyComponent],
    exports:[RoleModifyComponent]
})

export class RoleModifyModule {

}
