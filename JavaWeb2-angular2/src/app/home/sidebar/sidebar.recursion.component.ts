import {Component,Input} from "@angular/core";

@Component({
    selector: 'sidebar-recursion',
    template: `
                <div *ngIf="list.length==0"><a [routerLink]="[url]">{{name}}</a></div>
                <div *ngIf="list.length>0">{{name}}</div>
                <div *ngIf="list.length>0">
                    <sidebar-recursion *ngFor="let i of list" [url]="i.pageUrl" [name]="i.moduleName" [list]="i.list"></sidebar-recursion>
                </div>            
        `,
    styleUrls: ['sidebar.recursion.css']
})

export class SidebarRecursionComponent {

    @Input() url:string;
    @Input() name:string;
    @Input() list:any[];

}