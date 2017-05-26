import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {RouteFullPath} from "../../../app.routes";
import {UserAdd} from "../../../models/user/user.add";

@Component({
    selector: 'user-manage-add',
    templateUrl: 'user-manage.add.html'
})

export class UserManageAddComponent {

    constructor(private router:Router){}

    private userAdd:UserAdd = new UserAdd();

    //取消
    public cancel():void{
        this.router.navigate([RouteFullPath.UserManageList]);
    }

    //保存
    public save():void{
        console.log(this.userAdd);
    }

}
