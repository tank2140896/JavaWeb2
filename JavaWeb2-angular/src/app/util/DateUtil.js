"use strict";
exports.__esModule = true;
var DateUtil = /** @class */ (function () {
    function DateUtil() {
    }
    DateUtil.formatDate = function (date) {
        console.log(date);
        if (typeof date.month === "number") {
            if (date.month < 10) {
                date.month = '0' + date.month;
            }
        }
        if (typeof date.day === "number") {
            if (date.day < 10) {
                date.day = '0' + date.day;
            }
        }
        return date.year + '-' + date.month + '-' + date.day + ' 00:00:00';
    };
    return DateUtil;
}());
exports.DateUtil = DateUtil;
//# sourceMappingURL=DateUtil.js.map