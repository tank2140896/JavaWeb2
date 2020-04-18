import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {RoleModifyRequest} from '../../../model/role/RoleModifyRequest';
import {DictionaryModifyRequest} from '../../../model/dictionary/DictionaryModifyRequest';

@Component({
  selector: 'app-web-dictionary-modify',
  templateUrl: './dictionary.modify.html',
  styleUrls: ['./dictionary.modify.scss'],
  providers:[]
})

export class DictionaryModifyComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private dictionaryModifyRequest:DictionaryModifyRequest = new DictionaryModifyRequest();//字典修改

  //初始化
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(queryParam => {
      let dictionaryId = queryParam.dictionaryId;
      this.dictionaryModifyRequest.id = dictionaryId;
      this.dictionaryDetail(dictionaryId);
    });
  }

  //字典详情
  public dictionaryDetail(dictionaryId:string):void{
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_DICTIONARY_DETAIL+'/'+dictionaryId,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.dictionaryModifyRequest.dataType = result.data.dataType;//数据类型
            this.dictionaryModifyRequest.keyCode = result.data.keyCode;//key值
            this.dictionaryModifyRequest.valueCode = result.data.valueCode;//value值
            this.dictionaryModifyRequest.category = result.data.category;//分类
            this.dictionaryModifyRequest.means = result.data.means;//含义
            this.dictionaryModifyRequest.remark = result.data.remark;//备注
          }else{
            alert(result.message);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //修改字典
  public dictionaryModify():void{
    console.log(this.dictionaryModifyRequest);
    this.httpService.putJsonData(ApiConstant.getPath(ApiConstant.SYS_DICTIONARY_MODIFY,true),JSON.stringify(this.dictionaryModifyRequest),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            alert('修改字典成功');
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
  public dictionaryModifyBack():void{
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
