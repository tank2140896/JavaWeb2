import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from "@angular/router";
import {NgbDatepickerI18n,NgbModal} from "@ng-bootstrap/ng-bootstrap";

import {HttpService} from "../../../service/HttpService";
import {AuthService} from "../../../service/AuthService";
import {SessionService} from "../../../service/SessionService";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {DateUtil} from "../../../util/DateUtil";
import {DatepickerI18nService} from "../../../service/DatepickerI18nService";
import {ResultPage} from "../../../models/result/result.page";
import {RoleList} from "../../../models/role/role.list";

@Component({
    selector: 'role-list',
    templateUrl: './role.list.html',
    styleUrls: ['./role.list.scss'],
    providers:[/*I18n,*/{provide:NgbDatepickerI18n,useClass:DatepickerI18nService}]
})

export class RoleListComponent implements OnInit {

    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private ngbModal:NgbModal,
                private httpService:HttpService,
                private authService:AuthService,
                private sessionService:SessionService){
        //this.roleListZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_ROLE_LIST,false));
        this.roleDeleteZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_ROLE_DELETE,false));
        this.roleAddZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_ROLE_ADD,false));
        this.roleModifyZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_ROLE_MODIFY,false));
        this.roleDetailZone = authService.canShow(HttpRequestUrl.getPath(HttpRequestUrl.SYS_ROLE_DETAIL,false));
    }

    /** 操作权限 start */
    //roleListZone:boolean;//角色列表
    roleDeleteZone:boolean;//删除角色
    roleAddZone:boolean;//新增角色
    roleModifyZone:boolean;//修改角色
    roleDetailZone:boolean;//角色详情
    /** 操作权限 end */

    private roleList:RoleList = new RoleList();//用户列表搜索条件
    private resultPage:ResultPage = new ResultPage();//分页结果初始化

    //初始化
    ngOnInit(): void {
        /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
        this.roleList.currentPage = 1;
        this.roleList.pageSize = 5;
        */
        this.roleListFunction(this.roleList);//初始化角色列表
    }

    //搜索按钮
    public roleSearch(currentPage):void{
        this.resultPage = new ResultPage();//对每次搜索进行初始化
        this.roleList.currentPage = currentPage;
        /** start 针对日期插件的特殊处理 */
        let createStartDate = this.roleList.createStartDate;
        let createEndDate = this.roleList.createEndDate;
        if(createStartDate!=null&&createStartDate!=''){
            this.roleList.createStartDate = DateUtil.formatDate(createStartDate);
        }
        if(createEndDate!=null&&createEndDate!=''){
            this.roleList.createEndDate = DateUtil.formatDate(createEndDate);
        }
        /** end 针对日期插件的特殊处理 */
        this.roleListFunction(this.roleList);
        /** start 针对日期插件的特殊处理 */
        this.roleList.createStartDate = createStartDate;
        this.roleList.createEndDate = createEndDate;
        /** end 针对日期插件的特殊处理 */
    }

    //重置
    public reset():void{
        this.roleList = new RoleList();
    }

    //角色搜索共通方法
    private roleListFunction(roleList:RoleList):void {
        this.httpService.postJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_ROLE_LIST,true),JSON.stringify(roleList),this.sessionService.getHeadToken()).subscribe(
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
    public deleteRole(roleId:string,content):void{
        this.ngbModal.open(content).result.then((result) => {
            if(result){
                this.httpService.deleteData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_ROLE_DELETE+'/'+roleId,true),this.sessionService.getHeadToken()).subscribe(
                    result=>{
                        //删除即使失败这里也暂不做任何处理
                        /** 删除成功重新刷新列表 */
                        this.resultPage = new ResultPage();
                        this.roleListFunction(this.roleList);
                    },
                    error=>{
                        this.router.navigate(['login']);
                    }
                );
            }
        }, (reason) => {
            //主要是ModalDismissReasons.ESC和ModalDismissReasons.BACKDROP_CLICK
        });
    }

    //新增角色
    public addRole():void{
        this.router.navigate(['../add'],{relativeTo:this.activatedRoute});
    }

    //修改角色
    public modifyRole(roleId):void{
        this.router.navigate(['../modify'],{relativeTo:this.activatedRoute,queryParams:{'roleId':roleId}});
    }

    //角色详情
    public roleDetail(roleId):void{
        this.router.navigate(['../detail'],{relativeTo:this.activatedRoute,queryParams:{'roleId':roleId}});
    }

    //角色模块分配
    public roleMenuAssignment(roleId):void{
        this.router.navigate(['../menuAssignment'],{relativeTo:this.activatedRoute,queryParams:{'roleId':roleId}});
    }

}
