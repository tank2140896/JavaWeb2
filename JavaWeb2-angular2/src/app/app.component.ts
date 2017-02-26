import {Component} from '@angular/core';

@Component({
  selector: 'web-app',
  templateUrl: 'app/login.html',
  styleUrls: ['app/login.css']
})

export class AppComponent{

  public login():void{
    console.log(1);
  }
  
}
