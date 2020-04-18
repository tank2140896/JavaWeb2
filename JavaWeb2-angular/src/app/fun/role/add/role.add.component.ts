import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {UserAddRequest} from '../../../model/user/UserAddRequest';
import {ApiConstant} from '../../../constant/ApiConstant';
import {RoleAddRequest} from '../../../model/role/RoleAddRequest';

@Component({
  selector: 'app-web-role-add',
  templateUrl: './role.add.html',
  styleUrls: ['./role.add.scss'],
  providers:[]
})

export class RoleAddComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private roleAddRequest:RoleAddRequest = new RoleAddRequest();//角色新增

  //初始化
  ngOnInit(): void {

  }

  //新增角色
  public roleAdd():void{
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_ROLE_ADD,true),JSON.stringify(this.roleAddRequest),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            alert('新增角色成功');
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

  //返回
  public roleAddBack():void{
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
