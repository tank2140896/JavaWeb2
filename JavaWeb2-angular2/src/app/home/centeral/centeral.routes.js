"use strict";
var user_list_component_1 = require("./sys_manager/user_manager/user.list.component");
var role_list_component_1 = require("./sys_manager/role_manager/role.list.component");
var centeral_component_1 = require("./centeral.component");
exports.ROUTES_CENTERAL = [
    { path: '', component: centeral_component_1.CenteralComponent },
    { path: 'sysManager/userManger/list', component: user_list_component_1.UserListComponent },
    { path: 'sysManager/roleManger/list', component: role_list_component_1.RoleListComponent }
];
//# sourceMappingURL=centeral.routes.js.map