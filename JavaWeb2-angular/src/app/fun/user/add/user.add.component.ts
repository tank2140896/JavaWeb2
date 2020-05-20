import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {UserAddRequest} from '../../../model/user/UserAddRequest';
import {ApiConstant} from '../../../constant/ApiConstant';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {HeadToken} from '../../../model/HeadToken';

@Component({
  selector: 'app-web-user-add',
  templateUrl: './user.add.html',
  styleUrls: ['./user.add.scss'],
  providers:[]
})

export class UserAddComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpClient:HttpClient,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private userAddRequest:UserAddRequest = new UserAddRequest();//用户新增
  private userStateFromDictionaryData:any;
  private userPortraitFormData:any = null;

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
            this.userPortraitUpload(result.data);//上传用户头像
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
            this.userStateFromDictionaryData = result.data;
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

  //选择头像
  public portraitSelect($event):void {
    const file = $event.target.files[0];
    //console.log(file);
    const formData = new FormData();
    formData.append('userPortraitFile',file);
    this.userPortraitFormData = formData;
  }

  //上传头像
  public userPortraitUpload(data):void{
    if(this.userPortraitFormData!=null){
      /** 重新设置headers（不要设置Content-Type） start */
      let headToken:HeadToken = this.sessionService.getHeadToken();
      if(headToken==null){
        headToken = new HeadToken();
      }
      let headers:HttpHeaders = new HttpHeaders({
        'Access-Control-Allow-Headers':'Authorization',
        token:headToken.token//传入token
      });
      let options = {headers:headers,withCredentials:true};
      /** 重新设置headers（不要设置Content-Type） end */
      this.httpClient.post(ApiConstant.getPath(ApiConstant.SYS_USER_PORTRAIT_UPLOAD+'/'+data,true),this.userPortraitFormData,options).subscribe(
        {
          next:(result:any) => {
            //console.log(result);
            if(result.code==200){
              alert('新增用户成功');
              this.router.navigate(['../'],{relativeTo:this.activatedRoute});
            }else{
              alert('新增用户成功，头像上传失败');
            }
          },
          error:e => {},
          complete:() => {}
        }
      );
    }else{
      alert('新增用户成功');
      this.router.navigate(['../'],{relativeTo:this.activatedRoute});
    }
  }

}
