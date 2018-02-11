import {Component,Input,Output,ViewEncapsulation,EventEmitter} from "@angular/core";

@Component({
    //encapsulation参考：https://stackoverflow.com/questions/36214546/styles-in-component-for-d3-js-do-not-show-in-angular-2/36226061#36226061
    encapsulation: ViewEncapsulation.None,
    selector: 'role-menuAssignmentRecursion',
    templateUrl: './role.menu.assignment.recursion.html',
    styleUrls: ['./role.menu.assignment.scss']
})

export class RoleMenuAssignmentRecursionComponent {

    @Input() checkFlag:boolean;
    @Input() moduleName:string;
    @Input() moduleId:string;
    @Input() list:any[];

}
