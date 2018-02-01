"use strict";
exports.__esModule = true;
var common_constant_1 = require("../../constant/common.constant");
var RoleList = /** @class */ (function () {
    function RoleList() {
        this.roleName = common_constant_1.CommonConstant.EMPTY; //角色名
        this.createStartDate = null; //角色创建的开始日期
        this.createEndDate = null; //角色创建的结束日期
        this.currentPage = 1; //当前页数
        this.pageSize = 10; //每页显示多少条
    }
    return RoleList;
}());
exports.RoleList = RoleList;
//# sourceMappingURL=role.list.js.map