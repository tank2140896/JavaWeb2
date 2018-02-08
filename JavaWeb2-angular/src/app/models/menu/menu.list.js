"use strict";
exports.__esModule = true;
var common_constant_1 = require("../../constant/common.constant");
var MenuList = /** @class */ (function () {
    function MenuList() {
        this.moduleName = common_constant_1.CommonConstant.EMPTY; //模块名称
        this.moduleType = '0'; //模块类型
        this.createStartDate = null; //模块创建的开始日期
        this.createEndDate = null; //模块创建的结束日期
        this.currentPage = 1; //当前页数
        this.pageSize = 10; //每页显示多少条
    }
    return MenuList;
}());
exports.MenuList = MenuList;
//# sourceMappingURL=menu.list.js.map