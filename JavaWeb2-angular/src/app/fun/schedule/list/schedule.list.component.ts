import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';

@Component({
  selector: 'app-web-schedule-list',
  templateUrl: './schedule.list.html',
  styleUrls: ['./schedule.list.scss'],
  providers:[]
})

export class ScheduleListComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){
    this.scheduleListZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_SCHEDULE_LIST,false));
    this.scheduleAddZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_SCHEDULE_ADD,false));
  }

  private currentYearMonth:string;
  private currentYear:number;
  private currentMonth:number;
  private dateList:any;
  private scheduleSetFlag = false;

  /** 操作权限 start */
  scheduleListZone:boolean;//日程列表
  scheduleAddZone:boolean;//新增日程
  /** 操作权限 end */


  //初始化
  ngOnInit(): void {
    this.initSchedule();
  }

  //初始化日程
  public initSchedule() {
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.GET_SERVE_TIME,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          let currentDate = new Date(result.data);
          let year = currentDate.getFullYear();
          let month = currentDate.getMonth() + 1;
          this.currentYear = year;
          this.currentMonth = month;
          this.scheduleListFunction(this.currentYear,this.currentMonth);
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //日程列表
  public scheduleListFunction(year,month):void {
    if(month>12){
      month = 1;
      this.currentMonth = month;
      year = year + 1;
      this.currentYear = year;
    }
    if(month<1){
      month = 12;
      year = year - 1;
      this.currentMonth = month;
      this.currentYear = year;
    }
    year = String(year);
    if(month<10){
      month = '0' + month;
    }else{
      month = String(month);
    }
    this.currentYearMonth = year + '年' + month + '月';
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_SCHEDULE_LIST,true),JSON.stringify({year:year,month:month}),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            let ret = result.data;
            this.dateList = ret;
          }else{
            this.router.navigate(['webLogin']);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //上个月
  public preMonth():void {
    this.currentMonth = this.currentMonth-1;
    this.scheduleListFunction(this.currentYear,this.currentMonth);
  }

  //下个月
  public nextMonth():void {
    this.currentMonth = this.currentMonth+1;
    this.scheduleListFunction(this.currentYear,this.currentMonth);
  }

  //改变日程类型
  public changeScheduleType(day):void {
    if(this.scheduleSetFlag){
      let currentScheduleType = day.scheduleType;
      if(currentScheduleType<4){
        day.scheduleType = day.scheduleType + 1;
      }else{
        day.scheduleType = 1;
      }
    }
  }

  //日程设定
  public scheduleSet():void{
    this.scheduleSetFlag = true;
  }

  //保存日程
  public scheduleSave():void{
    let sendYear = this.currentYear+'';
    let sendMonth = this.currentMonth<10?('0'+this.currentMonth):this.currentMonth;
    let data = {list:this.dateList,year:sendYear,month:sendMonth};
    //console.log(data);
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_SCHEDULE_ADD,true),JSON.stringify(data),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            alert('日程保存成功');
            this.scheduleCancel();
          }else{
            this.router.navigate(['webLogin']);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //取消
  public scheduleCancel():void{
    this.scheduleSetFlag = false;
    this.scheduleListFunction(this.currentYear,this.currentMonth);
  }

}
