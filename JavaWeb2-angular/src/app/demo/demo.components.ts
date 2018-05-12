import {Component,OnInit} from '@angular/core';

import {of} from 'rxjs';

@Component({
    selector: 'app-demo',
    templateUrl: './demo.html',
    styleUrls: ['./demo.scss'],
})

export class DemoComponent implements OnInit {

    constructor() {

    }

    ngOnInit() {
        this.angular_demo2_forEachShow();
        this.angular_demo2_forMapShow();
        this.angular_demo2_forOutputShow();

        this.rxjs_demo1();
    }

    /** angular_demo1 */
    demo1_inputValueShow:string;
    public angular_demo1_showInputValue(x:HTMLInputElement):void{
        this.demo1_inputValueShow = x.value;
    }

    /** angular_demo2 */
    demo2_forEach:number[] = [11,12,13];
    demo2_forMap:number[] = [];
    demo2_forOutput:string = '';
    demo2_forEachRet:number = 0;
    public angular_demo2_forEachShow():void{
        this.demo2_forEach.forEach((x:number)=>{
            this.demo2_forEachRet+=x;
        });
    }
    public angular_demo2_forMapShow():void{
        this.demo2_forEach.map((x:number)=>{
            this.demo2_forMap.push(x+10);
        });
    }
    public angular_demo2_forOutputShow():void{
        let a = 'a';
        let b = 'b';
        this.demo2_forOutput = `
            输出A：${a},
            输出B：${b}
        `;
    }

    /** angular_demo3 */
    public angular_demo3_ngIf():boolean{
        console.log('我会被输出2次');
        return true;
    }

    /** angular_demo4 */
    angular_demo4_switchValue:string='A';

    /*----------------------------------------------------------------------------------------------------------------*/

    /** rxjs_demo1 */
    public rxjs_demo1():void{
        const source$ = of(1,2,3);
        source$.subscribe(console.log);
    }

}
