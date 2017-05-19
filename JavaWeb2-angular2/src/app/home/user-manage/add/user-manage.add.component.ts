import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {RouteFullPath} from "../../../app.routes";

@Component({
    selector: 'user-manage-add',
    templateUrl: 'user-manage.add.html'
})

export class UserManageAddComponent {

    constructor(private router:Router){}

    public cancel():void{
        this.router.navigate([RouteFullPath.UserManageList]);
    }

}
