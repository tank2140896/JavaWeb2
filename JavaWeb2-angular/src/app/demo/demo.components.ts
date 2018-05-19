import {Component,OnInit} from '@angular/core';

import {of,Observable} from 'rxjs';
import {map} from "rxjs/internal/operators";

@Component({
    selector: 'app-demo',
    templateUrl: './demo.html',
    styleUrls: ['./demo.scss'],
})

export class DemoComponent implements OnInit {

    constructor() {

    }
    ngOnInit() {
        /** demo for angular */
        this.angular_demo2_forEachShow();
        this.angular_demo2_forMapShow();
        this.angular_demo2_forOutputShow();
        /** demo for rxjs */
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
        const source$ = of(11,22,33);
        source$.subscribe(console.log);
        source$.subscribe(each=>{
            console.log(each*10);
        });
    }

    /** rxjs_demo2 */
    public rxjs_demo2():void{
        const source$ = Observable.create(o=>{
            o.next(10);
            o.next(20);
            o.next(30);
        });
        source$.pipe(map(each=>(<number>each)*10)).subscribe(i=>console.log(i));
    }

    /** rxjs_demo3 */
    public rxjs_demo3():void{
        const onSubscribe = x =>{
            /**
            x.next(11);
            x.next(22);
            x.next(33);
            */
            let num = 11;
            const handle = setInterval(()=>{
                x.next(num++);
                if(num>13){
                    clearInterval(handle);
                    //注：error和complete只会有一种状态
                    x.error('error');
                    x.complete();
                }
            },1000);
            return {
                unsubscribe:()=>{
                    clearInterval(handle);
                }
            };
        };
        const source$ = new Observable(onSubscribe);
        const theObserver = {
            next: item => console.log(item),
            error: err=> console.log(err),
            complete: ()=> console.log('all over')
        };
        source$.subscribe(theObserver);
        //const subscription = source$.subscribe(theObserver);
        //setTimeout(()=>{subscription.unsubscribe();},2000);
    }

}
