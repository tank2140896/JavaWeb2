import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {RoleAddComponent} from './role.add.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[RoleAddComponent],
    exports:[RoleAddComponent]
})

export class RoleAddModule {

}
