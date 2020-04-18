import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {UserAddComponent} from './user.add.component';

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule],
    declarations:[UserAddComponent],
    exports:[UserAddComponent]
})

export class UserAddModule {

}
