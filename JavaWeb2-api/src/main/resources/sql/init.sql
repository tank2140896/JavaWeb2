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
INSERT INTO `dictionary` VALUES ('20190705214431115', null, 'del_flag', 'undelete', '0', 'system', '1', '未删除', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431116', null, 'del_flag', 'delete', '1', 'system', '2', '删除', '2018-02-08 17:02:21', 'admin123456', null, null, '0');

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
INSERT INTO `module` VALUES ('20190408140336803', '用户管理', '/web/sys/user', null, '20190408140043147', null, '2', '1', '1', 'sys:user', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408140517989', '角色管理', '/web/sys/role', null, '20190408140043147', null, '2', '2', '1', 'sys:role', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408140608316', '模块管理', '/web/sys/module', null, '20190408140043147', null, '2', '3', '1', 'sys:module', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141509095', '查询用户', null, '/web/sys/user/list', '20190408140336803', null, '3', '1', '2', 'sys:user:list', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141522656', '新增用户', null, '/web/sys/user/add', '20190408140336803', null, '3', '2', '2', 'sys:user:add', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141538196', '修改用户', null, '/web/sys/user/modify,/web/sys/user/detail', '20190408140336803', null, '3', '3', '2', 'sys:user:modify', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141553730', '删除用户', null, '/web/sys/user/delete', '20190408140336803', null, '3', '4', '2', 'sys:user:delete', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702725', '用户详情', null, '/web/sys/user/detail', '20190408140336803', null, '3', '5', '2', 'sys:user:detail', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702726', '用户角色信息', null, '/web/sys/user/userRoleInfo', '20190408140336803', null, '3', '6', '2', 'sys:user:role', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702727', '用户角色分配', null, '/web/sys/user/userRoleAssignment,/web/sys/user/userRoleInfo', '20190408140336803', null, '3', '7', '2', 'sys:user:role:assignment', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702728', '用户模块信息', null, '/web/sys/user/userModuleInfo', '20190408140336803', null, '3', '8', '2', 'sys:user:module', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702729', '用户模块分配', null, '/web/sys/user/userModuleAssignment,/web/sys/user/userModuleInfo', '20190408140336803', null, '3', '9', '2', 'sys:user:module:assignment', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141815822', '新增角色', null, '/web/sys/role/add', '20190408140517989', null, '3', '1', '2', 'sys:role:add', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141827174', '修改角色', null, '/web/sys/role/modify,/web/sys/role/detail', '20190408140517989', null, '3', '2', '2', 'sys:role:modifty', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141852659', '查询角色', null, '/web/sys/role/list', '20190408140517989', null, '3', '3', '2', 'sys:role:list', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141902486', '角色详情', null, '/web/sys/role/detail', '20190408140517989', null, '3', '4', '2', 'sys:role:detail', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141916424', '删除角色', null, '/web/sys/role/delete', '20190408140517989', null, '3', '5', '2', 'sys:role:delete', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142025602', '角色模块分配', null, '/web/sys/role/roleModuleAssignment,/web/sys/role/roleModuleInfo', '20190408140517989', null, '3', '6', '2', 'sys:role:module:assignment', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142025603', '角色模块信息', null, '/web/sys/role/roleModuleInfo', '20190408140517989', null, '3', '7', '2', 'sys:role:module', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142112268', '查询模块', null, '/web/sys/module/list', '20190408140608316', null, '3', '1', '2', 'sys:module:list', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142122024', '模块详情', null, '/web/sys/module/detail', '20190408140608316', null, '3', '2', '2', 'sys:module:detail', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142132292', '新增模块', null, '/web/sys/module/add', '20190408140608316', null, '3', '3', '2', 'sys:module:add', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142143289', '删除模块', null, '/web/sys/module/delete', '20190408140608316', null, '3', '4', '2', 'sys:module:delete', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142154821', '修改模块', null, '/web/sys/module/modify,/web/sys/module/detail', '20190408140608316', null, '3', '5', '2', 'sys:module:modify', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');

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