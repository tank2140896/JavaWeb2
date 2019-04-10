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
INSERT INTO `module` VALUES ('20190408140043147', '系统管理', NULL, NULL, NULL, NULL, 1, 1, 1, 'xtgl', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408140336803', '用户管理', NULL, NULL, '20190408140043147', NULL, 2, 1, 1, 'yhgl', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408140517989', '角色管理', NULL, NULL, '20190408140043147', NULL, 2, 2, 1, 'jsgl', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408140608316', '模块管理', NULL, NULL, '20190408140043147', NULL, 2, 3, 1, 'mkgl', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408141509095', '查询用户', NULL, '/web/pc/sys/user/list', '20190408140336803', NULL, 3, 1, 2, 'yhlb', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408141522656', '新增用户', NULL, '/web/pc/sys/user/add', '20190408140336803', NULL, 3, 3, 2, 'xzyh', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408141538196', '修改用户', NULL, '/web/pc/sys/user/modify,/web/pc/sys/user/detail', '20190408140336803', NULL, 3, 4, 2, 'xgyh', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408141553730', '删除用户', NULL, '/web/pc/sys/user/delete', '20190408140336803', NULL, 3, 2, 2, 'scyh', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408141702724', '用户详情', NULL, '/web/pc/sys/user/detail', '20190408140336803', NULL, 3, 5, 2, 'yhxq', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408141740545', '用户角色分配', NULL, '/web/pc/sys/user/roleAssignment,/web/pc/sys/user/userRoleInfo', '20190408140336803', NULL, 3, 6, 2, 'yhjsfp', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408141815822', '新增角色', NULL, '/web/pc/sys/role/add', '20190408140517989', NULL, 3, 5, 2, 'xzjs', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408141827174', '修改角色', NULL, '/web/pc/sys/role/modify,/web/pc/sys/role/detail', '20190408140517989', NULL, 3, 3, 2, 'xgjs', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408141852659', '查询角色', NULL, '/web/pc/sys/role/list', '20190408140517989', NULL, 3, 1, 2, 'jslb', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408141902486', '角色详情', NULL, '/web/pc/sys/role/detail', '20190408140517989', NULL, 3, 4, 2, 'jsxq', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408141916424', '删除角色', NULL, '/web/pc/sys/role/delete', '20190408140517989', NULL, 3, 2, 2, 'scjs', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408142025602', '角色模块分配', NULL, '/web/pc/sys/role/moduleAssignment,/web/pc/sys/role/roleModuleInfo', '20190408140517989', NULL, 3, 6, 2, 'jsmkfp', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408142112268', '查询模块', NULL, '/web/pc/sys/module/list', '20190408140608316', NULL, 3, 1, 2, 'mklb', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408142122024', '模块详情', NULL, '/web/pc/sys/module/detail', '20190408140608316', NULL, 3, 5, 2, 'mkxq', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408142132292', '新增模块', NULL, '/web/pc/sys/module/add', '20190408140608316', NULL, 3, 3, 2, 'xzmk', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408142143289', '删除模块', NULL, '/web/pc/sys/module/delete', '20190408140608316', NULL, 3, 2, 2, 'scmk', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);
INSERT INTO `module` VALUES ('20190408142154821', '修改模块', NULL, '/web/pc/sys/module/modify,/web/pc/sys/module/detail', '20190408140608316', NULL, 3, 4, 2, 'xgmk', NULL, NULL, NULL, 1, '2018-02-08 17:02:21', 'admin123456', NULL, NULL, 0);

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
