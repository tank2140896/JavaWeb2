import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {NgModule} from '@angular/core';

import {LoginComponent} from './login.components';

@NgModule({
  declarations:[LoginComponent],
  imports:[CommonModule,FormsModule],
  exports:[LoginComponent]
})

export class LoginModule{

}
