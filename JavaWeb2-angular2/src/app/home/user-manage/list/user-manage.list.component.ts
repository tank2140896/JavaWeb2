import {Component, OnInit} from '@angular/core';

import {HttpService} from "../../../service/http/HttpService";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {UserSearch} from "../../../models/user/user.search";
import {AuthService} from "../../../service/auth/AuthService";
import {HeadToken} from "../../../models/token/head.token";
import {SessionService} from "../../../service/session/SessionService";
import {LoginSuccessData} from "../../../models/login/login.success.data";

@Component({
    selector: 'user-manage-list',
    templateUrl: 'user-manage.list.html'
})

export class UserManageListComponent implements OnInit {

    addUserButton:any;//新增按钮
    constructor(private httpService:HttpService,
                private authService:AuthService,
                private sessionService:SessionService){
        this.addUserButton = authService.canShow(HttpRequestUrl.SYS_USER_LSIT_SUFFIX);
    }

    private userSearch:UserSearch = new UserSearch();

    private listData:any = null;
    private currentPage:number;
    private totalPage:number;

    ngOnInit(): void {
        let loginSuccessData:LoginSuccessData = this.sessionService.getLoginSuccessData();
        let headToken = new HeadToken();
        headToken.userId = loginSuccessData.getUser().userId;
        headToken.token = loginSuccessData.getToken();
        this.httpService.postJsonData(HttpRequestUrl.SYS_USER_LSIT,
                                      JSON.stringify(this.userSearch),
                                      headToken).subscribe(
            result=>{
                this.listData = result.data.data;
                this.currentPage = result.data.currentPage;
                this.totalPage = result.data.totalPage;
            }
        );
    }

}