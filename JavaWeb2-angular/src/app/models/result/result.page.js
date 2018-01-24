"use strict";
exports.__esModule = true;
var common_constant_1 = require("../../constant/common.constant");
var ResultPage = /** @class */ (function () {
    function ResultPage(ret) {
        this.dataLoading = common_constant_1.CommonConstant.DATA_LOADING;
        if (ret == undefined) {
            this.data = common_constant_1.CommonConstant.DATA_LOADING;
        }
        else {
            var getData = ret.data;
            if (getData == null || getData == '' || getData.length == 0) {
                this.data = null;
            }
            else {
                this.data = ret.data;
                this.currentPage = ret.currentPage;
                this.totalPage = ret.totalPage;
                this.pageSize = ret.pageSize;
                this.totalSize = ret.totalSize;
                this.pageList = ret.pageList;
            }
        }
    }
    return ResultPage;
}());
exports.ResultPage = ResultPage;
//# sourceMappingURL=result.page.js.map