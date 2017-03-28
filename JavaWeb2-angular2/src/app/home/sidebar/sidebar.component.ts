import {Component} from '@angular/core';

@Component({
    selector: 'home-sidebar',
    templateUrl: 'sidebar.html',
    styleUrls: ['sidebar.css']
})

export class SidebarComponent {

    menuList = JSON.parse(window.sessionStorage.getItem('menuList'));

}