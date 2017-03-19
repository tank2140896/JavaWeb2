import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {Response, Headers, RequestOptions} from "@angular/http";

import {LoginUser} from '../models/login/login.user';
import {HttpService} from "../service/http/HttpService";
import {AuthService} from "../service/auth/AuthService";

@Component({
    selector: 'web-app',
    templateUrl: 'app/login/login.html',
    styleUrls: ['app/login/login.css'],
    providers: [HttpService]
})

export class LoginComponent{

    constructor(private router:Router,private httpService:HttpService,private authService:AuthService) { }

    ngOnInit(){

    }

    buttonAuth:AuthService = this.authService;

    uua = this.authService.getSeesion('wo');

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

    /*------ 用户登录 start ------*/
    //用到[()]双向绑定需要初始化
    private user:LoginUser = new LoginUser();

    public login():void{
        /**
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let options = new RequestOptions({headers:headers});
        let body = JSON.stringify('json_value');
        this.httpService.postData('url',body,options);
        */
        this.httpService.getData('app/test.json').subscribe(
            (data:any)=>console.log(data),
            (error:any)=>console.log(error)
        );
        console.log(this.user);
        this.router.navigate(['home']/*,{queryParams:{'myKey':100}}*/);//<a [routerLink]="['home']" [queryParams]="{'myKey':100}" >Home</a>
    }
    /*------ 用户登录 end ------*/

}
