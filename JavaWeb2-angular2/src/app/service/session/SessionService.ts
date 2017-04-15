import {Injectable} from "@angular/core";
import {LoginSuccessData} from "../../models/login/login.success.data";

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
        loginSuccessData.setMenuList(getData.menuList);
        loginSuccessData.setAuthOperateList(getData.authOperateList);
        return loginSuccessData;
    }

}
