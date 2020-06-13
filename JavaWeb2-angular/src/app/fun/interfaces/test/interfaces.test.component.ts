import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {InterfacesTestRequest} from '../../../model/interfaces/InterfacesTestRequest';
import {ApiConstant} from '../../../constant/ApiConstant';

@Component({
  selector: 'app-web-interfaces-test',
  templateUrl: './interfaces.test.html',
  styleUrls: ['./interfaces.test.scss'],
  providers:[]
})

export class InterfacesTestComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private responseOut:string = null;
  private interfacesTestRequest:InterfacesTestRequest = new InterfacesTestRequest();//接口测试

  //初始化
  ngOnInit(): void {

  }

  //返回
  public interfacesTestBack():void {
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

  //提交
  public interfacesTest():void {
    //console.log(this.interfacesTestRequest);
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_INTERFACES_TEST,true),JSON.stringify(this.interfacesTestRequest),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            this.responseOut = result.data;
            alert('测试成功');
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

}
