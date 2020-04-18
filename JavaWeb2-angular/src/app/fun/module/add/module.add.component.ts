import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {ModuleAddRequest} from '../../../model/module/ModuleAddRequest';
import {CommonConstant} from '../../../constant/CommonConstant';

@Component({
  selector: 'app-web-module-add',
  templateUrl: './module.add.html',
  styleUrls: ['./module.add.scss'],
  providers:[]
})

export class ModuleAddComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private moduleAddRequest:ModuleAddRequest = new ModuleAddRequest();//模块新增

  private moduleListModuleTypeFromDictionaryData:any;

  private moduleNameAndIdListData:any;

  //初始化
  ngOnInit(): void {
    this.getModuleTypeFromDictionary();
  }

  //获取模块类型字典数据
  public getModuleTypeFromDictionary():void {
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.GET_DICTIONARY,true),JSON.stringify({dataType:'module_type'}),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.moduleListModuleTypeFromDictionaryData = result.data;
          }else{
            alert(result.message);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //单选联动事件
  public radioChange($event,moduleType):void {
    //获得上级模块
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_MODULE_MODULE_ID_AND_NAME_LIST+'/'+moduleType,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.moduleNameAndIdListData = result.data;
            if(moduleType==1){
              this.moduleAddRequest.parentId = CommonConstant.EMPTY;
            }else{
              try {
                this.moduleAddRequest.parentId = this.moduleNameAndIdListData[0].moduleId;//第一个总是默认被选中的
              }catch (e) {
                this.moduleAddRequest.parentId = CommonConstant.EMPTY;
              }
            }
          }else{
            alert(result.message);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //下拉联动事件
  public selectChange($event):void {
    this.moduleAddRequest.parentId = $event.target.value;
  }

  //新增模块
  public moduleAdd():void{
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_MODULE_ADD,true),JSON.stringify(this.moduleAddRequest),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            alert('新增模块成功');
            this.router.navigate(['../'],{relativeTo:this.activatedRoute});
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

  //返回
  public moduleAddBack():void{
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
