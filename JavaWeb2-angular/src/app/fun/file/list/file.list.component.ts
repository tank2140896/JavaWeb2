import {Component,OnInit} from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import {HttpClient,HttpHeaders} from '@angular/common/http';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {ResultPage} from '../../../model/ResultPage';
import {FileListRequest} from 'src/app/model/file/FileListRequest';
import {HeadToken} from 'src/app/model/HeadToken';

@Component({
  selector: 'app-web-file-list',
  templateUrl: './file.list.html',
  styleUrls: ['./file.list.scss'],
  providers:[]
})

export class FileListComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpClient:HttpClient,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){
    this.fileListZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_FILE_LIST,false));
    this.fileUploadZone = authService.canShow(ApiConstant.getPath(ApiConstant.SYS_FILE_UPLOAD_FILE,false));
  }

  /** 操作权限 start */
  fileListZone:boolean;//文件列表
  fileUploadZone:boolean;//上传文件
  /** 操作权限 end */

  private fileFormData:any = null;
  private fileListRequest:FileListRequest = new FileListRequest();//文件列表搜索条件
  private resultPage:ResultPage = new ResultPage();//分页结果初始化

  //初始化
  ngOnInit(): void {
    /** 若需修改分页大小或其它请求参数请注释后自行调整，这里使用默认值
    this.fileListRequest.currentPage = 1;
    this.fileListRequest.pageSize = 5;
    */
    this.fileListFunction(this.fileListRequest);//初始化文件列表
  }

  //搜索按钮
  public fileSearch(currentPage):void{
    this.resultPage = new ResultPage();//对每次搜索初始化分页
    this.fileListRequest.currentPage = currentPage;
    this.fileListFunction(this.fileListRequest);
  }

  //重置
  public reset():void{
    this.fileListRequest = new FileListRequest();
  }

  //文件列表
  public fileListFunction(fileListRequest:FileListRequest):void {
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_FILE_LIST,true),JSON.stringify(fileListRequest),this.sessionService.getHeadToken()).subscribe(
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

  //选择文件
  public fileSelect($event):void {
    const files = $event.target.files;
    const formData = new FormData();
    if(files!=null){
      for(let i=0;i<files.length;i++){
        formData.append('file',files[i]);
      }
    }
    this.fileFormData = formData;
  }

  //文件上传
  public fileUpload():void {
    if(this.fileFormData!=null){
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
      this.httpClient.post(ApiConstant.getPath(ApiConstant.SYS_FILE_UPLOAD_FILE,true),this.fileFormData,options).subscribe(
        {
          next:(result:any) => {
            //console.log(result);
            if(result.code==200){
              window.location.reload();
              alert('文件上传成功');
            }else{
              window.location.reload();
              alert('文件上传失败');
            }
          },
          error:e => {},
          complete:() => {}
        }
      );
    }else{
      alert('请选择所要上传的文件');
    }
  }

}
