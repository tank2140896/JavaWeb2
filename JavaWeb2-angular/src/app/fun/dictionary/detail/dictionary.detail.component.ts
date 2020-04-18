import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {RoleDetailResponse} from '../../../model/role/RoleDetailResponse';
import {DictionaryDetailResponse} from '../../../model/dictionary/DictionaryDetailResponse';

@Component({
  selector: 'app-web-dictionary-detail',
  templateUrl: './dictionary.detail.html',
  styleUrls: ['./dictionary.detail.scss'],
  providers:[]
})

export class DictionaryDetailComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private dictionaryDetailResponse:DictionaryDetailResponse = new DictionaryDetailResponse();//字典详情

  //初始化
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(queryParam => {
      let dictionaryId = queryParam.dictionaryId;
      this.dictionaryDetailResponse.id = dictionaryId;
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
            this.dictionaryDetailResponse.dataType = result.data.dataType;//数据类型
            this.dictionaryDetailResponse.keyCode = result.data.keyCode;//key值
            this.dictionaryDetailResponse.valueCode = result.data.valueCode;//value值
            this.dictionaryDetailResponse.category = result.data.category;//分类
            this.dictionaryDetailResponse.means = result.data.means;//含义
            this.dictionaryDetailResponse.remark = result.data.remark;//备注
          }else{
            alert(result.message);
          }
        },
        error:e => {},
        complete:() => {}
      }
    );
  }

  //返回
  public roleDetailBack():void{
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
