import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {ResultPage} from '../../../model/ResultPage';
import {OperationLogListRequest} from '../../../model/operationLog/OperationLogListRequest';

@Component({
  selector: 'app-web-operation-log-list',
  templateUrl: './operation.log.list.html',
  styleUrls: ['./operation.log.list.scss'],
  providers:[]
})

export class OperationLogListComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){
    this.operationLogListZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_OPERATION_LOG_LIST,false));
  }

  /** 操作权限 start */
  operationLogListZone:boolean;//操作日志列表
  /** 操作权限 end */

  private operationLogListRequest:OperationLogListRequest = new OperationLogListRequest();//操作日志列表搜索条件
  private resultPage:ResultPage = new ResultPage();//分页结果初始化

  //初始化
  ngOnInit(): void {
    /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
    this.operationLogListRequest.currentPage = 1;
    this.operationLogListRequest.pageSize = 5;
    */
    this.operationLogListFunction(this.operationLogListRequest);//初始化操作日志列表
  }

  //搜索按钮
  public operationLogSearch(currentPage):void{
    this.resultPage = new ResultPage();//对每次搜索初始化分页
    this.operationLogListRequest.currentPage = currentPage;
    this.operationLogListFunction(this.operationLogListRequest);
  }

  //重置
  public reset():void{
    this.operationLogListRequest = new OperationLogListRequest();
  }

  //操作日志列表
  public operationLogListFunction(operationLogListRequest:OperationLogListRequest):void {
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_OPERATION_LOG_LIST,true),JSON.stringify(operationLogListRequest),this.sessionService.getHeadToken()).subscribe(
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

}
