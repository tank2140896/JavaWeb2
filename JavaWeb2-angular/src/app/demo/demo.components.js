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
        this.angular_demo2_forEachShow();
        this.angular_demo2_forMapShow();
        this.angular_demo2_forOutputShow();
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
        var source$ = rxjs_1.of(1, 2, 3);
        source$.subscribe(console.log);
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