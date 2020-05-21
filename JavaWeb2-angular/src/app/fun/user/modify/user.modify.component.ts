import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {UserModifyRequest} from '../../../model/user/UserModifyRequest';
import {CommonConstant} from '../../../constant/CommonConstant';
import {HeadToken} from '../../../model/HeadToken';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'app-web-user-modify',
  templateUrl: './user.modify.html',
  styleUrls: ['./user.modify.scss'],
  providers:[]
})

export class UserModifyComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpClient:HttpClient,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private userModifyRequest:UserModifyRequest = new UserModifyRequest();//用户修改
  private UserStateFromDictionaryData:any;
  private userPortraitFormData:any = null;
  private userPortraitSrc;

  //初始化
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(queryParam => {
      let userId = queryParam.userId;
      this.userModifyRequest.userId = userId;
      this.userDetail(userId);
      this.userPortraitSrc = ApiConstant.getPath(ApiConstant.SYS_USER_PORTRAIT+'?userId='+userId+'&token='+this.sessionService.getHeadToken().token+'&t='+new Date().getTime(),true);
    });
  }

  //用户详情
  public userDetail(userId:string):void{
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_USER_DETAIL+'/'+userId,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.userModifyRequest.userName = result.data.userName;//用户名
            this.userModifyRequest.personName = result.data.personName;//用户姓名
            this.userModifyRequest.email = result.data.email;//电子邮箱
            this.userModifyRequest.phone = result.data.phone;//手机号码
            this.userModifyRequest.status = result.data.status;//用户状态
            this.userModifyRequest.remark = result.data.remark;//备注
            this.getUserStateFromDictionary();
            //this.userPortrait(userId,result.data.portrait);
          }else{
            alert(result.message);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //修改用户
  public userModify():void{
    this.httpService.putJsonData(ApiConstant.getPath(ApiConstant.SYS_USER_MODIFY,true),JSON.stringify(this.userModifyRequest),this.sessionService.getHeadToken()).subscribe(
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
    this.userModifyRequest.status=$event.target.value;
  }

  //返回
  public userModifyBack():void{
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

  /**
  public userPortrait(userId:string,portrait:string):void {
    if(portrait==null||portrait==CommonConstant.EMPTY){
      let img = document.getElementById('img');
      // @ts-ignore
      img.src = '../../../../assets/image/user/portrait.jpg';
      img.onload = () => {
        // @ts-ignore
        URL.revokeObjectURL(img.src);
      }
    }else{
      Object.defineProperty(Image.prototype, 'authSrc', {
        writable : true,
        enumerable : true,
        configurable : true
      });
      let img = document.getElementById('img');
      //let url = img.getAttribute('authSrc');
      let request = new XMLHttpRequest();
      request.responseType = 'blob';
      request.open('get',ApiConstant.getPath(ApiConstant.SYS_USER_PORTRAIT+'/'+userId,true), true);
      request.setRequestHeader('token', this.sessionService.getHeadToken().token);
      request.onreadystatechange = e => {
        if (request.readyState == XMLHttpRequest.DONE && request.status == 200) {
          // @ts-ignore
          img.src = URL.createObjectURL(request.response);
          img.onload = () => {
            // @ts-ignore
            URL.revokeObjectURL(img.src);
          }
        }
      };
      request.send(null);
    }
  }
  */

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
              alert('修改用户成功');
              this.router.navigate(['../'],{relativeTo:this.activatedRoute});
            }else{
              alert('修改用户成功，头像上传失败');
            }
          },
          error:e => {},
          complete:() => {}
        }
      );
    }else{
      alert('修改用户成功');
      this.router.navigate(['../'],{relativeTo:this.activatedRoute});
    }
  }

}
