import {Component,OnInit,HostBinding} from '@angular/core';

@Component({
    selector: 'app-demo',
    templateUrl: './demo.html',
    styleUrls: ['./demo.scss'],
})

export class DemoComponent implements OnInit {

    constructor() {

    }

    ngOnInit() {
        this.demo2_forEachShow();
    }

    /** demo1 */
    demo1_inputValueShow:string;
    public demo1_showInputValue(x:HTMLInputElement):void{
        this.demo1_inputValueShow = x.value;
    }

    /** demo2 */
    demo2_forEach:number[] = [11,12,13];
    demo2_forEachRet:number = 0;
    public demo2_forEachShow():void{
        this.demo2_forEach.forEach((x:number)=>{
            this.demo2_forEachRet+=x;
        });
    }

}
