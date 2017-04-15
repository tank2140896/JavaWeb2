import {Injectable} from "@angular/core";
import {LoginSuccessData} from "../../models/login/login.success.data";

@Injectable()
export class SessionService{

    private loginSuccessData:LoginSuccessData;

    public setLoginSuccessData(loginSuccessData:LoginSuccessData):void{
        this.loginSuccessData = loginSuccessData;
    }

    public getLoginSuccessData():LoginSuccessData{
        return this.loginSuccessData;
    }

}
