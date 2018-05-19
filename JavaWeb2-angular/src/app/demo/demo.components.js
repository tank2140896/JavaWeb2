"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var rxjs_1 = require("rxjs");
var operators_1 = require("rxjs/internal/operators");
var DemoComponent = /** @class */ (function () {
    function DemoComponent() {
        /** angular_demo2 */
        this.demo2_forEach = [11, 12, 13];
        this.demo2_forMap = [];
        this.demo2_forOutput = '';
        this.demo2_forEachRet = 0;
        /** angular_demo4 */
        this.angular_demo4_switchValue = 'A';
    }
    DemoComponent.prototype.ngOnInit = function () {
        /** demo for angular */
        this.angular_demo2_forEachShow();
        this.angular_demo2_forMapShow();
        this.angular_demo2_forOutputShow();
        /** demo for rxjs */
        this.rxjs_demo1();
    };
    DemoComponent.prototype.angular_demo1_showInputValue = function (x) {
        this.demo1_inputValueShow = x.value;
    };
    DemoComponent.prototype.angular_demo2_forEachShow = function () {
        var _this = this;
        this.demo2_forEach.forEach(function (x) {
            _this.demo2_forEachRet += x;
        });
    };
    DemoComponent.prototype.angular_demo2_forMapShow = function () {
        var _this = this;
        this.demo2_forEach.map(function (x) {
            _this.demo2_forMap.push(x + 10);
        });
    };
    DemoComponent.prototype.angular_demo2_forOutputShow = function () {
        var a = 'a';
        var b = 'b';
        this.demo2_forOutput = "\n            \u8F93\u51FAA\uFF1A" + a + ",\n            \u8F93\u51FAB\uFF1A" + b + "\n        ";
    };
    /** angular_demo3 */
    DemoComponent.prototype.angular_demo3_ngIf = function () {
        console.log('我会被输出2次');
        return true;
    };
    /*----------------------------------------------------------------------------------------------------------------*/
    /** rxjs_demo1 */
    DemoComponent.prototype.rxjs_demo1 = function () {
        var source$ = rxjs_1.of(11, 22, 33);
        source$.subscribe(console.log);
        source$.subscribe(function (each) {
            console.log(each * 10);
        });
    };
    /** rxjs_demo2 */
    DemoComponent.prototype.rxjs_demo2 = function () {
        var source$ = rxjs_1.Observable.create(function (o) {
            o.next(10);
            o.next(20);
            o.next(30);
        });
        source$.pipe(operators_1.map(function (each) { return each * 10; })).subscribe(function (i) { return console.log(i); });
    };
    /** rxjs_demo3 */
    DemoComponent.prototype.rxjs_demo3 = function () {
        var onSubscribe = function (x) {
            /**
            x.next(11);
            x.next(22);
            x.next(33);
            */
            var num = 11;
            var handle = setInterval(function () {
                x.next(num++);
                if (num > 13) {
                    clearInterval(handle);
                    //注：error和complete只会有一种状态
                    x.error('error');
                    x.complete();
                }
            }, 1000);
            return {
                unsubscribe: function () {
                    clearInterval(handle);
                }
            };
        };
        var source$ = new rxjs_1.Observable(onSubscribe);
        var theObserver = {
            next: function (item) { return console.log(item); },
            error: function (err) { return console.log(err); },
            complete: function () { return console.log('all over'); }
        };
        source$.subscribe(theObserver);
        //const subscription = source$.subscribe(theObserver);
        //setTimeout(()=>{subscription.unsubscribe();},2000);
    };
    DemoComponent = __decorate([
        core_1.Component({
            selector: 'app-demo',
            templateUrl: './demo.html',
            styleUrls: ['./demo.scss']
        })
    ], DemoComponent);
    return DemoComponent;
}());
exports.DemoComponent = DemoComponent;
//# sourceMappingURL=demo.components.js.map