"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var DemoComponent = /** @class */ (function () {
    function DemoComponent() {
        /** demo2 */
        this.demo2_forEach = [11, 12, 13];
        this.demo2_forMap = [];
        this.demo2_forOutput = '';
        this.demo2_forEachRet = 0;
    }
    DemoComponent.prototype.ngOnInit = function () {
        this.demo2_forEachShow();
        this.demo2_forMapShow();
        this.demo2_forOutputShow();
    };
    DemoComponent.prototype.demo1_showInputValue = function (x) {
        this.demo1_inputValueShow = x.value;
    };
    DemoComponent.prototype.demo2_forEachShow = function () {
        var _this = this;
        this.demo2_forEach.forEach(function (x) {
            _this.demo2_forEachRet += x;
        });
    };
    DemoComponent.prototype.demo2_forMapShow = function () {
        var _this = this;
        this.demo2_forEach.map(function (x) {
            _this.demo2_forMap.push(x + 10);
        });
    };
    DemoComponent.prototype.demo2_forOutputShow = function () {
        var a = 'a';
        var b = 'b';
        this.demo2_forOutput = "\n            \u8F93\u51FAA\uFF1A" + a + ",\n            \u8F93\u51FAB\uFF1A" + b + "\n        ";
    };
    /** demo3 */
    DemoComponent.prototype.demo3_ngIf = function () {
        console.log('我会被输出2次');
        return true;
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