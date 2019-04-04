SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module`  (
  `module_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `page_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `api_url` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `orders` int(11) NULL DEFAULT NULL,
  `module_type` int(11) NULL DEFAULT NULL,
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `create_date` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `updater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `del_flag` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`module_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('011bb51a-2ff1-11e8-8f30-1c1b0d1b66d4', '在线聊天室', '/web/pc/other/onlineChat/chat', NULL, 'c0543953-2ff0-11e8-8f30-1c1b0d1b66d4', NULL, 2, 1, 1, 'zxlts', NULL, NULL, 'fa fa-camera-retro', 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('063d7c24-03f9-11e8-a70c-00ff676e3062', '用户角色分配', '/web/pc/sys/user/roleAssignment', '/web/pc/sys/user/roleAssignment,/web/pc/sys/user/userRoleInfo', '07e7d8be-2b0d-48f8-8376-c1f22c31982c', NULL, 3, 6, 2, 'yhjsfp', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('07e7d8be-2b0d-48f8-8376-c1f22c31982c', '用户管理', '/web/pc/sys/user/list', NULL, 'a39eea77-e28a-44a6-a822-039e2a056b07', NULL, 2, 1, 1, 'yhgl', NULL, NULL, 'fa fa-camera-retro', 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('2582ad16-0c9a-11e8-a8e8-1c1b0d1b66d4', '模块列表', '/web/pc/sys/module/list', '/web/pc/sys/module/list', '46683b3f-ef74-4952-8ce1-fe4e7ed7ec5a', NULL, 3, 1, 2, 'mklb', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', '角色管理', '/web/pc/sys/role/list', NULL, 'a39eea77-e28a-44a6-a822-039e2a056b07', NULL, 2, 2, 1, 'jsgl', NULL, NULL, 'fa fa-fw fa-dashboard', 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('3dd79417-1df9-4781-844d-90b7c10fb1ff', '用户列表', '/web/pc/sys/user/list', '/web/pc/sys/user/list', '07e7d8be-2b0d-48f8-8376-c1f22c31982c', NULL, 3, 1, 2, 'yhlb', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('3fa14941-0110-11e8-9bec-00163e0e4f12', '删除用户', NULL, '/web/pc/sys/user/delete', '07e7d8be-2b0d-48f8-8376-c1f22c31982c', NULL, 3, 2, 2, 'scyh', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('43d41781-0ecc-11e8-9a31-1c1b0d1b66d4', '模块详情', '/web/pc/sys/module/detail', '/web/pc/sys/module/detail', '46683b3f-ef74-4952-8ce1-fe4e7ed7ec5a', NULL, 3, 5, 2, 'mkxq', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('44c1df40-0261-11e8-9bec-00163e0e4f12', '新增用户', '/web/pc/sys/user/add', '/web/pc/sys/user/add', '07e7d8be-2b0d-48f8-8376-c1f22c31982c', NULL, 3, 3, 2, 'xzyh', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('46683b3f-ef74-4952-8ce1-fe4e7ed7ec5a', '模块管理', '/web/pc/sys/module/list', NULL, 'a39eea77-e28a-44a6-a822-039e2a056b07', NULL, 2, 3, 1, 'mkgl', NULL, NULL, 'fa fa-fw fa-dashboard', 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('4bdc72ff-0afe-11e8-8934-1c1b0d1b66d4', '新增角色', '/web/pc/sys/role/add', '/web/pc/sys/role/add', '329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', NULL, 3, 5, 2, 'xzjs', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('5aa15800-0afd-11e8-8934-1c1b0d1b66d4', '修改角色', '/web/pc/sys/role/modify', '/web/pc/sys/role/modify,/web/pc/sys/role/detail', '329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', NULL, 3, 3, 2, 'xgjs', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('5fedf58e-0d5d-11e8-b414-1c1b0d1b66d4', '新增模块', '/web/pc/sys/module/add', '/web/pc/sys/module/add', '46683b3f-ef74-4952-8ce1-fe4e7ed7ec5a', NULL, 3, 3, 2, 'xzmk', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('64dd50c7-03e5-11e8-a70c-00ff676e3062', '修改用户', '/web/pc/sys/user/modify', '/web/pc/sys/user/modify,/web/pc/sys/user/detail', '07e7d8be-2b0d-48f8-8376-c1f22c31982c', NULL, 3, 4, 2, 'xgyh', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('7c2d216d-03ea-11e8-a70c-00ff676e3062', '用户详情', '/web/pc/sys/user/detail', '/web/pc/sys/user/detail', '07e7d8be-2b0d-48f8-8376-c1f22c31982c', NULL, 3, 5, 2, 'yhxq', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('840538ac-0cae-11e8-a8e8-1c1b0d1b66d4', '删除模块', NULL, '/web/pc/sys/module/delete', '46683b3f-ef74-4952-8ce1-fe4e7ed7ec5a', NULL, 3, 2, 2, 'scmk', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('872f4af0-0718-11e8-abf2-1c1b0d1b66d4', '角色列表', '/web/pc/sys/role/list', '/web/pc/sys/role/list', '329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', NULL, 3, 1, 2, 'jslb', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('a39eea77-e28a-44a6-a822-039e2a056b07', '系统管理', NULL, NULL, NULL, NULL, 1, 1, 1, 'xtgl', NULL, NULL, 'fa fa-fw fa-dashboard', 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('c0543953-2ff0-11e8-8f30-1c1b0d1b66d4', '其它', NULL, NULL, NULL, NULL, 1, 2, 1, 'qt', NULL, NULL, 'fa fa-camera-retro', 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('cf3b7a97-0afd-11e8-8934-1c1b0d1b66d4', '角色详情', '/web/pc/sys/role/detail', '/web/pc/sys/role/detail', '329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', NULL, 3, 4, 2, 'jsxq', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('d6f5c04e-30c8-11e8-8f30-1c1b0d1b66d4', '发送聊天信息', NULL, '/web/pc/other/onlineChat/sendChatMessage', '011bb51a-2ff1-11e8-8f30-1c1b0d1b66d4', NULL, 3, 1, 2, 'fsltxx', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('e3dd5e08-0ecb-11e8-9a31-1c1b0d1b66d4', '修改模块', '/web/pc/sys/module/modify', '/web/pc/sys/module/modify,/web/pc/sys/module/detail', '46683b3f-ef74-4952-8ce1-fe4e7ed7ec5a', NULL, 3, 4, 2, 'xgmk', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('ed44a661-0f07-11e8-9a31-1c1b0d1b66d4', '角色模块分配', '/web/pc/sys/role/menuAssignment', '/web/pc/sys/role/moduleAssignment,/web/pc/sys/role/roleModuleInfo', '329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', NULL, 3, 6, 2, 'jsmkfp', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);
INSERT INTO `module` VALUES ('f94ed30f-0afc-11e8-8934-1c1b0d1b66d4', '删除角色', NULL, '/web/pc/sys/role/delete', '329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', NULL, 3, 2, 2, 'scjs', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin2018', NULL, NULL, 0);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT 0,
  `type` int(11) NULL DEFAULT 0,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `updater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `del_flag` tinyint(4) NULL DEFAULT 0,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role_module
-- ----------------------------
DROP TABLE IF EXISTS `role_module`;
CREATE TABLE `role_module`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `module_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `portrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT 0,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT 0,
  `create_date` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `updater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `del_flag` tinyint(4) NULL DEFAULT 0,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_module
-- ----------------------------
DROP TABLE IF EXISTS `user_module`;
CREATE TABLE `user_module`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `module_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `strategy` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
