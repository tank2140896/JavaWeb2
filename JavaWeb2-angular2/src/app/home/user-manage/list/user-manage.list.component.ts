import {Component, OnInit} from '@angular/core';
import {HttpService} from "../../../service/http/HttpService";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {UserSearch} from "../../../models/user/user.search";

@Component({
    selector: 'user-manage-list',
    templateUrl: 'user-manage.list.html'
})

export class UserManageListComponent implements OnInit {

    constructor(private httpService:HttpService){ }

    private userSearch:UserSearch = new UserSearch();

    private listData:any = null;
    private currentPage:number;
    private totalPage:number;

    ngOnInit(): void {
        this.httpService.postJsonData(HttpRequestUrl.SYS_USER_LSIT,
                                      JSON.stringify(this.userSearch),
                                      JSON.parse(window.sessionStorage.getItem('loginSuccessData')).token).subscribe(
            result=>{
                this.listData = result.data.data;
                this.currentPage = result.data.currentPage;
                this.totalPage = result.data.totalPage;
            }
        );
    }

}