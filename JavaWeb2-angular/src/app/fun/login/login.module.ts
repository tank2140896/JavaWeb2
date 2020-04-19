import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {LoginComponent} from './login.components';

@NgModule({
  declarations:[LoginComponent],
  imports:[CommonModule,FormsModule,RouterModule],
  exports:[LoginComponent]
})

export class LoginModule{

}
