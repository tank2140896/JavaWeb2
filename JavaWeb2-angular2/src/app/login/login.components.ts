import {Component} from '@angular/core';
import {Router} from '@angular/router';

import {LoginUser} from '../models/login/login.user';
import {HttpService} from "../service/http/HttpService";
import {HttpRequestUrl} from "../constant/HttpRequestUrl";
import {LoginSuccessData} from "../models/login/login.success.data";

@Component({
    selector: 'web-app',
    templateUrl: 'login.html',
    styleUrls: ['login.css']
})

export class LoginComponent{

    constructor(private router:Router,private httpService:HttpService) { }

    /*------ 语言切换 start ------*/
    //初始化默认语言
    defaultLangue = {display:'中文',value:'CN'};
    //defaultLangue = {display:'请选择',value:'-1'};//默认空或请选择个人推荐这种写法

    //语言列表显示
    langues:any[] = [
        //{display:'请选择',value:'-1'},//默认空或请选择个人推荐这种写法
        {display:'中文',value:'CN'},
        {display:'英文',value:'EN'}
    ];

    //语言选择事件
    public langueChange(langueChoseEvent:any):void{
        let languesArray = this.langues;
        for(let i in languesArray){
            let each = languesArray[i];
            if(each.value==langueChoseEvent){
                console.log("你选择的语言为："+each.display);
                break;
            }
        }
    }
    /*------ 语言切换 end ------*/

    /*------ 验证码获取 start ------*/
    /**
    kaptcha = HttpRequestUrl.GET_KAPTCHA;

    public getKaptcha():void{
        this.kaptcha = HttpRequestUrl.GET_KAPTCHA+'?data='+new Date().getTime();
    }
    */
    /*------ 验证码获取 end ------*/

    /*------ 用户登录 start ------*/
    //用到[()]双向绑定需要初始化
    private user:LoginUser = new LoginUser();

    public login():void{
        this.httpService.postJsonData(HttpRequestUrl.LOGIN,JSON.stringify(this.user),null).subscribe(
            result=>{
                //console.log(result);
                let data = result.data;
                //let token = data.token;
                //let user = data.user;
                //let menuList = data.menuList;
                //let authOperateList = data.authOperateList;
                let loginSuccessData = new LoginSuccessData();
                loginSuccessData.setToken(data.token);
                loginSuccessData.setUser(data.user);
                loginSuccessData.setMenuList(data.menuList);
                loginSuccessData.setAuthOperateList(data.authOperateList);
                window.sessionStorage.setItem('loginSuccessData',JSON.stringify(loginSuccessData));
                this.router.navigate(['home']);
                //this.router.navigate(['home']/*,{queryParams:{'myKey':100}}*/);
                //<a [routerLink]="['home']" [queryParams]="{'myKey':100}" >Home</a>
            }
        );
    }
    /*------ 用户登录 end ------*/

}