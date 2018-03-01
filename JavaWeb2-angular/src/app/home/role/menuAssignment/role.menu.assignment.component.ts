import {Component,OnInit,ViewChild} from "@angular/core";
import {Router,ActivatedRoute} from "@angular/router";

import {HttpService} from "../../../service/HttpService";
import {SessionService} from "../../../service/SessionService";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {RoleMenuAssignmentRecursionComponent} from "./role.menu.assignment.recursion.component";

@Component({
    selector: 'role-menuAssignment',
    templateUrl: './role.menu.assignment.html',
    styleUrls: ['./role.menu.assignment.scss']
})

export class RoleMenuAssignmentComponent implements OnInit {

    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private httpService:HttpService,
                private sessionService:SessionService){
        this.roleId = activatedRoute.snapshot.queryParams['roleId'];
    }

    //初始化
    ngOnInit(): void {
        this.roleModuleInfo();
    }

    private roleId:string;
    private roleName:string;
    private roleModuleList:any;

    public roleModuleInfo():void{
        this.httpService.getJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_ROLE_MODULE_INFO+'/'+this.roleId,true),this.sessionService.getHeadToken()).subscribe(
            result=>{
                if(result.code==200){
                    let ret = result.data;
                    this.roleName = ret.role.roleName;
                    this.roleModuleList = ret.list;
                }else{
                    this.router.navigate(['login']);
                }
            },
            error=>{
                this.router.navigate(['login']);
            }
        );
    }

    //取消
    public cancel():void{
        this.router.navigate(['../list'],{relativeTo: this.activatedRoute});
    }

    //保存
    private emptyArray:string[] = [];
    public save():void{
        //console.log(this.roleModuleList);
        this.deepSearch(this.roleModuleList);
        this.httpService.postJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_ROLE_MODULE_ASSIGNMENT+'/'+this.roleId,true),JSON.stringify(this.emptyArray),this.sessionService.getHeadToken()).subscribe(
            result=>{
                if(result.code==200){
                    this.cancel();
                }else{
                    this.router.navigate(['login']);
                }
            },
            error=>{
                this.router.navigate(['login']);
            }
        );
    }

    private deepSearch(list:any):void{
        if(list!=null&&list.length!=0){
            for(let i=0;i<list.length;i++){
                let each = list[i];
                if(true==each.checkFlag){
                    this.emptyArray.push(each.moduleId);
                }
                if(each.list!=null&&each.list.length>0){
                    this.deepSearch(each.list);
                }
            }
        }
    }

}
