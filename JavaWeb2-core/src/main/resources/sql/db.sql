SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `module`
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `moduleid` varchar(255) NOT NULL,
  `modulename` varchar(255) NOT NULL,
  `moduleurl` varchar(255) DEFAULT NULL,
  `parentid` varchar(255) DEFAULT NULL,
  `fcode` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL,
  `moduletype` int(11) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `parentalias` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`moduleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('0147864d-1227-11e7-9162-00ffaea60ab9', '用户列表', '/sys/user/list', '8627cbc0-1222-11e7-9162-00ffaea60ab9', null, '3', '3', '2', null, null, null, null, '2017-03-26 21:21:31', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('06cb9e7f-1482-11e7-950d-00ffaea60ab9', '层级测试1级', null, null, null, '1', '2', '1', null, null, '层级测试', null, '2017-03-29 21:18:25', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('3765b5b5-1482-11e7-950d-00ffaea60ab9', '层级测试2-1级', null, '06cb9e7f-1482-11e7-950d-00ffaea60ab9', null, '2', '1', '1', null, null, '层级测试', null, '2017-03-29 21:19:39', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('619c72fe-1222-11e7-9162-00ffaea60ab9', '系统管理', null, null, null, '1', '1', '1', null, null, null, null, '2017-03-26 20:48:18', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('62d62437-1482-11e7-950d-00ffaea60ab9', '层级测试2-2级', null, '06cb9e7f-1482-11e7-950d-00ffaea60ab9', null, '2', '2', '1', null, null, '层级测试', null, '2017-03-29 21:20:16', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('78233933-1482-11e7-950d-00ffaea60ab9', '层级测试2-3级', null, '06cb9e7f-1482-11e7-950d-00ffaea60ab9', null, '2', '3', '1', null, null, '层级测试', null, '2017-03-29 21:20:45', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('8627cbc0-1222-11e7-9162-00ffaea60ab9', '用户管理', '/sys/user/list', '619c72fe-1222-11e7-9162-00ffaea60ab9', null, '2', '1', '1', null, null, null, null, '2017-03-26 20:49:07', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('938eefea-1482-11e7-950d-00ffaea60ab9', '层级测试2-2-1级', null, '62d62437-1482-11e7-950d-00ffaea60ab9', null, '3', '1', '1', null, null, '层级测试', null, '2017-03-29 21:22:00', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('a0b3ed5a-1222-11e7-9162-00ffaea60ab9', '新增用户', '/sys/user/add', '0147864d-1227-11e7-9162-00ffaea60ab9', null, '3', '1', '2', null, null, null, null, '2017-03-26 20:49:40', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('b6ed9059-1482-11e7-950d-00ffaea60ab9', '层级测试2-2-2级', null, '62d62437-1482-11e7-950d-00ffaea60ab9', null, '3', '2', '1', null, null, '层级测试', null, '2017-03-29 21:22:35', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('c4158250-1222-11e7-9162-00ffaea60ab9', '修改用户', '/sys/user/update', '0147864d-1227-11e7-9162-00ffaea60ab9', null, '3', '2', '2', null, null, null, null, '2017-03-26 20:50:57', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('cb0ea23c-1482-11e7-950d-00ffaea60ab9', '层级测试2-2-2-1级', null, 'b6ed9059-1482-11e7-950d-00ffaea60ab9', null, '4', '1', '1', null, null, '层级测试', null, '2017-03-29 21:23:35', '超级管理员', null, null, '0');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleid` varchar(255) NOT NULL,
  `rolename` varchar(255) NOT NULL,
  `parentid` varchar(255) DEFAULT NULL,
  `fcode` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT '0',
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('ba39f8b5-1221-11e7-9162-00ffaea60ab9', '管理员', null, null, '1', null, '2017-03-26 20:43:54', '超级管理员', null, null, '0');

-- ----------------------------
-- Table structure for `role_module`
-- ----------------------------
DROP TABLE IF EXISTS `role_module`;
CREATE TABLE `role_module` (
  `id` varchar(255) NOT NULL,
  `roleid` varchar(255) NOT NULL,
  `moduleid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_module
-- ----------------------------
INSERT INTO `role_module` VALUES ('ea73f860-1222-11e7-9162-00ffaea60ab9', 'ba39f8b5-1221-11e7-9162-00ffaea60ab9', '619c72fe-1222-11e7-9162-00ffaea60ab9');
INSERT INTO `role_module` VALUES ('ef48666e-1222-11e7-9162-00ffaea60ab9', 'ba39f8b5-1221-11e7-9162-00ffaea60ab9', '8627cbc0-1222-11e7-9162-00ffaea60ab9');
INSERT INTO `role_module` VALUES ('f3dfe498-1222-11e7-9162-00ffaea60ab9', 'ba39f8b5-1221-11e7-9162-00ffaea60ab9', 'a0b3ed5a-1222-11e7-9162-00ffaea60ab9');
INSERT INTO `role_module` VALUES ('fa9c2bca-1222-11e7-9162-00ffaea60ab9', 'ba39f8b5-1221-11e7-9162-00ffaea60ab9', 'c4158250-1222-11e7-9162-00ffaea60ab9');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `personname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `portrait` varchar(255) DEFAULT NULL,
  `parentid` varchar(255) DEFAULT NULL,
  `fcode` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT '0',
  `remark` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('65a85f1b-1209-11e7-9162-00ffaea60ab9', 'admin', 'YWRtaW4=', 'admin', null, null, null, null, null, '2', null, '0', null, null, null, null, '0');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(255) NOT NULL,
  `userid` varchar(255) NOT NULL,
  `roleid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('019dd83d-1222-11e7-9162-00ffaea60ab9', '65a85f1b-1209-11e7-9162-00ffaea60ab9', 'ba39f8b5-1221-11e7-9162-00ffaea60ab9');