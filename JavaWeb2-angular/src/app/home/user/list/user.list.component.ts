import {Component,OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {NgbDatepickerI18n} from "@ng-bootstrap/ng-bootstrap";

import {HttpService} from "../../../service/HttpService";
import {AuthService} from "../../../service/AuthService";
import {SessionService} from "../../../service/SessionService";
import {UserList} from "../../../models/user/user.list";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {DateUtil} from "../../../util/DateUtil";
import {DatepickerI18nService} from "../../../service/DatepickerI18nService";

@Component({
    selector: 'user-list',
    templateUrl: './user.list.html',
    styleUrls: ['./user.list.scss'],
    providers:[{provide:NgbDatepickerI18n,useClass:DatepickerI18nService}]
    //providers:[I18n,{provide:NgbDatepickerI18n,useClass:DatepickerI18nService}]
})

export class UserListComponent implements OnInit {

    //listUserZone:any;//用户列表
    //addUserZone:any;//用户新增
    //deleteUserZone:any;//用户删除
    //modifyUserZone:any;//用户修改
    //detailUserZone:any;//用户详情

    constructor(private httpService:HttpService,
                private authService:AuthService,
                private router:Router,
                private sessionService:SessionService){
        //this.listUserZone = authService.canShow(HttpRequestUrl.SYS_USER_LSIT_SUFFIX);
        //this.addUserZone = authService.canShow(HttpRequestUrl.SYS_USER_ADD_SUFFIX);
        //this.deleteUserZone = authService.canShow(HttpRequestUrl.SYS_USER_DELETE_SUFFIX);
        //this.modifyUserZone = authService.canShow(HttpRequestUrl.SYS_USER_MODIFY_SUFFIX);
        //this.detailUserZone = authService.canShow(HttpRequestUrl.SYS_USER_DETAIL_SUFFIX);
    }

    private userList:UserList = new UserList();//用户列表搜索条件

    private data:any = "loading";
    private currentPage:number;
    private totalPage:number;
    private pageSize:number;
    private totalSize:number;
    private pageList:number;

    //初始化获取用户列表
    ngOnInit(): void {
        /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
        this.userList.currentPage = 1;
        this.userList.pageSize = 5;
        */
        this.userListFunction(this.userList);//初始化用户列表
    }

    //搜索按钮
    public userSearch(currentPage):void{
        this.data = "loading";
        this.userList.currentPage = currentPage;
        /** start 针对日期插件的特殊处理 */
        let createStartDate = this.userList.createStartDate;
        let createEndDate = this.userList.createEndDate;
        if(createStartDate!=null&&createStartDate!=''){
            this.userList.createStartDate = DateUtil.formatDate(createStartDate);
        }
        if(createEndDate!=null&&createEndDate!=''){
            this.userList.createEndDate = DateUtil.formatDate(createEndDate);
        }
        /** end 针对日期插件的特殊处理 */
        this.userListFunction(this.userList);
        /** start 针对日期插件的特殊处理 */
        this.userList.createStartDate = createStartDate;
        this.userList.createEndDate = createEndDate;
        /** end 针对日期插件的特殊处理 */
    }

    //重置
    public reset():void{
        this.userList = new UserList();
    }

    //用户搜索共通方法
    private userListFunction(userList:UserList):void {
        this.httpService.postJsonData(HttpRequestUrl.SYS_USER_LIST,JSON.stringify(userList),this.sessionService.getHeadToken()).subscribe(
            result=>{
                if(result.code==200){
                    let ret = result.data;
                    //console.log(ret);
                    this.data = ret.data;
                    this.currentPage = ret.currentPage;
                    this.totalPage = ret.totalPage;
                    this.totalSize = ret.totalSize;
                    this.pageSize = this.userList.pageSize;
                    this.pageList = ret.pageList;
                }else if(result.code==500){
                    this.data = null;
                }else{
                    this.router.navigate(['login']);
                }
            },
            error=>{
                this.data = null;
            }
        );
    }

    /**
    //新增用户
    public addUser():void{
        //this.router.navigate(['add'],{relativeTo: this.activatedRoute});
        this.router.navigate([RouteFullPath.UserManageAdd]);
    }

    //修改用户
    public modifyUser(userId):void{
        //this.router.navigate([RouteFullPath.UserManageModify],{queryParams:{'userId':{'a':10,'b':'30'}}});
        this.router.navigate([RouteFullPath.UserManageModify],{queryParams:{'userId':userId}});
    }
    */

}
