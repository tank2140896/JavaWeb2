import {HttpEvent,HttpHandler,HttpInterceptor,HttpRequest,HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';

@Injectable()
export class HttpServiceInterceptor implements HttpInterceptor {

  constructor(private router:Router) {

  }

  intercept(req:HttpRequest<any>,next:HttpHandler):Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      tap(event=>{
        //console.log(event);
        if(event instanceof HttpResponse && event.status == 200){//http正常响应
          let responseBody = event.body;
          if(responseBody.code != 200){//后端接口返回的code值
            alert(responseBody.message);
            this.router.navigate(['webLogin']);
          }
        }
      },error=>{
        alert(error);
        this.router.navigate(['webLogin']);
      })
    );
  }

}
