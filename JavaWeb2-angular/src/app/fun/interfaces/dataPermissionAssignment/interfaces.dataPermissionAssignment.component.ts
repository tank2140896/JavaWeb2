import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {InterfacesUserRoleDataPermissionResponse} from '../../../model/interfaces/InterfacesUserRoleDataPermissionResponse';
import {ResultPage} from '../../../model/ResultPage';

@Component({
  selector: 'app-web-interfaces-data-permission-assignment',
  templateUrl: './interfaces.dataPermissionAssignment.html',
  styleUrls: ['./interfaces.dataPermissionAssignment.scss'],
  providers:[]
})

export class InterfacesDataPermissionAssignmentComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private interfacesUserRoleDataPermissionResponse_user:InterfacesUserRoleDataPermissionResponse = new InterfacesUserRoleDataPermissionResponse();
  private interfacesUserRoleDataPermissionResponse_role:InterfacesUserRoleDataPermissionResponse = new InterfacesUserRoleDataPermissionResponse();

  private resultPage_user:ResultPage = new ResultPage();//分页结果初始化;
  private resultPage_role:ResultPage = new ResultPage();//分页结果初始化;
  private url:String;
  private id:string;
  private entity:string;

  //初始化
  ngOnInit(): void {
    /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
    this.interfacesUserRoleDataPermissionResponse_user.currentPage = 1;
    this.interfacesUserRoleDataPermissionResponse_user.pageSize = 5;
    this.interfacesUserRoleDataPermissionResponse_role.currentPage = 1;
    this.interfacesUserRoleDataPermissionResponse_role.pageSize = 5;
    */
    this.activatedRoute.queryParams.subscribe(queryParam => {
      this.id = queryParam.id;
      this.url = queryParam.url;
      this.entity = queryParam.entity;
      this.interfacesUserRoleDataPermissionResponse_user.interfacesId = this.id;
      this.interfacesUserRoleDataPermissionResponse_user.type = 1;//用户
      this.interfacesUserRoleDataPermissionResponse_role.interfacesId = this.id;
      this.interfacesUserRoleDataPermissionResponse_role.type = 2;//角色
      this.userDataPermission(this.interfacesUserRoleDataPermissionResponse_user);
      this.roleDataPermission(this.interfacesUserRoleDataPermissionResponse_role);
    });
  }

  public userDataPermission(interfacesUserRoleDataPermissionResponse:InterfacesUserRoleDataPermissionResponse):void {
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_INTERFACES_USER_ROLE_DATA_PERMISSION,true),JSON.stringify(interfacesUserRoleDataPermissionResponse),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            let ret = result.data;
            this.resultPage_user = new ResultPage(ret);
          }else{
            this.router.navigate(['webLogin']);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  public roleDataPermission(interfacesUserRoleDataPermissionResponse:InterfacesUserRoleDataPermissionResponse):void {
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_INTERFACES_USER_ROLE_DATA_PERMISSION,true),JSON.stringify(interfacesUserRoleDataPermissionResponse),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            let ret = result.data;
            this.resultPage_role = new ResultPage(ret);
          }else{
            this.router.navigate(['webLogin']);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  public interfacesUserSearch(currentPage):void {
    this.interfacesUserRoleDataPermissionResponse_user.currentPage = currentPage;
    this.interfacesUserRoleDataPermissionResponse_user.type = 1;//用户
    this.userDataPermission(this.interfacesUserRoleDataPermissionResponse_user);
  }

  public interfacesRoleSearch(currentPage):void {
    this.interfacesUserRoleDataPermissionResponse_role.currentPage = currentPage;
    this.interfacesUserRoleDataPermissionResponse_role.type = 2;//角色
    this.roleDataPermission(this.interfacesUserRoleDataPermissionResponse_role);

  }

  //重置用户查询
  public resetUser():void {
    this.interfacesUserRoleDataPermissionResponse_user = new InterfacesUserRoleDataPermissionResponse();
    this.interfacesUserRoleDataPermissionResponse_user.interfacesId = this.id;
    this.interfacesUserRoleDataPermissionResponse_user.type = 1;//用户
  }

  //重置角色查询
  public resetRole():void {
    this.interfacesUserRoleDataPermissionResponse_role = new InterfacesUserRoleDataPermissionResponse();
    this.interfacesUserRoleDataPermissionResponse_role.interfacesId = this.id;
    this.interfacesUserRoleDataPermissionResponse_role.type = 2;//角色
  }

  public dataPermissionAssignment():void {
    //console.log(this.resultPage_user.data);
    //console.log(this.resultPage_role.data);
    let userRolePermissionRequest = {userPermissionResponseList:this.resultPage_user.data,rolePermissionResponseList:this.resultPage_role.data};
    //console.log(userRolePermissionRequest);
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_INTERFACES_DATA_PERMISSION_ASSIGNMENT+'/'+this.id,true),JSON.stringify(userRolePermissionRequest),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          if(result.code==200){
            alert('数据权限分配成功');
            this.dataPermissionAssignmentBack();
          }else{
            this.router.navigate(['webLogin']);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //返回
  public dataPermissionAssignmentBack():void{
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
