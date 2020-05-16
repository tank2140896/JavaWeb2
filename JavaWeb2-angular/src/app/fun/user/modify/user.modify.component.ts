import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {UserModifyRequest} from '../../../model/user/UserModifyRequest';

@Component({
  selector: 'app-web-user-modify',
  templateUrl: './user.modify.html',
  styleUrls: ['./user.modify.scss'],
  providers:[]
})

export class UserModifyComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private userModifyRequest:UserModifyRequest = new UserModifyRequest();//用户修改
  private UserStateFromDictionaryData:any;

  //初始化
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(queryParam => {
      let userId = queryParam.userId;
      this.userModifyRequest.userId = userId;
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
            this.userModifyRequest.userName = result.data.userName;//用户名
            this.userModifyRequest.personName = result.data.personName;//用户姓名
            this.userModifyRequest.email = result.data.email;//电子邮箱
            this.userModifyRequest.phone = result.data.phone;//手机号码
            this.userModifyRequest.status = result.data.status;//用户状态
            this.userModifyRequest.remark = result.data.remark;//备注
            this.getUserStateFromDictionary();
          }else{
            alert(result.message);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //修改用户
  public userModify():void{
    this.httpService.putJsonData(ApiConstant.getPath(ApiConstant.SYS_USER_MODIFY,true),JSON.stringify(this.userModifyRequest),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            alert('修改用户成功');
            this.router.navigate(['../'],{relativeTo:this.activatedRoute});
          }else{
            alert(result.message);
            //this.router.navigate(['webLogin']);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //获取用户状态字典数据
  public getUserStateFromDictionary():void {
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.GET_DICTIONARY,true),JSON.stringify({dataType:'user_state'}),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.UserStateFromDictionaryData = result.data;
          }else{
            alert(result.message);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //下拉事件
  public selectChangeModule($event):void{
    this.userModifyRequest.status=$event.target.value;
  }

  //返回
  public userModifyBack():void{
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
