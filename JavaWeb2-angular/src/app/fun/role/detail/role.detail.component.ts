import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {RoleDetailResponse} from '../../../model/role/RoleDetailResponse';

@Component({
  selector: 'app-web-role-detail',
  templateUrl: './role.detail.html',
  styleUrls: ['./role.detail.scss'],
  providers:[]
})

export class RoleDetailComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private roleDetailResponse:RoleDetailResponse = new RoleDetailResponse();//角色详情

  //初始化
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(queryParam => {
      let roleId = queryParam.roleId;
      this.roleDetail(roleId);
    });
  }

  //角色详情
  public roleDetail(roleId:string):void{
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_ROLE_DETAIL+'/'+roleId,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.roleDetailResponse.roleName = result.data.roleName;//角色名称
            this.roleDetailResponse.roleCode = result.data.roleCode;//角色代码
            this.roleDetailResponse.remark = result.data.remark;//备注
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
  public roleDetailBack():void{
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
