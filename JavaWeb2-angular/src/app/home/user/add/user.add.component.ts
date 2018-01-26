import {Component,OnInit} from '@angular/core';
import {Router} from "@angular/router";

import {HttpService} from "../../../service/HttpService";
import {AuthService} from "../../../service/AuthService";
import {SessionService} from "../../../service/SessionService";

@Component({
    selector: 'user-add',
    templateUrl: './user.add.html',
    styleUrls: ['./user.add.scss']
})

export class UserAddComponent implements OnInit {

    constructor(private router:Router,
                private httpService:HttpService,
                private authService:AuthService,
                private sessionService:SessionService){

    }

    //初始化
    ngOnInit(): void {

    }

}
