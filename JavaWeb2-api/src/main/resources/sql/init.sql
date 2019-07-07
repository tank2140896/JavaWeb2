SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` varchar(255) NOT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `data_type` varchar(255) DEFAULT NULL,
  `key_code` varchar(255) DEFAULT NULL,
  `value_code` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `means` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES ('20190705214431115', null, 'del_flag', 'undelete', '0', 'system', '1', '未删除', '删除标记', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431116', null, 'del_flag', 'delete', '1', 'system', '2', '删除', '删除标记', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431117', null, 'login_type', 'admin', '0', 'system', '1', '管理员', '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431118', null, 'login_type', 'web', '1', 'system', '2', '网页端', '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431119', null, 'login_type', 'android', '2', 'system', '3', '安卓端', '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431120', null, 'login_type', 'ios', '3', 'system', '4', 'IOS端', '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431121', null, 'module_type', 'catalogue', '1', 'system', '1', '目录', '模块类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431122', null, 'module_type', 'menu', '2', 'system', '2', '菜单', '模块类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431123', null, 'module_type', 'function', '3', 'system', '3', '功能', '模块类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431124', null, 'user_role_strategy', 'customize', '0', 'system', '1', '自定义', '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431125', null, 'user_role_strategy', 'union', '1', 'system', '2', '并集', '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431126', null, 'user_role_strategy', 'intersection', '2', 'system', '3', '交集', '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431127', null, 'user_role_strategy', 'basedOnUser', '3', 'system', '4', '以用户权限为准', '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431128', null, 'user_role_strategy', 'basedOnRole', '4', 'system', '5', '以角色权限为准', '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');

-- ----------------------------
-- Table structure for `module`
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `module_id` varchar(255) NOT NULL,
  `module_name` varchar(255) NOT NULL,
  `page_url` varchar(255) DEFAULT NULL,
  `api_url` varchar(10000) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `fcode` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL,
  `module_type` int(11) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `parent_alias` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `create_date` varchar(19) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_date` varchar(19) DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`module_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('20190408140043147', '系统管理', null, null, null, null, '1', '1', '1', 'sys', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408140336803', '用户管理', '/web/sys/user', null, '20190408140043147', null, '2', '1', '2', 'sys:user', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408140517989', '角色管理', '/web/sys/role', null, '20190408140043147', null, '2', '2', '2', 'sys:role', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408140608316', '模块管理', '/web/sys/module', null, '20190408140043147', null, '2', '3', '2', 'sys:module', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141509095', '查询用户', null, '/web/sys/user/list', '20190408140336803', null, '3', '1', '3', 'sys:user:list', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141522656', '新增用户', null, '/web/sys/user/add', '20190408140336803', null, '3', '2', '3', 'sys:user:add', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141538196', '修改用户', null, '/web/sys/user/modify,/web/sys/user/detail', '20190408140336803', null, '3', '3', '3', 'sys:user:modify', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141553730', '删除用户', null, '/web/sys/user/delete', '20190408140336803', null, '3', '4', '3', 'sys:user:delete', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702725', '用户详情', null, '/web/sys/user/detail', '20190408140336803', null, '3', '5', '3', 'sys:user:detail', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702726', '用户角色信息', null, '/web/sys/user/userRoleInfo', '20190408140336803', null, '3', '6', '3', 'sys:user:role', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702727', '用户角色分配', null, '/web/sys/user/userRoleAssignment,/web/sys/user/userRoleInfo', '20190408140336803', null, '3', '7', '3', 'sys:user:role:assignment', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702728', '用户模块信息', null, '/web/sys/user/userModuleInfo', '20190408140336803', null, '3', '8', '3', 'sys:user:module', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702729', '用户模块分配', null, '/web/sys/user/userModuleAssignment,/web/sys/user/userModuleInfo', '20190408140336803', null, '3', '9', '3', 'sys:user:module:assignment', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141815822', '新增角色', null, '/web/sys/role/add', '20190408140517989', null, '3', '1', '3', 'sys:role:add', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141827174', '修改角色', null, '/web/sys/role/modify,/web/sys/role/detail', '20190408140517989', null, '3', '2', '3', 'sys:role:modifty', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141852659', '查询角色', null, '/web/sys/role/list', '20190408140517989', null, '3', '3', '3', 'sys:role:list', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141902486', '角色详情', null, '/web/sys/role/detail', '20190408140517989', null, '3', '4', '3', 'sys:role:detail', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141916424', '删除角色', null, '/web/sys/role/delete', '20190408140517989', null, '3', '5', '3', 'sys:role:delete', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142025602', '角色模块分配', null, '/web/sys/role/roleModuleAssignment,/web/sys/role/roleModuleInfo', '20190408140517989', null, '3', '6', '3', 'sys:role:module:assignment', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142025603', '角色模块信息', null, '/web/sys/role/roleModuleInfo', '20190408140517989', null, '3', '7', '3', 'sys:role:module', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142112268', '查询模块', null, '/web/sys/module/list', '20190408140608316', null, '3', '1', '3', 'sys:module:list', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142122024', '模块详情', null, '/web/sys/module/detail', '20190408140608316', null, '3', '2', '3', 'sys:module:detail', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142132292', '新增模块', null, '/web/sys/module/add,/web/sys/module/getModuleIdAndNameList', '20190408140608316', null, '3', '3', '3', 'sys:module:add', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142143289', '删除模块', null, '/web/sys/module/delete', '20190408140608316', null, '3', '4', '3', 'sys:module:delete', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142154821', '修改模块', null, '/web/sys/module/modify,/web/sys/module/getModuleIdAndNameList', '20190408140608316', null, '3', '5', '3', 'sys:module:modify', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142154822', '模块ID和名称列表', null, '/web/sys/module/getModuleIdAndNameList', '20190408140608316', null, '3', '6', '3', 'sys:module:getModuleIdAndNameList', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` varchar(255) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `fcode` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT '0',
  `type` int(11) DEFAULT '0',
  `remark` varchar(255) DEFAULT NULL,
  `create_date` varchar(19) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_date` varchar(19) DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_module`
-- ----------------------------
DROP TABLE IF EXISTS `role_module`;
CREATE TABLE `role_module` (
  `id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  `module_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role_module
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `person_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `portrait` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `fcode` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT '0',
  `remark` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `create_date` varchar(19) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_date` varchar(19) DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_module`
-- ----------------------------
DROP TABLE IF EXISTS `user_module`;
CREATE TABLE `user_module` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `module_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_module
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  `strategy` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------