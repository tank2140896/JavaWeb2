import {Component,OnInit} from '@angular/core';
import {ActivatedRoute,Router} from "@angular/router";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {SessionService} from "../../../service/SessionService";
import {HttpService} from "../../../service/HttpService";

@Component({
    selector: 'user-roleAssignment',
    templateUrl: './user.role.assignment.html',
    styleUrls: ['./user.role.assignment.scss']
})

export class UserRoleAssignmentComponent implements OnInit {

    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private httpService:HttpService,
                private sessionService:SessionService){
        this.userId = activatedRoute.snapshot.queryParams['userId'];
    }

    //初始化
    ngOnInit(): void {
        this.userRoleInfo();//获取用户角色信息
    }

    private userId:string;
    private roleInfo:any;

    //获取用户角色信息
    public userRoleInfo():void{
        this.httpService.getJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_ROLE_INFO+'/'+this.userId,true),this.sessionService.getHeadToken()).subscribe(
            result=>{
                if(result.code==200){
                    this.roleInfo = result.data;
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
    public save():void{
        let postRoleInfo = this.roleInfo;
        var emptyArray:string[] = [];
        for(let i=0;i<postRoleInfo.length;i++){
            let each = postRoleInfo[i];
            if(true==each.checkFlag){
                emptyArray.push(each.roleId);
            }
        }
        this.httpService.postJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_ROLE_ASSIGNMENT+'/'+this.userId,true),JSON.stringify(emptyArray),this.sessionService.getHeadToken()).subscribe(
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

}
