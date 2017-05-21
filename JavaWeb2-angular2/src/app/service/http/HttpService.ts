import {Injectable} from '@angular/core';
import {Http, Response, RequestOptions, Headers} from "@angular/http";

import {Observable} from "rxjs/Rx";

@Injectable()
export class HttpService{

    constructor(private http:Http){ }

    getJsonData(url:string){
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let requestOptions = new RequestOptions({headers:headers,withCredentials:true});
        return this.http.get(url,requestOptions).map((response:Response)=>response.json()).catch(this.handleError);
    }

    postJsonData(url:string,body:string){
        let headers = new Headers();
        headers.append('Content-Type','application/json');
        let requestOptions = new RequestOptions({headers:headers,withCredentials:true});
        return this.http.post(url,body,requestOptions).map((response:Response)=>response.json()).catch(this.handleError);
    }

    getData(url:string){
        //this.httpService.getData(url).subscribe((data:Response)=>console.log(data.json()));
        return this.http.get(url).map((response:Response)=>response.json()).catch(this.handleError);
    }

    postData(url:string,body:string,requestOptions:RequestOptions){
        return this.http.post(url,body,requestOptions).map((response:Response)=>response.json()).catch(this.handleError);
    }

    private handleError(error:any) {
        //console.log(error);
        return Observable.throw(error);
    }

}