import {Injectable} from '@angular/core';
import {HttpClient,HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Rx";

import {HeadToken} from "../../models/token/head.token";

@Injectable()
export class HttpService{

    constructor(private httpClient:HttpClient){ }

    getJsonData(url:string,headToken:HeadToken){
        let headers = new HttpHeaders();
        headers.set('Content-Type','application/json');
        if(headToken!=null){
            headers.set('userId',headToken.userId);
            headers.set('token',headToken.token);
        }
        let options = {headers:headers,withCredentials:true};
        return this.httpClient.get(url,options).map(data=>data).do(this.handleError);
    }

    postJsonData(url:string,body:any,headToken:HeadToken){
        let headers = new HttpHeaders();
        headers.set('Content-Type','application/json');
        if(headToken!=null){
            headers.set('userId',headToken.userId);
            headers.set('token',headToken.token);
        }
        let options = {headers:headers,withCredentials:true};
        return this.httpClient.post(url,body,options).map(data=>data).do(this.handleError);
    }

    getSingleJsonData(url:string){
        return this.httpClient.get(url).map(data=>data).do(this.handleError);
    }

    postSingleJsonData(url:string,body:any,options:any){
        return this.httpClient.post(url,body,options).map(data=>data).do(this.handleError);
    }

    private handleError(error:any) {
        //console.log(error);
        return Observable.throw(error);
    }

}
