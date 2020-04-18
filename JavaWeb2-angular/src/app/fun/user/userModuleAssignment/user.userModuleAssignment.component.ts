import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {UserModuleAssignmentModuleRecursionComponent} from './user.userModuleAssignment.module.recursion.component';

@Component({
  selector: 'app-web-user-user-module-assignment',
  templateUrl: './user.userModuleAssignment.html',
  styleUrls: ['./user.userModuleAssignment.scss'],
  providers:[]
})

export class UserModuleAssignmentComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private needUserId:string;

  private moduleTree:any;

  //引入子组件（若使用子组件中的静态变量则不需要引入）
  //@ViewChild('userModuleAssignmentModuleRecursionComponent',{static:false})
  //public userModuleAssignmentModuleRecursionComponent:UserModuleAssignmentModuleRecursionComponent;

  //初始化
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(queryParam => {
      let userId = queryParam.userId;
      this.needUserId = userId;
      this.userModuleInfo();
    });
  }

  //获取用户模块信息
  public userModuleInfo():void {
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_USER_MODULE_INFO+'/'+this.needUserId,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.moduleTree = result.data;
          }else{
            alert(result.message);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //用户模块分配
  public userModuleAssignment():void{
    //console.log(UserModuleAssignmentModuleRecursionComponent.idList);
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_USER_MODULE_ASSIGNMENT+'/'+this.needUserId,true),JSON.stringify(UserModuleAssignmentModuleRecursionComponent.idList),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            alert('用户模块分配成功');
            UserModuleAssignmentModuleRecursionComponent.idList = [];//分配成功清空子组件中静态变量的值
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

  //返回
  public userModuleAssignmentBack():void{
    UserModuleAssignmentModuleRecursionComponent.idList = [];//点击返回清空子组件中静态变量的值
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
