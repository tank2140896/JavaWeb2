import {Injectable, OnInit} from '@angular/core';
import {HttpClient,HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Rx";

import {HeadToken} from "../models/token/head.token";

@Injectable()
export class HttpService implements OnInit {

    ngOnInit(){

    }

    constructor(public httpClient:HttpClient){

    }

    getOptions(headToken:HeadToken):any{
        if(headToken==null){
            headToken = new HeadToken();
            headToken.userId = '';
            headToken.token = '';
            headToken.type = '1';
        }
        let headers:HttpHeaders = new HttpHeaders({
            'Content-Type':'application/json',
            'Access-Control-Allow-Headers':'Authorization',
            'userId':headToken.userId,
            'token':headToken.token,
            'type':headToken.type
        });
        let options = {'headers':headers,withCredentials:true};
        return options;
    }

    getJsonData(url:string,headToken:HeadToken){
        return this.httpClient.get(url,this.getOptions(headToken)).map(data=>data).do(this.handleError);
    }

    postJsonData(url:string,body:any,headToken:HeadToken){
        return this.httpClient.post(url,body,this.getOptions(headToken)).map(data=>data).do(this.handleError);
    }

    deleteData(url:string,headToken:HeadToken){
        return this.httpClient.delete(url,this.getOptions(headToken)).map(data=>data).do(this.handleError);
    }

    putJsonData(url:string,body:any,headToken:HeadToken){
        return this.httpClient.put(url,body,this.getOptions(headToken)).map(data=>data).do(this.handleError);
    }


    getSingleJsonData(url:string){
        return this.httpClient.get(url).map(data=>data).do(this.handleError);
    }

    postSingleJsonData(url:string,body:any,options:any){
        return this.httpClient.post(url,body,options).map(data=>data).do(this.handleError);
    }

    deleteSingleData(url:string){
        return this.httpClient.delete(url).map(data=>data).do(this.handleError);
    }

    putSingleJsonData(url:string,body:any,options:any){
        return this.httpClient.put(url,body,options).map(data=>data).do(this.handleError);
    }

    private handleError(error:any) {
        //console.log(error);
        return Observable.throw(error);
    }

}
