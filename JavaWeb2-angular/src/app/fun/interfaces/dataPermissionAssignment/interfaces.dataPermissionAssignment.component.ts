import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';

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

  private resultData = {userPermissionResponseList:[],rolePermissionResponseList:[]};
  private url:String;
  private id:string;

  //初始化
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(queryParam => {
      this.id = queryParam.id;
      this.url = queryParam.url;
      this.userRoleDataPermission(this.id);
    });
  }

  public userRoleDataPermission(id:string):void {
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_INTERFACES_USER_ROLE_DATA_PERMISSION+'/'+id,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            let ret = result.data;
            this.resultData = ret;
          }else{
            this.router.navigate(['webLogin']);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  public dataPermissionAssignment():void {
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_INTERFACES_DATA_PERMISSION_ASSIGNMENT+'/'+this.id,true),JSON.stringify(this.resultData),this.sessionService.getHeadToken()).subscribe(
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
