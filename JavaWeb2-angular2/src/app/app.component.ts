import {Component} from '@angular/core';

@Component({
  selector: 'web-app',
  templateUrl: 'app/login/login.html',
  styleUrls: ['app/login/login.css']
})

export class AppComponent{

  public login():void{
    console.log(1);
  }

}
