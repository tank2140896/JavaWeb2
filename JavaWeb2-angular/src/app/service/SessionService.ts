import {Injectable} from "@angular/core";

import {HeadToken} from "../models/token/head.token";

@Injectable()
export class SessionService{

    public setSessionData(sessionData:string):void{
        window.sessionStorage.setItem('sessionData',sessionData);
    }

    public getSessionData():any{
        let getData:any = window.sessionStorage.getItem('sessionData');
        if(getData==null||getData==''){
            return null;
        }
        getData = JSON.parse(getData);
        return getData;
    }

    public clearSessionData():void{
        window.sessionStorage.removeItem('sessionData');
    }

    public getHeadToken():HeadToken{
        let sessionData:any = this.getSessionData();
        let headToken:HeadToken = new HeadToken();
        headToken.userId = sessionData.user.userId;
        headToken.token = sessionData.token;
        headToken.type = sessionData.type;
        return headToken;
    }

}
