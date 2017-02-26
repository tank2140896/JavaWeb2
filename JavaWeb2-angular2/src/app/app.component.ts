import {Component} from '@angular/core';

import {LoginComponent} from './login/login.component';

@Component({
  selector: 'web-app',
  templateUrl: './login/login.html',
  styleUrls: ['./login/login.css']
})
export class AppComponent{ }

let lc = new LoginComponent();
lc.test();
