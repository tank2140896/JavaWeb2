import {CommonConstant} from '../constant/CommonConstant';

export class HeadToken {

  token:string = CommonConstant.EMPTY;

  isAuth = '0';//0（不加密）；1（加密）

  //requestContentType = '1';//1（application/json）；2（multipart/form-data）；3（application/x-www-form-urlencoded）

  constructor (private  _isAuth?:string/*,private _requestContentType?:string*/){
    if(_isAuth==null||_isAuth==undefined){
      this.isAuth = '0';
    }else{
      this.isAuth = _isAuth;
    }
    /**
    if(_requestContentType==null||_requestContentType==undefined){
      this.requestContentType = '1';
    }else{
      this.requestContentType = _requestContentType;
    }
    */
  }

}
