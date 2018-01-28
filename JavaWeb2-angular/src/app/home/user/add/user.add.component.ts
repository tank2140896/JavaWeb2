import {Component,OnInit} from '@angular/core';
import {Router,ActivatedRoute} from "@angular/router";

import {HttpService} from "../../../service/HttpService";
import {SessionService} from "../../../service/SessionService";
import {UserAdd} from "../../../models/user/user.add";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";

@Component({
    selector: 'user-add',
    templateUrl: './user.add.html',
    styleUrls: ['./user.add.scss']
})

export class UserAddComponent implements OnInit {

    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private httpService:HttpService,
                private sessionService:SessionService){

    }

    //初始化
    ngOnInit(): void {

    }

    private userAdd:UserAdd = new UserAdd();//用户新增请求参数

    //重置
    public reset():void{
        this.userAdd = new UserAdd();
    }

    //取消
    public cancel():void{
        this.router.navigate(['../list'],{relativeTo: this.activatedRoute});
    }

    //保存
    public save():void{
        this.httpService.postJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_ADD,true),JSON.stringify(this.userAdd),this.sessionService.getHeadToken()).subscribe(
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
