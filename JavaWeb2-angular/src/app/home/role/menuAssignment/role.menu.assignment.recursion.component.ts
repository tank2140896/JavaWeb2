import {Component,Input,Output,ViewEncapsulation,EventEmitter} from "@angular/core";

@Component({
    //encapsulation参考：https://stackoverflow.com/questions/36214546/styles-in-component-for-d3-js-do-not-show-in-angular-2/36226061#36226061
    encapsulation: ViewEncapsulation.None,
    selector: 'role-menuAssignmentRecursion',
    templateUrl: './role.menu.assignment.recursion.html',
    styleUrls: ['./role.menu.assignment.scss']
})

export class RoleMenuAssignmentRecursionComponent {

    // @Input() checkFlag:boolean;
    // @Input() moduleName:string;
    // @Input() moduleId:string;
    @Input() list:any[];

    /**
    @Output() onCheckBoxChecked:EventEmitter<Boolean>;
    public childClick(checkFlag):void{
        console.log(checkFlag);
        if(checkFlag){
            this.checkFlag = false;
        }else{
            this.checkFlag = true;
        }
        this.onCheckBoxChecked.emit(this.checkFlag);
    }
    public checkBoxChecked($event):void{
        console.log("父组件写上类似<my></my (onCheckBoxChecked)='checkBoxChecked($event)'><my>，然后从checkBoxChecked方法可以获得子组件的值，但是在递归下还是有难度的");
    }
    */

}
