import {Component,Input} from "@angular/core";

@Component({
    selector: 'sidebar-recursion',
    templateUrl: 'sidebar.recursion.html',
    styleUrls: ['sidebar.recursion.css']
})

export class SidebarRecursionComponent {

    @Input() url:string;
    @Input() name:string;
    @Input() list:any[];

}