import {Injectable} from '@angular/core';
import {CanActivate,ActivatedRouteSnapshot,RouterStateSnapshot} from '@angular/router';
import {SessionService} from './SessionService';

@Injectable()
export class AuthService implements CanActivate {

  constructor(public sessionService:SessionService){

  }

  /** URL路径权限判断 */
  public canActivate(route: ActivatedRouteSnapshot,state: RouterStateSnapshot):boolean {
    let tokenData:any = this.sessionService.getTokenData();
    //console.log(tokenData);
    if(tokenData==null){//防止用户清空缓存
      return false;
    }
    let pageUrlList = tokenData.pageUrlList;
    if(pageUrlList==null){
      return false;
    }
    let url:string = null;
    try {
      url = state.url.split('?')[0];//获得页面请求的URL
      //console.log(url);
    }catch (e){
      return false;
    }
    if(url!=null){
      if(url=='/web'){//登录后首页不需要权限，任何人都可以看到
        return true;
      }else{
        for(let i of pageUrlList){
          if(url==i){
            return true;
          }
        }
      }
    }
    return false;
  }

  /** 操作权限判断 */
  public canShow(apiUrl:string):boolean{
    let tokenData:any = this.sessionService.getTokenData();
    if(tokenData==null){
      return false;
    }else{
      let authOperateList = tokenData.apiUrlList;
      if(authOperateList==null){
        return false;
      }
      for(let i of authOperateList){
        if(apiUrl==i){
          return true;
        }
      }
    }
    return false;
  }

}
