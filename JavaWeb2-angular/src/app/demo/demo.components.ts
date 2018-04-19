import {Component,OnInit} from '@angular/core';

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
        this.demo2_forMapShow();
        this.demo2_forOutputShow();
    }

    /** demo1 */
    demo1_inputValueShow:string;
    public demo1_showInputValue(x:HTMLInputElement):void{
        this.demo1_inputValueShow = x.value;
    }

    /** demo2 */
    demo2_forEach:number[] = [11,12,13];
    demo2_forMap:number[] = [];
    demo2_forOutput:string = '';
    demo2_forEachRet:number = 0;
    public demo2_forEachShow():void{
        this.demo2_forEach.forEach((x:number)=>{
            this.demo2_forEachRet+=x;
        });
    }
    public demo2_forMapShow():void{
        this.demo2_forEach.map((x:number)=>{
            this.demo2_forMap.push(x+10);
        });
    }
    public demo2_forOutputShow():void{
        let a = 'a';
        let b = 'b';
        this.demo2_forOutput = `
            输出A：${a},
            输出B：${b}
        `;
    }

    /** demo3 */
    public demo3_ngIf():boolean{
        console.log('我会被输出2次');
        return true;
    }

    /** demo4 */
    demo4_switchValue:string='A';

}
