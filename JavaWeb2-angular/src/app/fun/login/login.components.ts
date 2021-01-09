import {Component,OnInit} from '@angular/core';
import {UserLoginRequest} from '../../model/user/UserLoginRequest';
import {CommonConstant} from '../../constant/CommonConstant';
import {HttpService} from '../../service/HttpService';
import {ApiConstant} from '../../constant/ApiConstant';
import {SessionService} from '../../service/SessionService';
import {Router} from '@angular/router';
import {HeadToken} from '../../model/HeadToken';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-web-login',
  templateUrl: './login.html',
  styleUrls: ['./login.scss']
})

export class LoginComponent implements OnInit {

  private userLoginRequest:UserLoginRequest = new UserLoginRequest();//用户登录参数封装，用到[()]双向绑定需要初始化
  private userLoginErrorMessage:string = CommonConstant.EMPTY;

  constructor(public httpService:HttpService,
              public sessionService:SessionService,
              public router:Router,
              public datePipe: DatePipe) {

  }

  ngOnInit() {

  }

  /**
  //简单登录密码加密（1、安装："js-base64": "3.6.0"；2、AppModule.ts中加入DatePipe；3、调用：this.datePipe.transform(new Date(),'yyyyMMddHHmmss')）
  public getPasswordEncode(passwod:string,time:string):string {
    console.log(passwod,time)
    let passwordEncod:string;
    let position = Number.parseInt(time)%2;
    if(position==0){//偶数
      passwordEncod = Base64.encode(passwod+time);
    }else{//奇数
      passwordEncod = Base64.encode(time+passwod);
    }
    let temArray:Array<any> = new Array(passwordEncod.length);
    for(let i=0;i<passwordEncod.length;i++){
      temArray[i] = passwordEncod.charAt(i);
    }
    let one,another,tmp;
    for(let i=0;i<time.length;){
      one = time.charAt(i++);
      another = time.charAt((i++));
      tmp = temArray[one];
      temArray[one] = temArray[another];
      temArray[another] = tmp;
    }
    passwordEncod = temArray.toString().replace(new RegExp( ',' , 'g' ),'');
    return passwordEncod;
  }
  */

  //回车事件
  public keydownEnter($event):void {
    //console.log($event);
    this.login();
  }

  //用户登录
  public login():void{
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.LOGIN,true),this.userLoginRequest,null).subscribe(
      {
        next:(result:any) => {
          if(result.code==200){
            let token = result.data;//获得token
            let headToken:HeadToken = new HeadToken();
            headToken.token = token;
            /** 获得redis中的信息并进行路由跳转 start */
            this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.GET_REDIS_TOKEN_DATA,true),headToken).subscribe(
              {
                next:(result:any) => {
                  if(result.code==200){
                    let ret = result.data;
                    this.sessionService.setTokenData(JSON.stringify(ret));
                    this.router.navigate(['web']);
                  }else{
                    this.userLoginErrorMessage = result.message;
                  }
                },
                error:e => {
                  //console.log(e);
                  //this.userLoginErrorMessage = e.message;
                },
                complete:() => {}
              }
            );
            /** 获得redis中的信息并进行路由跳转 end */
          }else{
            this.userLoginErrorMessage = result.message;
          }
        },
        error:e => {
          //console.log(e);
          //this.userLoginErrorMessage = e.message;
        },
        complete:() => {}
      }
    );
  }

  //示例
  public demo():void {
    this.router.navigate(['demo']);
  }

}
