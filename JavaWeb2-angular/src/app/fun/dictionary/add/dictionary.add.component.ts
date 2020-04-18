import {Component,OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {HttpService} from '../../../service/HttpService';
import {AuthService} from '../../../service/AuthService';
import {SessionService} from '../../../service/SessionService';
import {ApiConstant} from '../../../constant/ApiConstant';
import {DictionaryAddRequest} from '../../../model/dictionary/DictionaryAddRequest';

@Component({
  selector: 'app-web-dictionary-add',
  templateUrl: './dictionary.add.html',
  styleUrls: ['./dictionary.add.scss'],
  providers:[]
})

export class DictionaryAddComponent implements OnInit {

  constructor(private router:Router,
              private activatedRoute:ActivatedRoute,
              private httpService:HttpService,
              private authService:AuthService,
              private sessionService:SessionService){

  }

  private dictionaryAddRequest:DictionaryAddRequest = new DictionaryAddRequest();//字典新增

  //初始化
  ngOnInit(): void {

  }

  //新增字典
  public dictionaryAdd():void{
    this.httpService.postJsonData(ApiConstant.getPath(ApiConstant.SYS_DICTIONARY_ADD,true),JSON.stringify(this.dictionaryAddRequest),this.sessionService.getHeadToken()).subscribe(
      {
        next:(result:any) => {
          //console.log(result);
          if(result.code==200){
            alert('新增字典成功');
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
  public dictionaryAddBack():void{
    this.router.navigate(['../'],{relativeTo:this.activatedRoute});
  }

}
