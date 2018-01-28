import {Component,OnInit} from '@angular/core';
import {Router,ActivatedRoute} from "@angular/router";

import {HttpService} from "../../../service/HttpService";
import {SessionService} from "../../../service/SessionService";
import {HttpRequestUrl} from "../../../constant/HttpRequestUrl";
import {UserDetail} from "../../../models/user/user.detail";

@Component({
    selector: 'user-detail',
    templateUrl: './user.detail.html',
    styleUrls: ['./user.detail.scss']
})

export class UserDetailComponent implements OnInit {

    constructor(private router:Router,
                private activatedRoute:ActivatedRoute,
                private httpService:HttpService,
                private sessionService:SessionService){
        this.userId = activatedRoute.snapshot.queryParams['userId'];
    }

    //初始化
    ngOnInit(): void {
        this.userDetail = new UserDetail();
        this.detail();//详情
    }

    private userId:string;
    private userDetail:UserDetail;//用户详情返回参数

    //返回
    public back():void{
        this.router.navigate(['../list'],{relativeTo: this.activatedRoute});
    }

    //详情
    public detail():void{
        this.httpService.getJsonData(HttpRequestUrl.getPath(HttpRequestUrl.SYS_USER_DETAIL+'/'+this.userId,true),this.sessionService.getHeadToken()).subscribe(
            result=>{
                if(result.code==200){
                    let data = result.data;
                    if(data!=null){
                        this.userDetail.userName = data.userName;
                        this.userDetail.personName = data.personName;
                        this.userDetail.email = data.email;
                        this.userDetail.phone = data.phone;
                        this.userDetail.remark = data.remark;
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
