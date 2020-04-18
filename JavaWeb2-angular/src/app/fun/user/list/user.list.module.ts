import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {UserListComponent} from './user.list.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[UserListComponent],
    exports:[UserListComponent]
})

export class UserListModule {

}
