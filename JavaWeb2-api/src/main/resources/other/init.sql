SET FOREIGN_KEY_CHECKS=0;

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
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('011bb51a-2ff1-11e8-8f30-1c1b0d1b66d4', '在线聊天室', '/web/pc/other/onlineChat/chat', null, 'c0543953-2ff0-11e8-8f30-1c1b0d1b66d4', null, '2', '1', '1', 'zxlts', null, null, 'fa fa-camera-retro', '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('063d7c24-03f9-11e8-a70c-00ff676e3062', '用户角色分配', '/web/pc/sys/user/roleAssignment', '/web/pc/sys/user/roleAssignment,/web/pc/sys/user/userRoleInfo', '07e7d8be-2b0d-48f8-8376-c1f22c31982c', null, '3', '6', '2', 'yhjsfp', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('07e7d8be-2b0d-48f8-8376-c1f22c31982c', '用户管理', '/web/pc/sys/user/list', null, 'a39eea77-e28a-44a6-a822-039e2a056b07', null, '2', '1', '1', 'yhgl', null, null, 'fa fa-camera-retro', '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('2582ad16-0c9a-11e8-a8e8-1c1b0d1b66d4', '模块列表', '/web/pc/sys/module/list', '/web/pc/sys/module/list', '46683b3f-ef74-4952-8ce1-fe4e7ed7ec5a', null, '3', '1', '2', 'mklb', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', '角色管理', '/web/pc/sys/role/list', null, 'a39eea77-e28a-44a6-a822-039e2a056b07', null, '2', '2', '1', 'jsgl', null, null, 'fa fa-fw fa-dashboard', '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('3dd79417-1df9-4781-844d-90b7c10fb1ff', '用户列表', '/web/pc/sys/user/list', '/web/pc/sys/user/list', '07e7d8be-2b0d-48f8-8376-c1f22c31982c', null, '3', '1', '2', 'yhlb', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('3fa14941-0110-11e8-9bec-00163e0e4f12', '删除用户', null, '/web/pc/sys/user/delete', '07e7d8be-2b0d-48f8-8376-c1f22c31982c', null, '3', '2', '2', 'scyh', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('43d41781-0ecc-11e8-9a31-1c1b0d1b66d4', '模块详情', '/web/pc/sys/module/detail', '/web/pc/sys/module/detail', '46683b3f-ef74-4952-8ce1-fe4e7ed7ec5a', null, '3', '5', '2', 'mkxq', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('44c1df40-0261-11e8-9bec-00163e0e4f12', '新增用户', '/web/pc/sys/user/add', '/web/pc/sys/user/add', '07e7d8be-2b0d-48f8-8376-c1f22c31982c', null, '3', '3', '2', 'xzyh', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('46683b3f-ef74-4952-8ce1-fe4e7ed7ec5a', '模块管理', '/web/pc/sys/module/list', null, 'a39eea77-e28a-44a6-a822-039e2a056b07', null, '2', '3', '1', 'mkgl', null, null, 'fa fa-fw fa-dashboard', '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('4bdc72ff-0afe-11e8-8934-1c1b0d1b66d4', '新增角色', '/web/pc/sys/role/add', '/web/pc/sys/role/add', '329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', null, '3', '5', '2', 'xzjs', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('5aa15800-0afd-11e8-8934-1c1b0d1b66d4', '修改角色', '/web/pc/sys/role/modify', '/web/pc/sys/role/modify,/web/pc/sys/role/detail', '329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', null, '3', '3', '2', 'xgjs', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('5fedf58e-0d5d-11e8-b414-1c1b0d1b66d4', '新增模块', '/web/pc/sys/module/add', '/web/pc/sys/module/add', '46683b3f-ef74-4952-8ce1-fe4e7ed7ec5a', null, '3', '3', '2', 'xzmk', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('64dd50c7-03e5-11e8-a70c-00ff676e3062', '修改用户', '/web/pc/sys/user/modify', '/web/pc/sys/user/modify,/web/pc/sys/user/detail', '07e7d8be-2b0d-48f8-8376-c1f22c31982c', null, '3', '4', '2', 'xgyh', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('7c2d216d-03ea-11e8-a70c-00ff676e3062', '用户详情', '/web/pc/sys/user/detail', '/web/pc/sys/user/detail', '07e7d8be-2b0d-48f8-8376-c1f22c31982c', null, '3', '5', '2', 'yhxq', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('840538ac-0cae-11e8-a8e8-1c1b0d1b66d4', '删除模块', null, '/web/pc/sys/module/delete', '46683b3f-ef74-4952-8ce1-fe4e7ed7ec5a', null, '3', '2', '2', 'scmk', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('872f4af0-0718-11e8-abf2-1c1b0d1b66d4', '角色列表', '/web/pc/sys/role/list', '/web/pc/sys/role/list', '329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', null, '3', '1', '2', 'jslb', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('a39eea77-e28a-44a6-a822-039e2a056b07', '系统管理', null, null, null, null, '1', '1', '1', 'xtgl', null, null, 'fa fa-fw fa-dashboard', '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('c0543953-2ff0-11e8-8f30-1c1b0d1b66d4', '其它', null, null, null, null, '1', '2', '1', 'qt', null, null, 'fa fa-camera-retro', '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('cf3b7a97-0afd-11e8-8934-1c1b0d1b66d4', '角色详情', '/web/pc/sys/role/detail', '/web/pc/sys/role/detail', '329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', null, '3', '4', '2', 'jsxq', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('d6f5c04e-30c8-11e8-8f30-1c1b0d1b66d4', '发送聊天信息', null, '/web/pc/other/onlineChat/sendChatMessage', '011bb51a-2ff1-11e8-8f30-1c1b0d1b66d4', null, '3', '1', '2', 'fsltxx', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('e3dd5e08-0ecb-11e8-9a31-1c1b0d1b66d4', '修改模块', '/web/pc/sys/module/modify', '/web/pc/sys/module/modify,/web/pc/sys/module/detail', '46683b3f-ef74-4952-8ce1-fe4e7ed7ec5a', null, '3', '4', '2', 'xgmk', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('ed44a661-0f07-11e8-9a31-1c1b0d1b66d4', '角色模块分配', '/web/pc/sys/role/menuAssignment', '/web/pc/sys/role/moduleAssignment,/web/pc/sys/role/roleModuleInfo', '329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', null, '3', '6', '2', 'jsmkfp', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');
INSERT INTO `module` VALUES ('f94ed30f-0afc-11e8-8934-1c1b0d1b66d4', '删除角色', null, '/web/pc/sys/role/delete', '329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', null, '3', '2', '2', 'scjs', null, null, null, '1', '2018-02-08 17:02:21', 'admin2018', null, null, '0');

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
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('4ca05b93-04d1-11e8-9073-1c1b0d1b66d4', '角色A', null, null, '0', '0', null, '2018-01-11 11:12:13', 'admin2018', null, null, '0');
INSERT INTO `role` VALUES ('55f6e415-04d1-11e8-9073-1c1b0d1b66d4', '角色B', null, null, '0', '0', null, '2018-01-11 11:12:13', 'admin2018', null, null, '0');
INSERT INTO `role` VALUES ('5d9c7e68-04d1-11e8-9073-1c1b0d1b66d4', '角色C', null, null, '0', '0', null, '2018-01-11 11:12:13', 'admin2018', null, null, '0');

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
INSERT INTO `role_module` VALUES ('add19326-30ed-11e8-8f30-1c1b0d1b66d4', '4ca05b93-04d1-11e8-9073-1c1b0d1b66d4', 'c0543953-2ff0-11e8-8f30-1c1b0d1b66d4');
INSERT INTO `role_module` VALUES ('add19417-30ed-11e8-8f30-1c1b0d1b66d4', '4ca05b93-04d1-11e8-9073-1c1b0d1b66d4', '011bb51a-2ff1-11e8-8f30-1c1b0d1b66d4');
INSERT INTO `role_module` VALUES ('add19474-30ed-11e8-8f30-1c1b0d1b66d4', '4ca05b93-04d1-11e8-9073-1c1b0d1b66d4', 'd6f5c04e-30c8-11e8-8f30-1c1b0d1b66d4');

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
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('7c90a994-9a26-4531-89eb-b1523c3ceb7c', '123qqq', 'asdasd1343', 'a', 'b', 'c', null, 'admin2018', null, '1', 'd', '0', '2018-01-28 10:53:35', 'admin2018', '2018-01-28 14:43:02', 'admin2018', '0');

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
INSERT INTO `user_role` VALUES ('a912e085-30ed-11e8-8f30-1c1b0d1b66d4', '7c90a994-9a26-4531-89eb-b1523c3ceb7c', '4ca05b93-04d1-11e8-9073-1c1b0d1b66d4');
