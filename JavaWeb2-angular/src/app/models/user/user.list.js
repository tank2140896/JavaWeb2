"use strict";
exports.__esModule = true;
var common_constant_1 = require("../../constant/common.constant");
var UserList = /** @class */ (function () {
    function UserList() {
        this.userName = common_constant_1.CommonConstant.EMPTY; //用户名
        this.personName = common_constant_1.CommonConstant.EMPTY; //用户姓名
        this.createStartDate = null; //用户创建的开始日期
        this.createEndDate = null; //用户创建的结束日期
        this.currentPage = 1; //当前页数
        this.pageSize = 10; //每页显示多少条
    }
    return UserList;
}());
exports.UserList = UserList;
//# sourceMappingURL=user.list.js.map