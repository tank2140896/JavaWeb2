import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

import {HttpService} from "../../../service/http/HttpService";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {UserSearch} from "../../../models/user/user.search";
import {AuthService} from "../../../service/auth/AuthService";
import {RouteFullPath} from "../../../app.routes";

@Component({
    selector: 'user-manage-list',
    templateUrl: 'user-manage.list.html'
})

export class UserManageListComponent implements OnInit {

    listUserZone:any;//用户列表
    addUserZone:any;//用户新增
    deleteUserZone:any;//用户删除
    modifyUserZone:any;//用户修改
    detailUserZone:any;//用户详情

    constructor(private httpService:HttpService,
                private authService:AuthService,
                private router:Router){
        this.listUserZone = authService.canShow(HttpRequestUrl.SYS_USER_LSIT_SUFFIX);
        this.addUserZone = authService.canShow(HttpRequestUrl.SYS_USER_ADD_SUFFIX);
        this.deleteUserZone = authService.canShow(HttpRequestUrl.SYS_USER_DELETE_SUFFIX);
        this.modifyUserZone = authService.canShow(HttpRequestUrl.SYS_USER_MODIFY_SUFFIX);
        this.detailUserZone = authService.canShow(HttpRequestUrl.SYS_USER_DETAIL_SUFFIX);
    }

    private userSearch:UserSearch = new UserSearch();

    private listData:any = null;
    private currentPage:number;
    private totalPage:number;

    //初始化获取用户列表
    ngOnInit(): void {
        this.userSearch.currentPage = 1;
        this.userSearch.pageSize = 3;
        this.userSearchFunction(this.userSearch);
    }

    //搜索按钮
    public searchUser(currentPage):void{
        this.userSearch.currentPage = currentPage;
        this.userSearch.pageSize = 3;
        this.userSearchFunction(this.userSearch);
    }

    //用户搜索共通方法
    private userSearchFunction(userSearch:UserSearch):void {
        this.httpService.postJsonData(HttpRequestUrl.SYS_USER_LIST, JSON.stringify(userSearch)).subscribe(
            result=>{
                this.listData = result.data.data;
                this.currentPage = result.data.currentPage;
                this.totalPage = result.data.totalPage;
            }
        );
    }

    //新增用户
    public addUser():void{
        //this.router.navigate(['add'],{relativeTo: this.activatedRoute});
        this.router.navigate([RouteFullPath.UserManageAdd]);
    }

    //修改用户
    public modifyUser(userId):void{
        this.router.navigate([RouteFullPath.UserManageModify],{queryParams:{'userId':userId}});
    }

}