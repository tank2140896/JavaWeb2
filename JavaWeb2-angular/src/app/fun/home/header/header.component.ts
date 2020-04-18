import {Component,OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';
import {SessionService} from '../../../service/SessionService';
import {HttpService} from '../../../service/HttpService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-web-home-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  providers: [DatePipe]
})
export class HeaderComponent implements OnInit {

  private serveTime:string;
  private localTime:string;
  private userName:string;

  constructor(public sessionService:SessionService,
              public httpService:HttpService,
              public router:Router,
              public datePipe:DatePipe) {

  }

  ngOnInit() {
    this.getLocalTime();
    this.getServeTime();

    this.userName = this.sessionService.getTokenData().user.userName;
  }

  public getServeTime() {
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.GET_SERVE_TIME,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          let currentTime = result.data;
          let date:Date = new Date();
          date.setTime(new Date(currentTime).getTime()+1000);//网络及加载延迟，修正1秒
          setInterval(()=>{
            this.serveTime = this.datePipe.transform(date,'yyyy-MM-dd HH:mm:ss');
            date.setTime(date.getTime()+1000);
          },1000);
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  public getLocalTime() {
    let date:Date = new Date();
    setInterval(()=>{
      this.localTime = this.datePipe.transform(date,'yyyy-MM-dd HH:mm:ss');
      date.setTime(date.getTime()+1000);
    },1000);
  }

  public logout():void{
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.LOGOUT,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          this.router.navigate(['']);
          this.sessionService.clearTokenData();
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

}
