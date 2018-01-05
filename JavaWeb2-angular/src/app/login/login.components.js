"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var router_animations_1 = require("../router.animations");
var login_user_1 = require("../models/login/login.user");
var HttpRequestUrl_1 = require("../constant/HttpRequestUrl");
var LoginComponent = /** @class */ (function () {
    function LoginComponent(router, httpService, sessionService) {
        this.router = router;
        this.httpService = httpService;
        this.sessionService = sessionService;
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
        this.user = new login_user_1.LoginUser();
    }
    LoginComponent.prototype.ngOnInit = function () { };
    LoginComponent.prototype.login = function () {
        var _this = this;
        this.httpService.postJsonData(HttpRequestUrl_1.HttpRequestUrl.LOGIN, JSON.stringify(this.user), null).subscribe(function (result) {
            if (result.code == 200) {
                var data = result.data;
                _this.sessionService.setLoginSuccessData(JSON.stringify(data));
                _this.router.navigate(['home']);
            }
            else {
                alert('用户名或密码错误');
            }
        });
    };
    LoginComponent = __decorate([
        core_1.Component({
            selector: 'web-app',
            templateUrl: 'login.html',
            styleUrls: ['login.scss'],
            animations: [router_animations_1.routerTransition()]
        })
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.components.js.map