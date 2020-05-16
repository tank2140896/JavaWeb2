import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {UserListRequest} from '../../../model/user/UserListRequest';
import {ResultPage} from '../../../model/ResultPage';


@Component({
  selector: 'app-web-user-list',
  templateUrl: './user.list.html',
  styleUrls: ['./user.list.scss'],
  providers:[]
})

export class UserListComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){
    this.userListZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_USER_LIST,false));
    this.userDeleteZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_USER_DELETE,false));
    this.userAddZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_USER_ADD,false));
    this.userModifyZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_USER_MODIFY,false));
    this.userDetailZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_USER_DETAIL,false));
    this.userRoleAssignmentZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_USER_ROLE_ASSIGNMENT,false));
    this.userModuleAssignmentZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_USER_MODULE_ASSIGNMENT,false));
    this.userInitPasswordZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_USER_INIT_PASSWORD,false));
  }

  /** 操作权限 start */
  userListZone:boolean;//用户列表
  userDeleteZone:boolean;//删除用户
  userAddZone:boolean;//新增用户
  userModifyZone:boolean;//修改用户
  userDetailZone:boolean;//用户详情
  userRoleAssignmentZone:boolean;//用户角色分配
  userModuleAssignmentZone:boolean;//用户模块分配
  userInitPasswordZone:boolean;//初始化密码
  /** 操作权限 end */

  private userListRequest:UserListRequest = new UserListRequest();//用户列表搜索条件
  private resultPage:ResultPage = new ResultPage();//分页结果初始化

  //初始化
  ngOnInit(): void {
    /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
    this.userListRequest.currentPage = 1;
    this.userListRequest.pageSize = 5;
    */
    this.userListFunction(this.userListRequest);//初始化用户列表
  }

  //搜索按钮
  public userSearch(currentPage):void{
    this.resultPage = new ResultPage();//对每次搜索初始化分页
    this.userListRequest.currentPage = currentPage;
    this.userListFunction(this.userListRequest);
  }

  //重置
  public reset():void{
    this.userListRequest = new UserListRequest();
  }

  //用户列表
  public userListFunction(userListRequest:UserListRequest):void {
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_USER_LIST,true),JSON.stringify(userListRequest),this.sessionService.getHeadToken()).subscribe(
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

  //新增用户
  public userAddFunction():void {
    this.router.navigate(['add'],{relativeTo:this.activatedRoute});
  }

  //删除用户
  public userDeleteFunction(userId:string):void {
    this.httpService.deleteJsonData(ApiConstant.getPath(ApiConstant.SYS_USER_DELETE+'/'+userId,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.userSearch(1);
            alert('用户删除成功');
          }else{
            alert('用户删除失败');
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //用户详情
  public userDetailFunction(userId:string):void {
    this.router.navigate(['detail'],{relativeTo:this.activatedRoute,queryParams:{userId:userId}});
  }

  //修改用户
  public userModifyFunction(userId:string):void {
    this.router.navigate(['modify'],{relativeTo:this.activatedRoute,queryParams:{userId:userId}});
  }

  //用户角色分配
  public userRoleAssignmentFunction(userId:string):void {
    this.router.navigate(['userRoleAssignment'],{relativeTo:this.activatedRoute,queryParams:{userId:userId}});
  }

  //用户模块分配
  public userModuleAssignmentFunction(userId:string):void {
    this.router.navigate(['userModuleAssignment'],{relativeTo:this.activatedRoute,queryParams:{userId:userId}});
  }

  //初始化密码
  public userInitPasswordFunction(userId:string):void {
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_USER_INIT_PASSWORD+'/'+userId,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.userSearch(1);
            alert('初始化密码成功');
          }else{
            alert('初始化密码失败');
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

}
