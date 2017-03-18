import { Injectable } from '@angular/core';
import {Http, Response, RequestOptions} from "@angular/http";

import {Observable} from "rxjs/Rx";

@Injectable()
export class HttpService{

    constructor(private http:Http){ }

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
