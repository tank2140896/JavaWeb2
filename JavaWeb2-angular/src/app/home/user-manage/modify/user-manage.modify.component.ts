import {Component} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'user-manage-modify',
    templateUrl: 'user-manage.modify.html'
})

export class UserManageModifyComponent {

    private userId:string;

    constructor(private activatedRoute:ActivatedRoute){
        this.userId = activatedRoute.snapshot.queryParams['userId'];
        console.log(this.userId);
    }

}
