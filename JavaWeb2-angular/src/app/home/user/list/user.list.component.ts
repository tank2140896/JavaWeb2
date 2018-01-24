import {Component,OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {NgbDatepickerI18n,NgbModal} from "@ng-bootstrap/ng-bootstrap";

import {HttpService} from "../../../service/HttpService";
import {AuthService} from "../../../service/AuthService";
import {SessionService} from "../../../service/SessionService";
import {UserList} from "../../../models/user/user.list";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {DateUtil} from "../../../util/DateUtil";
import {DatepickerI18nService} from "../../../service/DatepickerI18nService";
import {ResultPage} from "../../../models/result/result.page";

@Component({
    selector: 'user-list',
    templateUrl: './user.list.html',
    styleUrls: ['./user.list.scss'],
    providers:[/*I18n,*/{provide:NgbDatepickerI18n,useClass:DatepickerI18nService}]
})

export class UserListComponent implements OnInit {

    constructor(private router:Router,
                private ngbModal:NgbModal,
                private httpService:HttpService,
                private authService:AuthService,
                private sessionService:SessionService){
        //this.userListZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_LIST,false));
        this.userDeleteZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_DELETE,false));
    }

    /** 操作权限 start */
    //userListZone:boolean;//用户列表
    userDeleteZone:boolean;//用户删除
    /** 操作权限 end */

    private userList:UserList = new UserList();//用户列表搜索条件
    private resultPage:ResultPage = new ResultPage();//分页结果初始化

    //初始化
    ngOnInit(): void {
        /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
        this.userList.currentPage = 1;
        this.userList.pageSize = 5;
        */
        this.userListFunction(this.userList);//初始化用户列表
    }

    //搜索按钮
    public userSearch(currentPage):void{
        this.resultPage = new ResultPage();//对每次搜索进行初始化
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
        this.httpService.postJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_LIST,true),JSON.stringify(userList),this.sessionService.getHeadToken()).subscribe(
            result=>{
                if(result.code==200){
                    let ret = result.data;
                    //console.log(ret);
                    this.resultPage = new ResultPage(ret);
                }else{
                    this.router.navigate(['login']);
                }
            },
            error=>{
                this.router.navigate(['login']);
            }
        );
    }

    //删除用户
    public deleteUser(userId:string,content):void{
        this.ngbModal.open(content).result.then((result) => {
            if(result){
                alert(userId);//TODO 执行删除操作
            }
        }, (reason) => {
            //主要是ModalDismissReasons.ESC和ModalDismissReasons.BACKDROP_CLICK
        });

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
