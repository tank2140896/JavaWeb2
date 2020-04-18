import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {UserModifyComponent} from './user.modify.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[UserModifyComponent],
    exports:[UserModifyComponent]
})

export class UserModifyModule {

}
