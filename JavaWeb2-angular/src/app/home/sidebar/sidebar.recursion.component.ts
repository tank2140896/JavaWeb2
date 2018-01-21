import {Component,Input,ViewEncapsulation} from "@angular/core";

@Component({
    //encapsulation参考：https://stackoverflow.com/questions/36214546/styles-in-component-for-d3-js-do-not-show-in-angular-2/36226061#36226061
    encapsulation: ViewEncapsulation.None,
    selector: 'home-sidebar-recursion',
    templateUrl: './sidebar.recursion.html',
    styleUrls: ['./sidebar.component.scss']
})

export class SidebarRecursionComponent {

    @Input('showMenu') showMenu:boolean = true;

    addExpandClass():void {
        if (this.showMenu==true) {
            this.showMenu = false;
        } else {
            this.showMenu = true;
        }
    }

    @Input() url:string;
    @Input() name:string;
    @Input() icon:string;
    @Input() list:any[];

}
