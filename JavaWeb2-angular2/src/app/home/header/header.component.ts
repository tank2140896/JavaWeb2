import {Component} from '@angular/core';
import {Router} from "@angular/router";

import {SessionService} from "../../service/session/SessionService";
import {HttpService} from "../../service/http/HttpService";
import {HttpRequestUrl} from "../../constant/HttpRequestUrl";
import {HeadToken} from "../../models/token/head.token";
import {LoginSuccessData} from "../../models/login/login.success.data";

@Component({
    selector: 'home-header',
    templateUrl: 'header.html',
    styleUrls: ['header.css']
})

export class HeaderComponent {

    constructor(private sessionService:SessionService,
                private router:Router,
                private httpService:HttpService){}

    logout(){
        let loginSuccessData:LoginSuccessData = this.sessionService.getLoginSuccessData();
        let headToken = new HeadToken();
        headToken.userId = loginSuccessData.getUser().userId;
        headToken.token = loginSuccessData.getToken();
        this.httpService.postJsonData(HttpRequestUrl.LOGOUT,null,headToken).subscribe(
            result=>{
                this.sessionService.setLoginSuccessData(null);
                this.router.navigate(['/']);
            }
        );
    }

}
