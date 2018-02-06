import {Component,OnInit} from '@angular/core';
import {Router,ActivatedRoute} from "@angular/router";

import {HttpService} from "../../../service/HttpService";
import {SessionService} from "../../../service/SessionService";
import {RoleAdd} from "../../../models/role/role.add";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";

@Component({
    selector: 'role-add',
    templateUrl: './role.add.html',
    styleUrls: ['./role.add.scss']
})

export class RoleAddComponent implements OnInit {

    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private httpService:HttpService,
                private sessionService:SessionService){

    }

    //初始化
    ngOnInit(): void {

    }

    private roleAdd:RoleAdd = new RoleAdd();//角色新增请求参数

    //重置
    public reset():void{
        this.roleAdd = new RoleAdd();
    }

    //取消
    public cancel():void{
        this.router.navigate(['../list'],{relativeTo: this.activatedRoute});
    }

    //保存
    public save():void{
        this.httpService.postJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_ROLE_ADD,true),JSON.stringify(this.roleAdd),this.sessionService.getHeadToken()).subscribe(
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
