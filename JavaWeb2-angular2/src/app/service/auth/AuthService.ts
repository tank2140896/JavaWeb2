import {Injectable} from "@angular/core";
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";

import {SessionService} from "../session/SessionService";
import {LoginSuccessData} from "../../models/login/login.success.data";
import {RouteFullPath} from "../../app.routes";

@Injectable()
export class AuthService implements CanActivate {

    constructor(private sessionService:SessionService){ }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        let loginSuccessData:LoginSuccessData = this.sessionService.getLoginSuccessData();
        if(loginSuccessData==null){
            return false;
        }
        let moduleList = loginSuccessData.getModuleList();
        if(moduleList==null/*||authOperateList.length==0*/){
            return false;
        }
        let url = state.url.split("?")[0];//获得页面请求的URL
        //console.log(url);
        if(url==RouteFullPath.Home){
            return true;
        }else{
            for(let i of moduleList){
                if(url==i.pageUrl){
                    return true;
                }
            }
        }
        return false;
    }

    canShow(apiUrl:string){
        //console.log(apiUrl);
        let loginSuccessData:LoginSuccessData = this.sessionService.getLoginSuccessData();
        if(loginSuccessData==null){
            return false;
        }
        let authOperateList = loginSuccessData.getAuthOperateList();
        if(authOperateList==null/*||authOperateList.length==0*/){
            return false;
        }
        for(let i of authOperateList){
            if(i.apiUrl==apiUrl){
                return true;
            }
        }
        return false;
    }

}