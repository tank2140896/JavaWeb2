import {Component} from '@angular/core';
import {Router} from "@angular/router";

import {SessionService} from "../../service/session/SessionService";
import {HttpService} from "../../service/http/HttpService";
import {HttpRequestUrl} from "../../constant/HttpRequestUrl";

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
        let userId = this.sessionService.getLoginSuccessData().getUser().userId;
        this.httpService.getJsonData(HttpRequestUrl.LOGOUT+'/'+userId,null).subscribe(
            result=>{
                this.sessionService.setLoginSuccessData(null);
                this.router.navigate(['/']);
            }
        );
    }

}
