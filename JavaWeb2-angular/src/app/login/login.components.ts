import {Component,OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {routerTransition} from '../router.animations';

import {LoginUser} from '../models/login/login.user';
import {HttpService} from "../service/http/HttpService";
import {HttpRequestUrl} from "../constant/HttpRequestUrl";
import {SessionService} from "../service/session/SessionService";

@Component({
    selector: 'web-app',
    templateUrl: 'login.html',
    styleUrls: ['login.scss'],
    animations: [routerTransition()]
})

export class LoginComponent implements OnInit {

    constructor(private router:Router,
                private httpService:HttpService,
                private sessionService:SessionService) { }

    ngOnInit() {}

    /**
    <div>
        <span>语言</span>
        <!-- 要想用ngModel需要引入[import {FormsModule} from '@angular/forms';] -->
        <select [ngModel]="defaultLangue.value" (ngModelChange)="langueChange($event)">
        <option *ngFor="let langue of langues" [ngValue]="langue.value">{{langue.display}}</option>
        </select>
    </div>
    */

    /*------ 语言切换 start ------*/
    /**
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
    */
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
               if(result.code==200){
                   let data = result.data;
                   this.sessionService.setLoginSuccessData(JSON.stringify(data));
                   this.router.navigate(['home']);
               }else{
                   alert('用户名或密码错误');
               }
            }
        );
    }
    /*------ 用户登录 end ------*/

}
