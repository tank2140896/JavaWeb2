"use strict";
var user_list_component_1 = require("./list/user.list.component");
var user_add_component_1 = require("./add/user.add.component");
exports.ROUTES_USER = [
    { path: '', component: user_list_component_1.UserListComponent },
    { path: 'list', component: user_list_component_1.UserListComponent },
    { path: 'add', component: user_add_component_1.UserAddComponent }
];
//# sourceMappingURL=user.routes.js.map