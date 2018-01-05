import {Injectable} from "@angular/core";
import {LoginSuccessData} from "../../models/login/login.success.data";
import {HeadToken} from "../../models/token/head.token";

@Injectable()
export class SessionService{

    public setLoginSuccessData(loginSuccessData:string):void{
        window.sessionStorage.setItem('sessionData',loginSuccessData);
    }

    public getLoginSuccessData():LoginSuccessData{
        let getData:any = window.sessionStorage.getItem('sessionData');
        let loginSuccessData:LoginSuccessData = new LoginSuccessData();
        getData = JSON.parse(getData);
        loginSuccessData.setToken(getData.token);
        loginSuccessData.setUser(getData.user);
        loginSuccessData.setModuleList(getData.moduleList);
        loginSuccessData.setMenuList(getData.menuList);
        loginSuccessData.setAuthOperateList(getData.authOperateList);
        return loginSuccessData;
    }

    public getHeadToken():HeadToken{
        let loginSuccessData:LoginSuccessData = this.getLoginSuccessData();
        let headToken:HeadToken = new HeadToken();
        headToken.userId = loginSuccessData.getUser().userId;
        headToken.token = loginSuccessData.getToken();
        return headToken;
    }

}
