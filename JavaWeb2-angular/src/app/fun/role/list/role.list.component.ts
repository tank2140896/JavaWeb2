import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {ResultPage} from '../../../model/ResultPage';
import {RoleListRequest} from '../../../model/role/RoleListRequest';


@Component({
  selector: 'app-web-role-list',
  templateUrl: './role.list.html',
  styleUrls: ['./role.list.scss'],
  providers:[]
})

export class RoleListComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){
    this.roleListZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_ROLE_LIST,false));
    this.roleDeleteZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_ROLE_DELETE,false));
    this.roleAddZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_ROLE_ADD,false));
    this.roleModifyZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_ROLE_MODIFY,false));
    this.roleDetailZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_ROLE_DETAIL,false));
    this.roleModuleAssignmentZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_ROLE_MODULE_ASSIGNMENT,false));
  }

  /** 操作权限 start */
  roleListZone:boolean;//角色列表
  roleDeleteZone:boolean;//删除角色
  roleAddZone:boolean;//新增角色
  roleModifyZone:boolean;//修改角色
  roleDetailZone:boolean;//角色详情
  roleModuleAssignmentZone:boolean;//角色模块分配
  /** 操作权限 end */

  private roleListRequest:RoleListRequest = new RoleListRequest();//角色列表搜索条件
  private resultPage:ResultPage = new ResultPage();//分页结果初始化

  //初始化
  ngOnInit(): void {
    /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
    this.roleListRequest.currentPage = 1;
    this.roleListRequest.pageSize = 5;
    */
    this.roleListFunction(this.roleListRequest);//初始化用户列表
  }

  //搜索按钮
  public roleSearch(currentPage):void{
    this.resultPage = new ResultPage();//对每次搜索初始化分页
    this.roleListRequest.currentPage = currentPage;
    this.roleListFunction(this.roleListRequest);
  }

  //重置
  public reset():void{
    this.roleListRequest = new RoleListRequest();
  }

  //角色列表
  public roleListFunction(roleListRequest:RoleListRequest):void {
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_ROLE_LIST,true),JSON.stringify(roleListRequest),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            let ret = result.data;
            this.resultPage = new ResultPage(ret);
          }else{
            this.router.navigate(['webLogin']);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //新增角色
  public roleAddFunction():void {
    this.router.navigate(['add'],{relativeTo:this.activatedRoute});
  }

  //删除角色
  public roleDeleteFunction(roleId:string):void {
    this.httpService.deleteJsonData(ApiConstant.getPath(ApiConstant.SYS_ROLE_DELETE+'/'+roleId,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.roleSearch(1);
            alert('角色删除成功');
          }else{
            alert('角色删除失败');
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //角色详情
  public roleDetailFunction(roleId:string):void {
    this.router.navigate(['detail'],{relativeTo:this.activatedRoute,queryParams:{roleId:roleId}});
  }

  //修改角色
  public roleModifyFunction(roleId:string):void {
    this.router.navigate(['modify'],{relativeTo:this.activatedRoute,queryParams:{roleId:roleId}});
  }

  //角色模块分配
  public roleModuleAssignmentFunction(roleId:string):void {
    this.router.navigate(['roleModuleAssignment'],{relativeTo:this.activatedRoute,queryParams:{roleId:roleId}});
  }

}
