import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';

@Component({
  selector: 'app-web-system-log-list',
  templateUrl: './system.log.list.html',
  styleUrls: ['./system.log.list.scss'],
  providers:[]
})

export class SystemLogListComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){
    this.systemLogListZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_OPERATION_LOG_LIST,false));
  }

  /** 操作权限 start */
  systemLogListZone:boolean;//系统日志列表
  /** 操作权限 end */

  private connectionStatus:number = 0;

  //初始化
  ngOnInit(): void {
    //TODO
  }

  //查看日志
  public listLogs():void{
    this.connectionStatus = 1;
  }

  //清空日志
  public clearLogs():void{
    
  }

  //断开日志
  public disconnectLogs():void{
    
  }

  //暂停日志
  public stopLogs():void{
    
  }

}
