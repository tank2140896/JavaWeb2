import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {InterfacesDetailResponse} from '../../../model/interfaces/InterfacesDetailResponse';

@Component({
  selector: 'app-web-interfaces-detail',
  templateUrl: './interfaces.detail.html',
  styleUrls: ['./interfaces.detail.scss'],
  providers:[]
})

export class InterfacesDetailComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private interfacesDetailResponse:InterfacesDetailResponse = new InterfacesDetailResponse();//接口详情

  //初始化
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(queryParam => {
      let id = queryParam.id;
      this.interfacesDetail(id);
    });
  }

  //接口详情
  public interfacesDetail(id:string):void{
    this.httpService.getJsonData(ApiConstant.getPath(ApiConstant.SYS_INTERFACES_DETAIL+'/'+id,true),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.interfacesDetailResponse.name = result.data.name;//接口名称
            this.interfacesDetailResponse.url = result.data.url;//URL
            this.interfacesDetailResponse.method = result.data.method;//方法名称
            this.interfacesDetailResponse.times = result.data.times;//时间
            this.interfacesDetailResponse.unit = result.data.unit;//单位
            this.interfacesDetailResponse.counts = result.data.counts;//次数
            this.interfacesDetailResponse.remark = result.data.remark;//备注
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
  public interfacesDetailBack():void{
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
