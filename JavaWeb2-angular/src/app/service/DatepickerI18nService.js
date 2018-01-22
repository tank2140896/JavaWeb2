"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
//参考：https://ng-bootstrap.github.io/#/components/datepicker/examples
var DatepickerI18nService = /** @class */ (function (_super) {
    __extends(DatepickerI18nService, _super);
    function DatepickerI18nService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    // constructor(private _i18n:I18n) {
    //     super();
    // }
    DatepickerI18nService.prototype.getWeekdayShortName = function (weekday) {
        return I18N_VALUES['zh_cn'].weekdays[weekday - 1];
        //return I18N_VALUES[this._i18n.language].weekdays[weekday - 1];
    };
    DatepickerI18nService.prototype.getMonthShortName = function (month) {
        return I18N_VALUES['zh_cn'].months[month - 1];
        //return I18N_VALUES[this._i18n.language].months[month - 1];
    };
    DatepickerI18nService.prototype.getMonthFullName = function (month) {
        return this.getMonthShortName(month);
    };
    DatepickerI18nService = __decorate([
        core_1.Injectable()
    ], DatepickerI18nService);
    return DatepickerI18nService;
}(ng_bootstrap_1.NgbDatepickerI18n));
exports.DatepickerI18nService = DatepickerI18nService;
// @Injectable()
// export class I18n {
//
//     language = 'zh_cn';
//
// }
var I18N_VALUES = {
    'en': {
        weekdays: ['Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'Su'],
        months: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    },
    'zh_cn': {
        weekdays: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
        months: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
    }
};
//# sourceMappingURL=DatepickerI18nService.js.map