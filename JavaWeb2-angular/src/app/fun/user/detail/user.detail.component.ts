import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {UserDetailResponse} from '../../../model/user/UserDetailResponse';

@Component({
  selector: 'app-web-user-detail',
  templateUrl: './user.detail.html',
  styleUrls: ['./user.detail.scss'],
  providers:[]
})

export class UserDetailComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private userDetailResponse:UserDetailResponse = new UserDetailResponse();//用户详情

  //初始化
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(queryParam => {
      let userId = queryParam.userId;
      this.userDetail(userId);
    });
  }

  //用户详情
  public userDetail(userId:string):void{
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_USER_DETAIL+'/'+userId,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.userDetailResponse.userName = result.data.userName;//用户名
            this.userDetailResponse.personName = result.data.personName;//用户姓名
            this.userDetailResponse.email = result.data.email;//电子邮箱
            this.userDetailResponse.phone = result.data.phone;//手机号码
            this.userDetailResponse.remark = result.data.remark;//备注
          }else{
            alert(result.message);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //返回
  public userDetailBack():void{
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
