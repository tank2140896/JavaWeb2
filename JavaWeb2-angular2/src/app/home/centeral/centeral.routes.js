"use strict";
var user_component_1 = require("./sys_manager/user_manager/user.component");
var role_component_1 = require("./sys_manager/role_manager/role.component");
var centeral_component_1 = require("./centeral.component");
var user_routes_1 = require("./sys_manager/user_manager/user.routes");
var role_routes_1 = require("./sys_manager/role_manager/role.routes");
exports.ROUTES_CENTERAL = [
    { path: '', component: centeral_component_1.CenteralComponent },
    { path: 'sysManager/userManger', component: user_component_1.UserComponent, children: user_routes_1.ROUTES_USER },
    { path: 'sysManager/roleManger', component: role_component_1.RoleComponent, children: role_routes_1.ROUTES_ROLE }
];
//# sourceMappingURL=centeral.routes.js.map