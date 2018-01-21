import {Component, OnInit} from '@angular/core';
import {Router, NavigationEnd} from '@angular/router';
import {SessionService} from "../../service/SessionService";

@Component({
    selector: 'home-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

    pushRightClass: string = 'push-right';

    menuList:any;

    constructor(public router: Router,
                public sessionService:SessionService) {
        this.router.events.subscribe(val => {
            if (
                val instanceof NavigationEnd &&
                window.innerWidth <= 992 &&
                this.isToggled()
            ) {
                this.toggleSidebar();
            }
        });
    }

    ngOnInit(): void {
        let sessionData:any = this.sessionService.getSessionData();
        this.menuList = sessionData.menuList;
        //console.log(this.menuList);
    }

    isToggled(): boolean {
        const dom: Element = document.querySelector('body');
        return dom.classList.contains(this.pushRightClass);
    }

    toggleSidebar() {
        const dom: any = document.querySelector('body');
        dom.classList.toggle(this.pushRightClass);
    }

}
