import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {UserAddRequest} from '../../../model/user/UserAddRequest';
import {ApiConstant} from '../../../constant/ApiConstant';

@Component({
  selector: 'app-web-user-add',
  templateUrl: './user.add.html',
  styleUrls: ['./user.add.scss'],
  providers:[]
})

export class UserAddComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private userAddRequest:UserAddRequest = new UserAddRequest();//用户新增
  private UserStateFromDictionaryData:any;

  //初始化
  ngOnInit(): void {
    this.getUserStateFromDictionary();
  }

  //新增用户
  public userAdd():void{
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_USER_ADD,true),JSON.stringify(this.userAddRequest),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            alert('新增用户成功');
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

  //获取用户状态字典数据
  public getUserStateFromDictionary():void {
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.GET_DICTIONARY,true),JSON.stringify({dataType:'user_state'}),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.UserStateFromDictionaryData = result.data;
          }else{
            alert(result.message);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //下拉事件
  public selectChangeModule($event):void{
    this.userAddRequest.status=$event.target.value;
  }

  //返回
  public userAddBack():void{
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
