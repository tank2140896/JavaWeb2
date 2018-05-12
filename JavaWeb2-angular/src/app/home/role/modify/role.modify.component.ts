import {Component,OnInit} from '@angular/core';
import {Router,ActivatedRoute} from "@angular/router";

import {HttpService} from "../../../service/HttpService";
import {SessionService} from "../../../service/SessionService";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {RoleModify} from "../../../models/role/role.modify";

@Component({
    selector: 'role-modify',
    templateUrl: './role.modify.html',
    styleUrls: ['./role.modify.scss']
})

export class RoleModifyComponent implements OnInit {

    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private httpService:HttpService,
                private sessionService:SessionService){
        this.roleId = activatedRoute.snapshot.queryParams['roleId'];
    }

    //初始化
    ngOnInit(): void {
        this.roleModify = new RoleModify();
        this.detail();//获取角色详细信息
    }

    private roleId:string;

    private roleModify:RoleModify;//角色修改请求参数

    //取消
    public cancel():void{
        this.router.navigate(['../list'],{relativeTo: this.activatedRoute});
    }

    //修改
    public modify():void{
        this.httpService.putJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_ROLE_MODIFY,true),JSON.stringify(this.roleModify),this.sessionService.getHeadToken()).subscribe(
            result=>{
                let getResult = (<any>result);
                if(getResult.code==200){
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
    public detail():void{
        this.httpService.getJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_ROLE_DETAIL+'/'+this.roleId,true),this.sessionService.getHeadToken()).subscribe(
        result=>{
                let getResult = (<any>result);
                if(getResult.code==200){
                    let data = getResult.data;
                    if(data!=null){
                        this.roleModify.roleId = data.roleId;
                        this.roleModify.roleName = data.roleName;
                        this.roleModify.remark = data.remark;
                    }
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
