import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {RoleModuleAssignmentModuleRecursionComponent} from './role.roleModuleAssignment.module.recursion.component';

@Component({
  selector: 'app-web-role-role-module-assignment',
  templateUrl: './role.roleModuleAssignment.html',
  styleUrls: ['./role.roleModuleAssignment.scss'],
  providers:[]
})

export class RoleModuleAssignmentComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private needRoleId:string;

  private moduleTree:any;

  //引入子组件（若使用子组件中的静态变量则不需要引入）
  //@ViewChild('userModuleAssignmentModuleRecursionComponent',{static:false})
  //public userModuleAssignmentModuleRecursionComponent:UserModuleAssignmentModuleRecursionComponent;

  //初始化
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(queryParam => {
      let roleId = queryParam.roleId;
      this.needRoleId = roleId;
      this.roleModuleInfo();
    });
  }

  //获取角色模块信息
  public roleModuleInfo():void {
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_ROLE_MODULE_INFO+'/'+this.needRoleId,true),this.sessionService.getHeadToken()).subscribe(
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

  //角色模块分配
  public roleModuleAssignment():void{
    //console.log(UserModuleAssignmentModuleRecursionComponent.idList);
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_ROLE_MODULE_ASSIGNMENT+'/'+this.needRoleId,true),JSON.stringify(RoleModuleAssignmentModuleRecursionComponent.idList),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            alert('角色模块分配成功');
            RoleModuleAssignmentModuleRecursionComponent.idList = [];//分配成功清空子组件中静态变量的值
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
  public roleModuleAssignmentBack():void{
    RoleModuleAssignmentModuleRecursionComponent.idList = [];//点击返回清空子组件中静态变量的值
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
