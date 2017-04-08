import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'web-app',
  //templateUrl: 'app/app.component.html'
  template: '<router-outlet></router-outlet>'
})

export class AppComponent implements OnInit {

  constructor(){ }

  ngOnInit(){ }

}