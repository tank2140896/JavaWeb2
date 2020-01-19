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
INSERT INTO `dictionary` VALUES ('20190705214431115', null, 'del_flag', 'undelete', '0', 'yhfw', '1', '未删除', '删除标记', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431116', null, 'del_flag', 'delete', '1', 'yhfw', '2', '删除', '删除标记', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431117', null, 'login_type', 'admin', '0', 'yhfw', '1', '管理员', '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431118', null, 'login_type', 'web', '1', 'yhfw', '2', '网页端', '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431119', null, 'login_type', 'android', '2', 'yhfw', '3', '安卓端', '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431120', null, 'login_type', 'ios', '3', 'yhfw', '4', 'IOS端', '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431121', null, 'module_type', 'catalogue', '1', 'yhfw', '1', '目录', '模块类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431122', null, 'module_type', 'menu', '2', 'yhfw', '2', '菜单', '模块类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431123', null, 'module_type', 'function', '3', 'yhfw', '3', '功能', '模块类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431124', null, 'user_role_strategy', 'customize', '0', 'yhfw', '1', '自定义', '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431125', null, 'user_role_strategy', 'union', '1', 'yhfw', '2', '并集', '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431126', null, 'user_role_strategy', 'intersection', '2', 'yhfw', '3', '交集', '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431127', null, 'user_role_strategy', 'basedOnUser', '3', 'yhfw', '4', '以用户权限为准', '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('20190705214431128', null, 'user_role_strategy', 'basedOnRole', '4', 'yhfw', '5', '以角色权限为准', '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');

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
INSERT INTO `module` VALUES ('20190408140336803', '用户管理', '/yhfw/sys/user', null, '20190408140043147', null, '2', '1', '2', 'sys:user', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408140517989', '角色管理', '/yhfw/sys/role', null, '20190408140043147', null, '2', '2', '2', 'sys:role', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408140608316', '模块管理', '/yhfw/sys/module', null, '20190408140043147', null, '2', '3', '2', 'sys:module', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141509095', '查询用户', null, '/yhfw/sys/user/list', '20190408140336803', null, '3', '1', '3', 'sys:user:list', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141522656', '新增用户', null, '/yhfw/sys/user/add', '20190408140336803', null, '3', '2', '3', 'sys:user:add', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141538196', '修改用户', null, '/yhfw/sys/user/modify,/web/sys/user/detail', '20190408140336803', null, '3', '3', '3', 'sys:user:modify', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141553730', '删除用户', null, '/yhfw/sys/user/delete', '20190408140336803', null, '3', '4', '3', 'sys:user:delete', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702725', '用户详情', null, '/yhfw/sys/user/detail', '20190408140336803', null, '3', '5', '3', 'sys:user:detail', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702726', '用户角色信息', null, '/yhfw/sys/user/userRoleInfo', '20190408140336803', null, '3', '6', '3', 'sys:user:role', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702727', '用户角色分配', null, '/yhfw/sys/user/userRoleAssignment,/web/sys/user/userRoleInfo', '20190408140336803', null, '3', '7', '3', 'sys:user:role:assignment', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702728', '用户模块信息', null, '/yhfw/sys/user/userModuleInfo', '20190408140336803', null, '3', '8', '3', 'sys:user:module', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141702729', '用户模块分配', null, '/yhfw/sys/user/userModuleAssignment,/web/sys/user/userModuleInfo', '20190408140336803', null, '3', '9', '3', 'sys:user:module:assignment', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141815822', '新增角色', null, '/yhfw/sys/role/add', '20190408140517989', null, '3', '1', '3', 'sys:role:add', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141827174', '修改角色', null, '/yhfw/sys/role/modify,/web/sys/role/detail', '20190408140517989', null, '3', '2', '3', 'sys:role:modifty', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141852659', '查询角色', null, '/yhfw/sys/role/list', '20190408140517989', null, '3', '3', '3', 'sys:role:list', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141902486', '角色详情', null, '/yhfw/sys/role/detail', '20190408140517989', null, '3', '4', '3', 'sys:role:detail', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408141916424', '删除角色', null, '/yhfw/sys/role/delete', '20190408140517989', null, '3', '5', '3', 'sys:role:delete', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142025602', '角色模块分配', null, '/yhfw/sys/role/roleModuleAssignment,/yhfw/sys/role/roleModuleInfo', '20190408140517989', null, '3', '6', '3', 'sys:role:module:assignment', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142025603', '角色模块信息', null, '/yhfw/sys/role/roleModuleInfo', '20190408140517989', null, '3', '7', '3', 'sys:role:module', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142112268', '查询模块', null, '/yhfw/sys/module/list', '20190408140608316', null, '3', '1', '3', 'sys:module:list', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142122024', '模块详情', null, '/yhfw/sys/module/detail', '20190408140608316', null, '3', '2', '3', 'sys:module:detail', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142132292', '新增模块', null, '/yhfw/sys/module/add,/yhfw/sys/module/getModuleIdAndNameList', '20190408140608316', null, '3', '3', '3', 'sys:module:add', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142143289', '删除模块', null, '/yhfw/sys/module/delete', '20190408140608316', null, '3', '4', '3', 'sys:module:delete', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142154821', '修改模块', null, '/yhfw/sys/module/modify,/yhfw/sys/module/getModuleIdAndNameList', '20190408140608316', null, '3', '5', '3', 'sys:module:modify', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('20190408142154822', '模块ID和名称列表', null, '/yhfw/sys/module/getModuleIdAndNameList', '20190408140608316', null, '3', '6', '3', 'sys:module:getModuleIdAndNameList', null, null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');

-- ----------------------------
-- Table structure for `quartz`
-- ----------------------------
DROP TABLE IF EXISTS `quartz`;
CREATE TABLE `quartz` (
  `job_id` varchar(255) NOT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `param` varchar(255) DEFAULT NULL,
  `cron_expression` varchar(255) DEFAULT NULL,
  `execute_start_time` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_date` varchar(19) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_date` varchar(19) DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of quartz
-- ----------------------------

-- ----------------------------
-- Table structure for `quartz_blob_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `quartz_blob_triggers`;
CREATE TABLE `quartz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `quartz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `quartz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `quartz_calendars`
-- ----------------------------
DROP TABLE IF EXISTS `quartz_calendars`;
CREATE TABLE `quartz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(190) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for `quartz_cron_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `quartz_cron_triggers`;
CREATE TABLE `quartz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `quartz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `quartz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `quartz_fired_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `quartz_fired_triggers`;
CREATE TABLE `quartz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(190) DEFAULT NULL,
  `JOB_GROUP` varchar(190) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `quartz_job_details`
-- ----------------------------
DROP TABLE IF EXISTS `quartz_job_details`;
CREATE TABLE `quartz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for `quartz_locks`
-- ----------------------------
DROP TABLE IF EXISTS `quartz_locks`;
CREATE TABLE `quartz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_locks
-- ----------------------------

-- ----------------------------
-- Table structure for `quartz_paused_trigger_grps`
-- ----------------------------
DROP TABLE IF EXISTS `quartz_paused_trigger_grps`;
CREATE TABLE `quartz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for `quartz_scheduler_state`
-- ----------------------------
DROP TABLE IF EXISTS `quartz_scheduler_state`;
CREATE TABLE `quartz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for `quartz_simple_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `quartz_simple_triggers`;
CREATE TABLE `quartz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `quartz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `quartz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `quartz_simprop_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `quartz_simprop_triggers`;
CREATE TABLE `quartz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `quartz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `quartz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `quartz_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `quartz_triggers`;
CREATE TABLE `quartz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(190) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `quartz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `quartz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_triggers
-- ----------------------------

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
