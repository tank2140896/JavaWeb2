import {Component,OnInit} from '@angular/core';
import {Router,ActivatedRoute} from "@angular/router";

import {HttpService} from "../../../service/HttpService";
import {SessionService} from "../../../service/SessionService";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {RoleDetail} from "../../../models/role/role.detail";

@Component({
    selector: 'role-detail',
    templateUrl: './role.detail.html',
    styleUrls: ['./role.detail.scss']
})

export class RoleDetailComponent implements OnInit {

    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private httpService:HttpService,
                private sessionService:SessionService){
        this.roleId = activatedRoute.snapshot.queryParams['roleId'];
    }

    //初始化
    ngOnInit(): void {
        this.roleDetail = new RoleDetail();
        this.detail();//详情
    }

    private roleId:string;
    private roleDetail:RoleDetail;//角色详情返回参数

    //返回
    public back():void{
        this.router.navigate(['../list'],{relativeTo: this.activatedRoute});
    }

    //详情
    public detail():void{
        this.httpService.getJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_ROLE_DETAIL+'/'+this.roleId,true),this.sessionService.getHeadToken()).subscribe(
            result=>{
                if(result.code==200){
                    let data = result.data;
                    if(data!=null){
                        this.roleDetail.roleName = data.roleName;
                        this.roleDetail.remark = data.remark;
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
