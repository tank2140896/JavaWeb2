import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {ModuleModifyRequest} from '../../../model/module/ModuleModifyRequest';
import {CommonConstant} from '../../../constant/CommonConstant';

@Component({
  selector: 'app-web-module-modify',
  templateUrl: './module.modify.html',
  styleUrls: ['./module.modify.scss'],
  providers:[]
})

export class ModuleModifyComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private moduleModifyRequest:ModuleModifyRequest = new ModuleModifyRequest();//模块修改

  private moduleListModuleTypeFromDictionaryData:any;

  private moduleNameAndIdListData:any;

  //初始化
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(queryParam => {
      let moduleId = queryParam.moduleId;
      this.moduleModifyRequest.moduleId = moduleId;
      this.moduleDetail(moduleId);
    });
  }

  //模块详情
  public moduleDetail(moduleId:string):void{
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_MODULE_DETAIL+'/'+moduleId,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.getModuleTypeFromDictionary();
            this.moduleModifyRequest.moduleType = result.data.moduleType;//模块类型
            this.moduleModifyRequest.parentId = result.data.parentId;//上级模块ID
            this.moduleModifyRequest.moduleName = result.data.moduleName;//模块名
            this.moduleModifyRequest.alias = result.data.alias;//别名
            this.moduleModifyRequest.pageUrl = result.data.pageUrl;//页面URL
            this.moduleModifyRequest.apiUrl = result.data.apiUrl;//api的URL
            this.moduleModifyRequest.icon = result.data.icon;//图标
            this.moduleModifyRequest.orders = result.data.orders;//模块顺序
            this.moduleModifyRequest.remark = result.data.remark;//备注
            this.radioChange(null,this.moduleModifyRequest.moduleType);
          }else{
            alert(result.message);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
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
    this.moduleModifyRequest.moduleType = moduleType;
    //获得上级模块
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_MODULE_MODULE_ID_AND_NAME_LIST+'/'+moduleType,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.moduleNameAndIdListData = result.data;
            /** 这是新增时的处理，修改不需要
            if(moduleType==1){
              this.moduleModifyRequest.parentId = CommonConstant.EMPTY;
            }else{
              try {
                this.moduleModifyRequest.parentId = this.moduleNameAndIdListData[0].moduleId;//第一个总是默认被选中的
              }catch (e) {
                this.moduleModifyRequest.parentId = CommonConstant.EMPTY;
              }
            }
            */
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
    this.moduleModifyRequest.parentId = $event.target.value;
  }

  //修改模块
  public moduleModify():void{
    //console.log(this.moduleModifyRequest.parentId);
    this.httpService.putJsonData(ApiConstant.getPath(ApiConstant.SYS_MODULE_MODIFY,true),JSON.stringify(this.moduleModifyRequest),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            alert('修改模块成功');
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
  public moduleModifyBack():void{
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
