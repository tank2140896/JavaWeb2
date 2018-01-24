/*
Navicat MySQL Data Transfer

Source Server         : demo1
Source Server Version : 50505
Source Host           : 47.96.157.130:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-01-24 23:46:39
*/

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
INSERT INTO `module` VALUES ('07e7d8be-2b0d-48f8-8376-c1f22c31982c', '用户管理', '/web/sys/user', null, 'a39eea77-e28a-44a6-a822-039e2a056b07', null, '2', '1', '1', 'yhgl', null, null, 'fa fa-camera-retro', '2018-01-06 15:44:48', 'admin', null, null, '0');
INSERT INTO `module` VALUES ('329c3b64-4aed-4b8c-aa0d-dc7ecf01afec', '角色管理', '/b', null, 'a39eea77-e28a-44a6-a822-039e2a056b07', null, '2', '2', '1', 'jsgl', null, null, 'fa fa-fw fa-dashboard', '2018-01-06 15:46:04', 'admin', null, null, '0');
INSERT INTO `module` VALUES ('3dd79417-1df9-4781-844d-90b7c10fb1ff', '用户列表', '/web/sys/user/list', '/web/sys/user/list', '07e7d8be-2b0d-48f8-8376-c1f22c31982c', null, '3', '1', '2', 'yhlb', null, null, null, '2018-01-06 15:49:31', 'admin', null, null, '0');
INSERT INTO `module` VALUES ('3fa14941-0110-11e8-9bec-00163e0e4f12', '删除用户', null, '/web/sys/user/delete', '07e7d8be-2b0d-48f8-8376-c1f22c31982c', null, '3', '2', '2', 'scyh', null, null, null, '2018-01-06 15:49:31', 'admin', null, null, '0');
INSERT INTO `module` VALUES ('46683b3f-ef74-4952-8ce1-fe4e7ed7ec5a', '模块管理', '/c', null, 'a39eea77-e28a-44a6-a822-039e2a056b07', null, '2', '3', '1', 'mkgl', null, null, 'fa fa-fw fa-dashboard', '2018-01-06 15:47:11', 'admin', null, null, '0');
INSERT INTO `module` VALUES ('74b14b3f-f2b6-11e7-818e-00ff676e3062', '菜单测试', null, null, null, null, '1', '2', '1', 'cdcsa', null, null, 'fa fa-fw fa-dashboard', '2018-01-06 15:53:18', 'admin', null, null, '0');
INSERT INTO `module` VALUES ('a39eea77-e28a-44a6-a822-039e2a056b07', '系统管理', null, null, null, null, '1', '1', '1', 'xtgl', null, null, 'fa fa-fw fa-dashboard', '2018-01-06 15:35:36', 'admin', null, null, '0');
INSERT INTO `module` VALUES ('ae7144e5-f2b6-11e7-818e-00ff676e3062', '子项A', '/d', null, '74b14b3f-f2b6-11e7-818e-00ff676e3062', null, '2', '1', '1', 'zxa', null, null, 'fa fa-fw fa-dashboard', '2018-01-06 15:54:27', 'admin', null, null, '0');
INSERT INTO `module` VALUES ('d657b637-f2b6-11e7-818e-00ff676e3062', '子项B', '/e', null, '74b14b3f-f2b6-11e7-818e-00ff676e3062', null, '2', '2', '1', 'zxb', null, null, 'fa fa-fw fa-dashboard', '2018-01-06 15:55:08', 'admin', null, null, '0');

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
  `type` int(11) DEFAULT NULL,
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
INSERT INTO `user` VALUES ('a', 'a', 'a', 'a', null, null, null, null, null, '0', null, '0', '2018-01-21 12:11:33', null, null, null, '0');
INSERT INTO `user` VALUES ('b', 'b', 'b', 'b', null, null, null, null, null, '0', null, '0', '2018-01-21 11:11:22', null, null, null, '0');
INSERT INTO `user` VALUES ('c', 'c', 'c', 'c', null, null, null, null, null, '0', null, '0', '2018-01-21 09:22:34', null, null, null, '0');
INSERT INTO `user` VALUES ('d', 'd', 'd', 'd', null, null, null, null, null, '0', null, '0', '2018-01-21 12:11:33', null, null, null, '0');
INSERT INTO `user` VALUES ('e', 'e', 'e', 'e', null, null, null, null, null, '0', null, '0', '2018-01-21 12:11:33', null, null, null, '0');
INSERT INTO `user` VALUES ('f', 'f', 'f', 'f', null, null, null, null, null, '0', null, '0', '2018-01-21 12:11:33', null, null, null, '0');
INSERT INTO `user` VALUES ('g', 'g', 'g', 'g', null, null, null, null, null, '0', null, '0', '2018-01-21 12:11:33', null, null, null, '0');
INSERT INTO `user` VALUES ('h', 'h', 'h', 'h', null, null, null, null, null, '0', null, '0', '2018-01-21 12:11:33', null, null, null, '0');
INSERT INTO `user` VALUES ('i', 'i', 'i', 'i', null, null, null, null, null, '0', null, '0', '2018-01-21 12:11:33', null, null, null, '0');
INSERT INTO `user` VALUES ('j', 'j', 'j', 'j', null, null, null, null, null, '0', null, '0', '2018-01-21 12:11:33', null, null, null, '0');
INSERT INTO `user` VALUES ('k', 'k', 'k', 'k', null, null, null, null, null, '0', null, '0', '2018-01-21 12:11:33', null, null, null, '0');

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
