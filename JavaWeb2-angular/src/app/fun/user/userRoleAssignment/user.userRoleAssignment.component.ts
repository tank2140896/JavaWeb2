import {Component, inject, Input, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {UserRoleAssignmentRequest} from '../../../model/user/UserRoleAssignmentRequest';


@Component({
  selector: 'app-web-user-user-role-assignment',
  templateUrl: './user.userRoleAssignment.html',
  styleUrls: ['./user.userRoleAssignment.scss'],
  providers:[]
})

export class UserRoleAssignmentComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private needUserId:string;

  private userRoleInfoData:any;

  private userRoleStrategyFromDictionaryData:any;

  //初始化
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(queryParam => {
      let userId = queryParam.userId;
      this.needUserId = userId;
      this.userRoleInfo();
    });
  }

  //获取用户角色列表
  public userRoleInfo():void {
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_USER_ROLE_INFO+'/'+this.needUserId,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.userRoleInfoData = result.data;
            this.getStrategyFromDictionary();
          }else{
            alert(result.message);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //用户角色分配
  public userRoleAssignment():void {
    let array:Array<UserRoleAssignmentRequest> = new Array<UserRoleAssignmentRequest>();
    let _userRoleInfoData = this.userRoleInfoData;
    //console.log(_userRoleInfoData);
    for(let i=0;i<_userRoleInfoData.length;i++){
      if(_userRoleInfoData[i].checkFlag){
        let userRoleAssignmentRequest:UserRoleAssignmentRequest = new UserRoleAssignmentRequest();
        userRoleAssignmentRequest.roleId = _userRoleInfoData[i].roleId;
        userRoleAssignmentRequest.moduleStrategy = _userRoleInfoData[i].moduleStrategy;
        userRoleAssignmentRequest.dataStrategy = _userRoleInfoData[i].dataStrategy;
        array.push(userRoleAssignmentRequest);
      }
    }
    //console.log(JSON.stringify(array));
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_USER_ROLE_ASSIGNMENT+'/'+this.needUserId,true),JSON.stringify(array),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            alert('用户角色分配成功');
            this.router.navigate(['../'],{relativeTo:this.activatedRoute});
          }else{
            alert(result.message);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //获取策略字典数据
  public getStrategyFromDictionary():void {
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.GET_DICTIONARY,true),JSON.stringify({dataType:'user_role_strategy'}),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.userRoleStrategyFromDictionaryData = result.data;
          }else{
            alert(result.message);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //下拉联动
  public selectChangeModule($event,eachData:any):void {
    //console.log($event.target.value);
    eachData.moduleStrategy = $event.target.value;
  }
  public selectChangeData($event,eachData:any):void {
    //console.log($event.target.value);
    eachData.dataStrategy = $event.target.value;
  }

  //返回
  public userRoleAssignmentBack():void{
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
