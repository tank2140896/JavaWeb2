import {Injectable} from "@angular/core";
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {HttpService} from "../http/HttpService";

@Injectable()
export class AuthService implements CanActivate {

    constructor(private httpService:HttpService){ }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        //TODO
        //let ret:boolean = true;
        //let x = this.httpService.getData('../../app/test.json').subscribe();
        //console.log(x);
        //return ret;
        //console.log(route.toString());
        console.log(state.url);
        return true;
    }

    private getSeesion():boolean{
        return false;
    }

}
