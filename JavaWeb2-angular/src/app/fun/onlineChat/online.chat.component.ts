import {Component, OnInit} from '@angular/core';
import {HttpService} from '../../service/HttpService';
import {SessionService} from '../../service/SessionService';
import {Router} from '@angular/router';
import {ApiConstant} from '../../constant/ApiConstant';
import {CommonConstant} from '../../constant/CommonConstant';
import {AuthService} from '../../service/AuthService';

@Component({
  selector: 'app-web-online-chat',
  templateUrl: './online.chat.html',
  styleUrls: ['./online.chat.scss']
})

export class OnlineChatComponent implements OnInit {

  constructor(public httpService:HttpService,
              public sessionService:SessionService,
              public authService:AuthService,
              public router:Router) {
    this.userOfflineZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_ONLINE_CHAT_USER_OFFLINE,false));
  }

  /** 操作权限 start */
  userOfflineZone:boolean;//用户下线
  /** 操作权限 end */

  private webSocket:WebSocket;
  private sendMessage:string;
  private getMessage:string  = CommonConstant.EMPTY;
  private userName:string;
  private onLineUserList:any;

  ngOnInit() {
    this.webSocketConnect();
    this.userName = this.sessionService.getTokenData().user.userName;
    this.onlineUseList();
  }

  //websocket连接
  public webSocketConnect():void {
    if (this.webSocket != null) {
      this.webSocket.close();
    }
    this.webSocket = new WebSocket(ApiConstant.WEBSOCKET_REQUEST_URL);
    //let that = this;

    this.webSocket.onopen = (event)=>{
      console.log('建立连接');
    };

    this.webSocket.onmessage = (messageEvent)=>{
      const data = JSON.parse(messageEvent.data);
      if(data.userName!=this.userName){
        this.getMessage += ('['+data.userName+']' + data.message + '\n');
      }
    };

    this.webSocket.onerror = (event)=>{
      console.log('发生错误');
    };

    this.webSocket.onclose = (closeEvent)=>{
      console.log('发生错误');
    };
  }

  //发送消息
  public sendFunction():void {
    const sendInfo = {userName:this.userName,message:this.sendMessage};
    this.webSocket.send(JSON.stringify(sendInfo));
    this.getMessage += ('[我]' + this.sendMessage + '\n');
    this.sendMessage = CommonConstant.EMPTY;
  }

  //清空消息
  public clearFunction():void {
    this.getMessage = CommonConstant.EMPTY;
  }

  //回车事件
  public keydownEnter($event):void {
    //console.log($event);
    this.sendFunction();
  }

  //获得在线用户列表
  public onlineUseList():void {
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_ONLINE_CHAT_USER_LIST,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            let ret = result.data;
            this.onLineUserList = ret;
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
  public userOffline(eachData):void {
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_ONLINE_CHAT_USER_OFFLINE+'/'+eachData.userId+',1',true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            alert('用户['+eachData.userName+']成功下线');
            this.onlineUseList();
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
