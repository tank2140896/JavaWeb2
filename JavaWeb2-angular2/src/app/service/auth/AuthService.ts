import {Injectable} from "@angular/core";
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {HttpService} from "../http/HttpService";

@Injectable()
export class AuthService implements CanActivate {

    static v:Date = new Date();

    constructor(private httpService:HttpService){ }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        //TODO
        //let ret:boolean = true;
        //let x = this.httpService.getData('../../app/test.json').subscribe();
        //console.log(x);
        //return ret;
        //console.log(route.toString());
        console.log(state.url);
        console.log(AuthService.v);
        return true;
    }

    public getSeesion(parm:string):boolean{
        //console.log(parm)
        //从后端获得到当前权限下的所有URL对象集合
        let backendMap = {
            '/system/userManage/addUser': '/system/userManage/addUser'
        }
        return backendMap[parm]==undefined?false:true;
    }

}
