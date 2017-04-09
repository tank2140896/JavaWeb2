import {Injectable} from "@angular/core";
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";

@Injectable()
export class AuthService implements CanActivate {

    constructor(/*private httpService:HttpService*/){ }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        let loginSuccessData = window.sessionStorage.getItem('loginSuccessData');
        if(loginSuccessData==null){
            return false;
        }
        let authOperateList = JSON.parse(loginSuccessData).authOperateList;
        if(authOperateList==null||authOperateList.length==0){
            return false;
        }
        let url = state.url;//获得页面请求的URL
        if(url=='/home'){
            return true;
        }else{
            for(let i of authOperateList){
                if(url==("/home/"+i.pageUrl)){
                    return true;
                }
            }
        }
        return false;
    }

    canShow(apiUrl:string){
        let loginSuccessData = window.sessionStorage.getItem('loginSuccessData');
        if(loginSuccessData==null){
            return false;
        }
        let authOperateList = JSON.parse(loginSuccessData).authOperateList;
        if(authOperateList==null||authOperateList.length==0){
            return false;
        }
        //console.log(apiUrl);
        for(let i of authOperateList){
            if(i.apiUrl==apiUrl){
                return true;
            }
        }
        return false;
    }

}