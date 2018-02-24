import {Component,OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {routerTransition} from '../router.animations';
import {CommonConstant} from "../constant/common.constant";
import {UserLogin} from '../models/user/user.login';
import {HttpService} from "../service/HttpService";
import {HttpRequestUrl} from "../constant/HttpRequestUrl";
import {SessionService} from "../service/SessionService";
import {StringUtil} from "../util/StringUtil";

@Component({
    selector: 'app-login',
    templateUrl: './login.html',
    styleUrls: ['./login.scss'],
    animations: [routerTransition()]
})

export class LoginComponent implements OnInit {

    constructor(public router:Router,
                public httpService:HttpService,
                public sessionService:SessionService) {

    }

    ngOnInit() {
        this.getKaptcha();
    }

    imageUrl:string;//验证码图片请求URL
    userLogin:UserLogin = new UserLogin();//用户登录参数封装，用到[()]双向绑定需要初始化
    userLoginErrorMessage:string = CommonConstant.EMPTY;

    //获取验证码
    public getKaptcha():void{
        let getUuid:string = this.sessionService.getSessionValueByKey('kaptcha');
        if(getUuid==null||getUuid==''){
            let generateUuid = StringUtil.getUuid();
            this.userLogin.uuid = generateUuid;
            this.sessionService.setSessionValueBykey('kaptcha',generateUuid);
        }else{
            this.userLogin.uuid = getUuid;
        }
        this.imageUrl = HttpRequestUrl.getPath(HttpRequestUrl.KAPTCHA,true)+'/'+this.userLogin.uuid+'?time='+new Date();
    }

    //用户登录
    public login():void{
        this.httpService.postJsonData(HttpRequestUrl.getPath(HttpRequestUrl.LOGIN,true),this.userLogin,null).subscribe(
            result=>{
                if(result.code==200){
                    let ret = result.data;
                    //console.log(ret);
                    this.sessionService.setSessionData(JSON.stringify(ret));
                    this.router.navigate(['web']);
                }else{
                    this.userLoginErrorMessage = result.message;
                }
            },
        error=>{
                this.userLoginErrorMessage = error;
            }
        );
    }

}
