import {Component,OnInit} from '@angular/core';
import {Router,ActivatedRoute} from "@angular/router";

import {HttpService} from "../../../service/HttpService";
import {SessionService} from "../../../service/SessionService";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {UserModify} from "../../../models/user/user.modify";

@Component({
    selector: 'user-modify',
    templateUrl: './user.modify.html',
    styleUrls: ['./user.modify.scss']
})

export class UserModifyComponent implements OnInit {

    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private httpService:HttpService,
                private sessionService:SessionService){
        this.userId = activatedRoute.snapshot.queryParams['userId'];
    }

    //初始化
    ngOnInit(): void {
        this.userModify = new UserModify();
        this.detail();//获取用户详细信息
    }

    private userId:string;

    private userModify:UserModify;//用户修改请求参数

    //取消
    public cancel():void{
        this.router.navigate(['../list'],{relativeTo: this.activatedRoute});
    }

    //修改
    public modify():void{
        this.httpService.putJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_MODIFY,true),JSON.stringify(this.userModify),this.sessionService.getHeadToken()).subscribe(
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
    public detail():void{
        this.httpService.getJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_DETAIL+'/'+this.userId,true),this.sessionService.getHeadToken()).subscribe(
        result=>{
                if(result.code==200){
                    let data = result.data;
                    if(data!=null){
                        this.userModify.userId = data.userId;
                        this.userModify.userName = data.userName;
                        this.userModify.personName = data.personName;
                        this.userModify.email = data.email;
                        this.userModify.phone = data.phone;
                        this.userModify.remark = data.remark;
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
