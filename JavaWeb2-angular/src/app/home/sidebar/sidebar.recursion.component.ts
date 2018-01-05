import {Component,Input} from "@angular/core";

@Component({
    selector: 'sidebar-recursion',
    templateUrl: 'sidebar.recursion.html',
    styleUrls: ['sidebar.recursion.css']
})

export class SidebarRecursionComponent {

    @Input('tag') tag:string = '+';
    @Input('hiddenFlag') hiddenFlag = true;

    tagEvent():void{
	if(this.hiddenFlag==true){
		this.tag = '-';
		this.hiddenFlag = false;
	}else{
		this.tag = '+';
		this.hiddenFlag = true;
	}
    }

    @Input() url:string;
    @Input() name:string;
    @Input() list:any[];

}
