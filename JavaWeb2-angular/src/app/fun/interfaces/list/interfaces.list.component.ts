import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {ResultPage} from '../../../model/ResultPage';
import {InterfacesListRequest} from '../../../model/interfaces/InterfacesListRequest';


@Component({
  selector: 'app-web-interfaces-list',
  templateUrl: './interfaces.list.html',
  styleUrls: ['./interfaces.list.scss'],
  providers:[]
})

export class InterfacesListComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){
    this.interfacesListZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_INTERFACES_LIST,false));
    this.interfacesModifyZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_INTERFACES_MODIFY,false));
    this.interfacesDetailZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_INTERFACES_DETAIL,false));
    this.interfacesUserRoleDataPermissionZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_INTERFACES_USER_ROLE_DATA_PERMISSION,false));
  }

  /** 操作权限 start */
  interfacesListZone:boolean;//接口列表
  interfacesModifyZone:boolean;//修改接口
  interfacesDetailZone:boolean;//接口详情
  interfacesUserRoleDataPermissionZone:boolean;//用户角色数据权限接口
  /** 操作权限 end */

  private interfacesListRequest:InterfacesListRequest = new InterfacesListRequest();//接口列表搜索条件
  private resultPage:ResultPage = new ResultPage();//分页结果初始化

  //初始化
  ngOnInit(): void {
    /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
    this.interfacesListRequest.currentPage = 1;
    this.interfacesListRequest.pageSize = 5;
    */
    this.interfacesListFunction(this.interfacesListRequest);//初始化接口列表
  }

  //搜索按钮
  public interfacesSearch(currentPage):void{
    this.resultPage = new ResultPage();//对每次搜索初始化分页
    this.interfacesListRequest.currentPage = currentPage;
    this.interfacesListFunction(this.interfacesListRequest);
  }

  //重置
  public reset():void{
    this.interfacesListRequest = new InterfacesListRequest();
  }

  //接口列表
  public interfacesListFunction(interfacesListRequest:InterfacesListRequest):void {
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_INTERFACES_LIST,true),JSON.stringify(interfacesListRequest),this.sessionService.getHeadToken()).subscribe(
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

  //接口详情
  public interfacesDetailFunction(id:string):void {
    this.router.navigate(['detail'],{relativeTo:this.activatedRoute,queryParams:{id:id}});
  }

  //修改接口
  public interfacesModifyFunction(id:string):void {
    this.router.navigate(['modify'],{relativeTo:this.activatedRoute,queryParams:{id:id}});
  }

  //数据权限分配
  public interfacesDataPermissionAssignmentFunction(id:string,url:string):void {
    this.router.navigate(['dataPermissionAssignment'],{relativeTo:this.activatedRoute,queryParams:{id:id,url:url}});
  }

}
