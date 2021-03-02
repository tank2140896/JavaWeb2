import {Component, OnInit} from '@angular/core';
import {HttpService} from '../../service/HttpService';
import {SessionService} from '../../service/SessionService';
import {Router} from '@angular/router';
import {ApiConstant} from '../../constant/ApiConstant';
import {AuthService} from '../../service/AuthService';
import {ResultPage} from '../../model/ResultPage';
import {OnlineUserListRequest} from '../../model/onlineUser/OnlineUserListRequest';

@Component({
  selector: 'app-web-online-user',
  templateUrl: './online.user.html',
  styleUrls: ['./online.user.scss']
})

export class OnlineUserComponent implements OnInit {

  constructor(public httpService:HttpService,
              public sessionService:SessionService,
              public authService:AuthService,
              public router:Router) {
    this.onlineUserListZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_ONLINE_USER_LIST,false));
    this.onlineUserOfflineZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_ONLINE_USER_OFFLINE,false));
  }

  /** 操作权限 start */
  onlineUserListZone:boolean;//在线用户列表
  onlineUserOfflineZone:boolean;//用户下线
  /** 操作权限 end */

  private onlineUserListRequest:OnlineUserListRequest = new OnlineUserListRequest();//在线用户列表搜索条件
  private resultPage:ResultPage = new ResultPage();//分页结果初始化

  ngOnInit() {
    /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
     this.onlineUserListRequest.currentPage = 1;
     this.onlineUserListRequest.pageSize = 5;
     */
    this.onlineUserSearch(1);
  }

  //重置
  public reset():void{
    this.onlineUserListRequest = new OnlineUserListRequest();
  }

  //搜索按钮
  public onlineUserSearch(currentPage):void{
    this.resultPage = new ResultPage();//对每次搜索初始化分页
    this.onlineUserListRequest.currentPage = currentPage;
    this.onlineUserListFunction(this.onlineUserListRequest);
  }

  //获得在线用户列表
  public onlineUserListFunction(onlineUserListRequest:OnlineUserListRequest):void {
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_ONLINE_USER_LIST,true),JSON.stringify(onlineUserListRequest),this.sessionService.getHeadToken()).subscribe(
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

  //用户下线
  public onlineUserOffline(eachData):void {
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_ONLINE_USER_OFFLINE+'/'+eachData.userId+',1,1',true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            alert('用户['+eachData.userName+']成功下线');
            this.onlineUserSearch(1);
          }else{
            this.router.navigate(['webLogin']);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

}
