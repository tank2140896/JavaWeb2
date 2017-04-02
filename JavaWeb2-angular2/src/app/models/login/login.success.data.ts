export class LoginSuccessData {

    private token:string = null;

    private user:any = null;

    private menuList:any = null;

    private authOperateList:any = null;

    public getToken():string{
        return this.token;
    }

    public setToken(token:string):void{
        this.token = token;
    }

    public getUser():any{
        return this.user;
    }

    public setUser(user:any):void{
        this.user = user;
    }

    public getMenuList():any{
        return this.menuList;
    }

    public setMenuList(menuList:any):void{
        this.menuList = menuList;
    }

    public getAuthOperateList():any{
        return this.authOperateList;
    }

    public setAuthOperateList(authOperateList:any):void{
        this.authOperateList = authOperateList;
    }

}