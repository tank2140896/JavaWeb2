import {Injectable} from '@angular/core';
import {HttpClient,HttpHeaders} from '@angular/common/http';
import {HeadToken} from '../model/HeadToken';
import {ApiConstant} from '../constant/ApiConstant';
import {DatePipe} from '@angular/common';

@Injectable()
export class HttpService {

  constructor(public httpClient:HttpClient,public datePipe: DatePipe){

  }

  public getOptions(headToken:HeadToken):any{
    if(headToken==null){
        headToken = new HeadToken();
    }
    let headers:HttpHeaders = new HttpHeaders({
        'Content-Type':'application/json',
        'Access-Control-Allow-Headers':'Authorization',
        'Api-Version':ApiConstant.API_VERSION,
        token:headToken.token,//传入token
        time:this.datePipe.transform(new Date(),'yyyyMMddHHmmss'),//传入时间
        //isAuth:headToken.isAuth//传入isAuth
    });
    let options = {headers:headers,withCredentials:true};
    return options;
  }

  public getJsonData(url:string,headToken:HeadToken){
    return this.httpClient.get(url,this.getOptions(headToken));
  }

  public postJsonData(url:string,body:any,headToken:HeadToken){
    return this.httpClient.post(url,body,this.getOptions(headToken));
  }

  public putJsonData(url:string,body:any,headToken:HeadToken){
    return this.httpClient.put(url,body,this.getOptions(headToken));
  }

  public deleteJsonData(url:string,headToken:HeadToken){
    return this.httpClient.delete(url,this.getOptions(headToken));
  }

  public getSingleJsonData(url:string){
    return this.httpClient.get(url);
  }

  public postSingleJsonData(url:string,body:any,options:any){
    return this.httpClient.post(url,body,options);
  }

  public putSingleJsonData(url:string,body:any,options:any){
    return this.httpClient.put(url,body,options);
  }

  public deleteSingleData(url:string){
    return this.httpClient.delete(url);
  }

}
