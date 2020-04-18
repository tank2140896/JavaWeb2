import {Component,OnInit} from '@angular/core';
import {UserLoginRequest} from '../../model/user/UserLoginRequest';
import {CommonConstant} from '../../constant/CommonConstant';
import {HttpService} from '../../service/HttpService';
import {ApiConstant} from '../../constant/ApiConstant';
import {SessionService} from '../../service/SessionService';
import {Router} from '@angular/router';
import {HeadToken} from '../../model/HeadToken';

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
              public router:Router) {

  }

  ngOnInit() {
    this.userLoginRequest.type = '1';//0：admin；1：web；2：Android；3：IOS
  }

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
                error:e => {console.log(e);/*this.userLoginErrorMessage = e.message;*/},
                complete:() => {}
              }
            );
            /** 获得redis中的信息并进行路由跳转 end */
          }else{
            this.userLoginErrorMessage = result.message;
          }
        },
        error:e => {console.log(e);this.userLoginErrorMessage = e.message;},
        complete:() => {}
      }
    );
  }

}
