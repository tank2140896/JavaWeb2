import {Component, OnInit} from '@angular/core';
import {HttpService} from '../../service/HttpService';
import {SessionService} from '../../service/SessionService';
import {AuthService} from '../../service/AuthService';
import {Router} from '@angular/router';
import {ApiConstant} from '../../constant/ApiConstant';
import {CommonConstant} from '../../constant/CommonConstant';

@Component({
  selector: 'app-web-system-log',
  templateUrl: './system.log.html',
  styleUrls: ['./system.log.scss']
})

export class SystemLogComponent implements OnInit {

  constructor(public httpService:HttpService,
              public sessionService:SessionService,
              public authService:AuthService,
              public router:Router) {
  }

  private connectionStatus:number;
  private webSocket:WebSocket;
  private logMessage:string;

  ngOnInit() {
    this.connectionStatus = 0;
    this.logMessage = CommonConstant.EMPTY;
  }

  public listLogs():void{
    this.connectionStatus = 1;
    this.webSocketConnect();
  }

  //清空日志
  public clearLogs():void{
    this.logMessage = CommonConstant.EMPTY;
  }

  //断开日志
  public disconnectLogs():void{
    this.webSocket.close();
    this.connectionStatus = 0;
  }

  //websocket连接
  public webSocketConnect():void {
    if (this.webSocket != null) {
      this.webSocket.close();
    }
    this.webSocket = new WebSocket(ApiConstant.WEBSOCKET_SYSTEM_LOG_URL);
    //let that = this;

    this.webSocket.onopen = (event)=>{
      console.log('建立连接');
    };

    this.webSocket.onmessage = (messageEvent)=>{
      //console.log(messageEvent.data)
      this.logMessage += (messageEvent.data + '\n');
    };

    this.webSocket.onerror = (event)=>{
      console.log('发生错误');
    };

    this.webSocket.onclose = (closeEvent)=>{
      console.log('连接关闭');
    };
  }

}
