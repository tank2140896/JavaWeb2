SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `module`
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `module_id` varchar(255) NOT NULL,
  `module_name` varchar(255) NOT NULL,
  `page_url` varchar(255) DEFAULT NULL,
  `api_url` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `fcode` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL,
  `module_type` int(11) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `parent_alias` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('0147864d-1227-11e7-9162-00ffaea60ab9', '用户列表', 'userManage/list', '/sys/user/list', '8627cbc0-1222-11e7-9162-00ffaea60ab9', null, '3', '3', '2', null, null, null, null, '2017-03-26 21:21:31', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('619c72fe-1222-11e7-9162-00ffaea60ab9', '系统管理', null, null, null, null, '1', '1', '1', null, null, null, null, '2017-03-26 20:48:18', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('8627cbc0-1222-11e7-9162-00ffaea60ab9', '用户管理', 'userManage/list', null, '619c72fe-1222-11e7-9162-00ffaea60ab9', null, '2', '1', '1', null, null, null, null, '2017-03-26 20:49:07', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('a', '层次1', null, null, null, null, '1', '1', '1', null, null, null, null, '2017-04-11 14:55:36', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('a0b3ed5a-1222-11e7-9162-00ffaea60ab9', '新增用户', null, null, '0147864d-1227-11e7-9162-00ffaea60ab9', null, '3', '1', '2', null, null, null, null, '2017-03-26 20:49:40', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('b', '层次1-1', '/b', null, 'a', null, '2', '1', '1', null, null, null, null, '2017-04-11 14:55:39', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('c', '层次1-2', null, null, 'a', null, '2', '2', '1', null, null, null, null, '2017-04-11 14:55:41', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('c4158250-1222-11e7-9162-00ffaea60ab9', '修改用户', null, null, '0147864d-1227-11e7-9162-00ffaea60ab9', null, '3', '2', '2', null, null, null, null, '2017-03-26 20:50:57', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('d', '层次1-3', '/d', null, 'a', null, '2', '3', '1', null, null, null, null, '2017-04-11 14:55:43', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('e', '层次1-2-1', '/e', null, 'c', null, '3', '1', '1', null, null, null, null, '2017-04-11 14:55:45', '超级管理员', null, null, '0');
INSERT INTO `module` VALUES ('f', '层次1-2-2', '/f', null, 'c', null, '3', '2', '1', null, null, null, null, '2017-04-11 14:55:47', '超级管理员', null, null, '0');

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
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `create_date` datetime DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0bad8187-1786-11e7-90a8-00ffaea60ab9', 'admin001', 'YWRtaW4=', 'admin001', null, null, null, '65a85f1b-1209-11e7-9162-00ffaea60ab9', null, '3', null, '0', '2017-04-02 17:42:22', null, null, null, '0');
INSERT INTO `user` VALUES ('248c7afc-1786-11e7-90a8-00ffaea60ab9', 'admin002', 'YWRtaW4=', 'admin002', null, null, null, '65a85f1b-1209-11e7-9162-00ffaea60ab9', null, '3', null, '0', '2017-04-02 17:42:25', null, null, null, '0');
INSERT INTO `user` VALUES ('30aa5e16-1786-11e7-90a8-00ffaea60ab9', 'admin003', 'YWRtaW4=', 'admin003', null, null, null, '65a85f1b-1209-11e7-9162-00ffaea60ab9', null, '3', null, '0', '2017-04-02 17:42:27', null, null, null, '0');
INSERT INTO `user` VALUES ('36f5416e-1786-11e7-90a8-00ffaea60ab9', 'admin004', 'YWRtaW4=', 'admin004', null, null, null, '65a85f1b-1209-11e7-9162-00ffaea60ab9', null, '3', null, '0', '2017-04-02 17:42:29', null, null, null, '0');
INSERT INTO `user` VALUES ('3d539255-1786-11e7-90a8-00ffaea60ab9', 'admin005', 'YWRtaW4=', 'admin005', null, null, null, '65a85f1b-1209-11e7-9162-00ffaea60ab9', null, '3', null, '0', '2017-04-02 17:42:31', null, null, null, '0');
INSERT INTO `user` VALUES ('4c48b2f4-1786-11e7-90a8-00ffaea60ab9', 'admin006', 'YWRtaW4=', 'admin006', null, null, null, '65a85f1b-1209-11e7-9162-00ffaea60ab9', null, '3', null, '0', '2017-04-02 17:42:34', null, null, null, '0');
INSERT INTO `user` VALUES ('65a85f1b-1209-11e7-9162-00ffaea60ab9', 'admin', 'YWRtaW4=', 'admin', null, null, null, null, null, '2', null, '0', '2017-04-02 17:42:36', null, null, null, '0');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------