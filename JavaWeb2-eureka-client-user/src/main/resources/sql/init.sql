SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` varchar(255) NOT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `fcode` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `data_type` varchar(255) DEFAULT NULL,
  `key_code` varchar(255) DEFAULT NULL,
  `value_code` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `means` varchar(255) DEFAULT NULL,
  `universally` tinyint(4) DEFAULT NULL,
  `system_id` varchar(255) DEFAULT NULL,
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
INSERT INTO `dictionary` VALUES ('0d8a5557-65b0-4d3a-a649-5746370f03da', null, null, '0', 'del_flag', 'undelete', '0', 'yhfw', '1', '未删除', '0', null, '删除标记', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('1307a3b1-1eeb-4313-9fd2-0fb8fabad023', null, null, '0', 'user_role_strategy', 'intersection', '2', 'yhfw', '3', '交集', '0', null, '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('19ed8562-91d4-4f88-a80e-a8ce32dd3ebb', null, null, '0', 'user_role_strategy', 'union', '1', 'yhfw', '2', '并集', '0', null, '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('339e270e-cf23-4146-bd7f-02ad6d952738', null, null, '0', 'login_type', 'web', '1', 'yhfw', '2', '网页端', '0', null, '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('4cf06744-6b36-4986-afa8-c00901e7ea56', null, null, '0', 'login_type', 'android', '2', 'yhfw', '3', '安卓端', '0', null, '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('563f4dca-59cc-48a2-8d77-3d316958c6ea', null, null, '0', 'login_type', 'admin', '0', 'yhfw', '1', '管理员', '0', null, '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('7ffb55c0-9384-411e-89ab-9d9aefd877c7', null, null, '0', 'module_type', 'catalogue', '1', 'yhfw', '1', '目录', '0', null, '模块类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('9a641145-540c-4e4a-be56-3fea1b4de787', null, null, '0', 'module_type', 'function', '3', 'yhfw', '3', '功能', '0', null, '模块类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('a68296ea-e585-4681-a115-31d689220f51', null, null, '0', 'login_type', 'ios', '3', 'yhfw', '4', 'IOS端', '0', null, '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('b744cf0c-7307-4b57-a191-aa7935f8a9bb', null, null, '0', 'user_role_strategy', 'basedOnRole', '4', 'yhfw', '5', '以角色权限为准', '0', null, '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('bcc8ee2f-1be1-4c84-a4cb-69933eaae107', null, null, '0', 'module_type', 'menu', '2', 'yhfw', '2', '菜单', '0', null, '模块类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('cb2c19c1-3f7a-4d7a-a07d-4e459455ca73', null, null, '0', 'del_flag', 'delete', '1', 'yhfw', '2', '删除', '0', null, '删除标记', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('dc7bc4c8-d414-4832-9bea-e10b9f3915f2', null, null, '0', 'user_role_strategy', 'basedOnUser', '3', 'yhfw', '4', '以用户权限为准', '0', null, '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('fbd2a910-c611-47a4-b5c2-0d2baca7e0ad', null, null, '0', 'user_role_strategy', 'customize', '0', 'yhfw', '1', '自定义', '0', null, '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');

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
  `system_id` varchar(255) DEFAULT NULL,
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
INSERT INTO `module` VALUES ('05f56db2-cc8f-4d58-89b4-98a155d47010', '用户模块分配', null, '/web/sys/user/userModuleAssignment,/web/sys/user/userModuleInfo', '8c4f0ba6-105d-4d8b-8de5-3d88a24c8e0b', null, '3', '9', '3', 'sys:user:module:assignment', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('09816d73-e882-4590-a0c7-87836d580a04', '修改模块', null, '/web/sys/module/modify,/web/sys/module/getModuleIdAndNameList', '18375a1b-2f5e-4a78-80f0-981f19a891ca', null, '3', '5', '3', 'sys:module:modify', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('152ff9f8-e090-4e12-bd20-8c252cf70853', '删除角色', null, '/web/sys/role/delete', '9a709d31-4984-4239-a651-cfeccb9749a1', null, '3', '5', '3', 'sys:role:delete', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('18375a1b-2f5e-4a78-80f0-981f19a891ca', '模块管理', '/web/sys/module', null, '64bf3393-8a94-4162-b80e-877d7678167c', null, '2', '3', '2', 'sys:module', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('1bd42365-2558-48b2-a69f-23abd05b64f1', '查询角色', null, '/web/sys/role/list', '9a709d31-4984-4239-a651-cfeccb9749a1', null, '3', '3', '3', 'sys:role:list', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('30005a59-b103-49c2-a2dd-dd2dae637937', '新增用户', null, '/web/sys/user/add', '8c4f0ba6-105d-4d8b-8de5-3d88a24c8e0b', null, '3', '2', '3', 'sys:user:add', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('6294919a-655f-475c-8ed4-050bdba14797', '模块ID和名称列表', null, '/web/sys/module/getModuleIdAndNameList', '18375a1b-2f5e-4a78-80f0-981f19a891ca', null, '3', '6', '3', 'sys:module:getModuleIdAndNameList', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('64bf3393-8a94-4162-b80e-877d7678167c', '系统管理', null, null, null, null, '1', '1', '1', 'sys', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('6b98fde7-c887-45c1-ae38-f0920e6f3de8', '查询模块', null, '/web/sys/module/list', '18375a1b-2f5e-4a78-80f0-981f19a891ca', null, '3', '1', '3', 'sys:module:list', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('725e517b-756f-4f46-b519-2be71aa21ede', '新增角色', null, '/web/sys/role/add', '9a709d31-4984-4239-a651-cfeccb9749a1', null, '3', '1', '3', 'sys:role:add', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('72aee33b-180b-451a-9c4e-411a67329093', '删除用户', null, '/web/sys/user/delete', '8c4f0ba6-105d-4d8b-8de5-3d88a24c8e0b', null, '3', '4', '3', 'sys:user:delete', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('7d896336-04a1-4fe4-a821-6cbe340e4c2f', '角色详情', null, '/web/sys/role/detail', '9a709d31-4984-4239-a651-cfeccb9749a1', null, '3', '4', '3', 'sys:role:detail', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('8c4f0ba6-105d-4d8b-8de5-3d88a24c8e0b', '用户管理', '/web/sys/user', null, '64bf3393-8a94-4162-b80e-877d7678167c', null, '2', '1', '2', 'sys:user', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('95004702-6316-4dc1-84b3-c74b21d8db19', '用户角色分配', null, '/web/sys/user/userRoleAssignment,/web/sys/user/userRoleInfo', '8c4f0ba6-105d-4d8b-8de5-3d88a24c8e0b', null, '3', '7', '3', 'sys:user:role:assignment', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('9a709d31-4984-4239-a651-cfeccb9749a1', '角色管理', '/web/sys/role', null, '64bf3393-8a94-4162-b80e-877d7678167c', null, '2', '2', '2', 'sys:role', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('abe0b68f-beac-4c51-ac0e-9f80882cb44d', '角色模块分配', null, '/web/sys/role/roleModuleAssignment,/web/sys/role/roleModuleInfo', '9a709d31-4984-4239-a651-cfeccb9749a1', null, '3', '6', '3', 'sys:role:module:assignment', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('b681978d-ac0a-4eb1-968e-0f4a2c38cdb1', '用户角色信息', null, '/web/sys/user/userRoleInfo', '8c4f0ba6-105d-4d8b-8de5-3d88a24c8e0b', null, '3', '6', '3', 'sys:user:role', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('b82bf57c-bbf6-452d-ac95-acb8ed7425a8', '查询用户', null, '/web/sys/user/list', '8c4f0ba6-105d-4d8b-8de5-3d88a24c8e0b', null, '3', '1', '3', 'sys:user:list', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('bb479f15-2693-463a-8fa0-5938a5fa0b3d', '角色模块信息', null, '/web/sys/role/roleModuleInfo', '9a709d31-4984-4239-a651-cfeccb9749a1', null, '3', '7', '3', 'sys:role:module', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('c1bc76f6-f1a8-4e1b-a5bc-c5254d3df7d5', '用户详情', null, '/web/sys/user/detail', '8c4f0ba6-105d-4d8b-8de5-3d88a24c8e0b', null, '3', '5', '3', 'sys:user:detail', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('d216d200-415f-4de3-bd5d-2d1cf7f1137a', '删除模块', null, '/web/sys/module/delete', '18375a1b-2f5e-4a78-80f0-981f19a891ca', null, '3', '4', '3', 'sys:module:delete', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('d45cd7f7-b7e3-48b3-b201-1d5a952ab215', '修改用户', null, '/web/sys/user/modify,/web/sys/user/detail', '8c4f0ba6-105d-4d8b-8de5-3d88a24c8e0b', null, '3', '3', '3', 'sys:user:modify', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('d77d7d31-52d2-48c9-8926-88d5d1eb927d', '修改角色', null, '/web/sys/role/modify,/web/sys/role/detail', '9a709d31-4984-4239-a651-cfeccb9749a1', null, '3', '2', '3', 'sys:role:modifty', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('e566412e-5c8b-4e98-a694-60b721a55ae3', '模块详情', null, '/web/sys/module/detail', '18375a1b-2f5e-4a78-80f0-981f19a891ca', null, '3', '2', '3', 'sys:module:detail', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('f330b698-feda-418c-9de9-9b5b44c8ccb4', '用户模块信息', null, '/web/sys/user/userModuleInfo', '8c4f0ba6-105d-4d8b-8de5-3d88a24c8e0b', null, '3', '8', '3', 'sys:user:module', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('f365c0b5-82f2-45e4-9957-c52ae73757e2', '新增模块', null, '/web/sys/module/add,/web/sys/module/getModuleIdAndNameList', '18375a1b-2f5e-4a78-80f0-981f19a891ca', null, '3', '3', '3', 'sys:module:add', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');

-- ----------------------------
-- Table structure for `operation_log`
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `request_parameter` varchar(255) DEFAULT NULL,
  `request_ip_address` varchar(255) DEFAULT NULL,
  `request_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of operation_log
-- ----------------------------

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
  `system_id` varchar(255) DEFAULT NULL,
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
