import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'web-app',
  //templateUrl: 'app/app.component.html'
  template: '<router-outlet></router-outlet>'//``可适用于多行
})

export class AppComponent implements OnInit {

  constructor(){

  }

  ngOnInit(){

  }

}
