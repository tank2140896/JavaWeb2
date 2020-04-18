import {Injectable} from '@angular/core';
import {HeadToken} from '../model/HeadToken';

@Injectable()
export class SessionService{

  public setSessionValueBykey(key:string,value:string):void{
    window.sessionStorage.setItem(key,value);
  }

  public getSessionValueByKey(key:string):string{
    return window.sessionStorage.getItem(key);
  }

  public clearSessionValueByKey(key:string):void{
    window.sessionStorage.removeItem(key);
  }

  public setTokenData(tokenData:string):void{
    this.setSessionValueBykey('tokenData',tokenData);
  }

  public getTokenData():any{
    let getData:string = this.getSessionValueByKey('tokenData');
    if(getData==null||getData==''){
      return null;
    }
    try {
      getData = JSON.parse(getData);
    }catch (e){
      return null;
    }
    return getData;
  }

  public clearTokenData():void{
    this.clearSessionValueByKey('tokenData');
  }

  public getHeadToken():HeadToken{
    let headToken:HeadToken = new HeadToken();
    let tokenData:any = this.getTokenData();
    if(tokenData!=null){
      try{
        headToken.token = tokenData.token;
      }catch (e){
        //do nothing
      }
    }
    return headToken;
  }

}
