import {Injectable} from "@angular/core";
import {NgbDatepickerI18n} from "@ng-bootstrap/ng-bootstrap";

//参考：https://ng-bootstrap.github.io/#/components/datepicker/examples
@Injectable()
export class DatepickerI18nService extends NgbDatepickerI18n {

    // constructor(private _i18n:I18n) {
    //     super();
    // }

    getWeekdayShortName(weekday: number): string {
        return I18N_VALUES['zh_cn'].weekdays[weekday - 1];
        //return I18N_VALUES[this._i18n.language].weekdays[weekday - 1];
    }

    getMonthShortName(month: number): string {
        return I18N_VALUES['zh_cn'].months[month - 1];
        //return I18N_VALUES[this._i18n.language].months[month - 1];
    }

    getMonthFullName(month: number): string {
        return this.getMonthShortName(month);
    }

}

// @Injectable()
// export class I18n {
//
//     language = 'zh_cn';
//
// }

const I18N_VALUES = {

    'en': {
        weekdays: ['Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'Su'],
        months: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    },
    'zh_cn': {
        weekdays: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
        months: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
    }

};
