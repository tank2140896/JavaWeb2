import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {ResultPage} from '../../../model/ResultPage';
import {DbTablesListRequest} from '../../../model/dbTables/DbTablesListRequest';
import {HttpClient, HttpHeaders} from '@angular/common/http';


@Component({
  selector: 'app-web-db-tables-list',
  templateUrl: './db.tables.list.html',
  styleUrls: ['./db.tables.list.scss'],
  providers:[]
})

export class DbTablesListComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              public httpClient:HttpClient,
              private authService:AuthService,
              private sessionService:SessionService){
    this.dbTablesListZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_DB_TABLES_LIST,false));
    this.dbTablesDetailZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_DB_TABLES_DETAIL,false));
    this.dbTablesCodeGenerateZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_DB_TABLES_CODE_GENERATE,false));
  }

  /** 操作权限 start */
  dbTablesListZone:boolean;//数据库表列表
  dbTablesDetailZone:boolean;//数据库表详情
  dbTablesCodeGenerateZone:boolean;//代码生成
  /** 操作权限 end */

  private dbTablesListRequest:DbTablesListRequest = new DbTablesListRequest();//数据库表列表搜索条件
  private resultPage:ResultPage = new ResultPage();//分页结果初始化

  //初始化
  ngOnInit(): void {
    /*若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
    this.dbTablesListRequest.currentPage = 1;
    this.dbTablesListRequest.pageSize = 5;
    */
    this.dbTablesListFunction(this.dbTablesListRequest);//初始化数据库表列表
  }

  //搜索按钮
  public dbTablesSearch(currentPage):void{
    this.resultPage = new ResultPage();//对每次搜索初始化分页
    this.dbTablesListRequest.currentPage = currentPage;
    this.dbTablesListFunction(this.dbTablesListRequest);
  }

  //重置
  public reset():void{
    this.dbTablesListRequest = new DbTablesListRequest();
  }

  //数据库表列表
  public dbTablesListFunction(dbTablesListRequest:DbTablesListRequest):void {
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_DB_TABLES_LIST,true),JSON.stringify(dbTablesListRequest),this.sessionService.getHeadToken()).subscribe(
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

  //数据库表详情
  public dbTablesDetailFunction(tableName:string):void{
    this.router.navigate(['detail'],{relativeTo:this.activatedRoute,queryParams:{tableName:tableName}});
  }

  //代码生成
  public dbTablesCodeGenerateFunction(tableName:string):void{
    let headers:HttpHeaders = new HttpHeaders({
      'Content-Type':'application/json',
      'Access-Control-Allow-Headers':'Authorization',
      token:this.sessionService.getHeadToken().token
    });
    let options:any = {headers:headers,withCredentials:true,responseType:'blob'};
    this.httpClient.get(ApiConstant.getPath(ApiConstant.SYS_DB_TABLES_CODE_GENERATE+'/'+tableName,true),options).subscribe(
        {
          next:(result:any) => {
            const blob = new Blob([result],{ type:'application/OCTET-STREAM;charset=UTF-8'});
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = tableName + '.zip';
            a.click();
            window.URL.revokeObjectURL(url);
          }
        }
    );
  }

}
