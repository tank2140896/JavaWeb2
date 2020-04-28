SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `data_permission`
-- ----------------------------
DROP TABLE IF EXISTS `data_permission`;
CREATE TABLE `data_permission` (
  `id` varchar(255) NOT NULL,
  `exclude_field` varchar(1000) NOT NULL,
  `interfaces_id` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_date` varchar(19) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_date` varchar(19) DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of data_permission
-- ----------------------------
INSERT INTO `data_permission` VALUES ('202004281340365581', 'userName,status', '202004271148525481', null, '2020-04-28 13:40:36', 'admin123456', null, null, '0');
INSERT INTO `data_permission` VALUES ('202004281340365901', 'createDate', '202004271148525481', null, '2020-04-28 13:40:36', 'admin123456', null, null, '0');

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
INSERT INTO `dictionary` VALUES ('202003282118348411', null, null, '0', 'del_flag', 'undelete', '0', 'yhfw', '1', '未删除', '0', null, '删除标记', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('202003282118348471', null, null, '0', 'user_role_strategy', 'intersection', '2', 'yhfw', '3', '交集', '0', null, '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('202003282118348501', null, null, '0', 'user_role_strategy', 'union', '1', 'yhfw', '2', '并集', '0', null, '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('202003282118348541', null, null, '0', 'login_type', 'web', '1', 'yhfw', '2', '网页端', '0', null, '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('202003282118348571', null, null, '0', 'login_type', 'android', '2', 'yhfw', '3', '安卓端', '0', null, '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('202003282119121371', null, null, '0', 'login_type', 'admin', '0', 'yhfw', '1', '管理员', '0', null, '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('202003282119121411', null, null, '0', 'module_type', 'catalogue', '1', 'yhfw', '1', '目录', '0', null, '模块类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('202003282119121471', null, null, '0', 'module_type', 'function', '3', 'yhfw', '3', '功能', '0', null, '模块类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('202003282119121511', null, null, '0', 'login_type', 'ios', '3', 'yhfw', '4', 'IOS端', '0', null, '登录类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('202003282119121561', null, null, '0', 'user_role_strategy', 'basedOnRole', '4', 'yhfw', '5', '以角色权限为准', '0', null, '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('202003282119469071', null, null, '0', 'module_type', 'menu', '2', 'yhfw', '2', '菜单', '0', null, '模块类型', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('202003282119469151', null, null, '0', 'del_flag', 'delete', '1', 'yhfw', '2', '删除', '0', null, '删除标记', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('202003282119469221', null, null, '0', 'user_role_strategy', 'basedOnUser', '3', 'yhfw', '4', '以用户权限为准', '0', null, '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `dictionary` VALUES ('202003282119469301', null, null, '0', 'user_role_strategy', 'customize', '0', 'yhfw', '1', '自定义', '0', null, '用户角色策略', '2018-02-08 17:02:21', 'admin123456', null, null, '0');

-- ----------------------------
-- Table structure for `interfaces`
-- ----------------------------
DROP TABLE IF EXISTS `interfaces`;
CREATE TABLE `interfaces` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `times` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `counts` int(11) DEFAULT NULL,
  `data_permission` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_date` varchar(19) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_date` varchar(19) DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of interfaces
-- ----------------------------
INSERT INTO `interfaces` VALUES ('202004141620407331', '请求参数错误接口', '/requestParameterError', 'GET, POST, PUT, DELETE', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407371', '请求参数缺失接口', '/requestParameterLost', 'GET, POST, PUT, DELETE', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407391', '获取服务器时间接口', '/getServeTime', 'GET', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407411', '请求接口不存在接口', '/notFound', 'GET, POST, PUT, DELETE', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407431', '用户登录接口', '/webLogin', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407441', '请求失效接口', '/invalidRequest', 'GET, POST, PUT, DELETE', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407451', '没有权限接口', '/noAuthory', 'GET, POST, PUT, DELETE', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407461', '系统异常接口', '/internalError', 'GET, POST, PUT, DELETE', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407481', '查询字典接口', '/web/sys/dictionary/list', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407501', '删除字典接口', '/web/sys/dictionary/delete/{dictionaryId}', 'DELETE', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407511', '新增字典接口', '/web/sys/dictionary/add', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407531', '修改字典接口', '/web/sys/dictionary/modify', 'PUT', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407551', '字典详情接口', '/web/sys/dictionary/detail/{dictionaryId}', 'GET', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407561', '获取redis中的token信息接口', '/web/loginAccess/getRedisTokenData', 'GET', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407571', '用户登出接口', '/web/loginAccess/logout', 'GET', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407591', '获得字典信息接口', '/web/loginAccess/getDictionary', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407601', '获取模块ID和模块名称列表接口', '/web/sys/module/getModuleIdAndNameList/{moduleType}', 'GET', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407621', '查询模块接口', '/web/sys/module/list', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407631', '新增模块接口', '/web/sys/module/add', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407651', '修改模块接口', '/web/sys/module/modify', 'PUT', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407661', '模块详情接口', '/web/sys/module/detail/{moduleId}', 'GET', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407671', '删除模块接口', '/web/sys/module/delete/{moduleId}', 'DELETE', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407691', '查询操作日志接口', '/web/sys/operationLog/list', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407701', '角色模块分配接口', '/web/sys/role/roleModuleAssignment/{roleId}', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407721', '角色详情接口', '/web/sys/role/detail/{roleId}', 'GET', '5', '秒', '10', '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407731', '新增角色接口', '/web/sys/role/add', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407741', '查询角色接口', '/web/sys/role/list', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407761', '修改角色接口', '/web/sys/role/modify', 'PUT', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407781', '角色模块信息接口', '/web/sys/role/roleModuleInfo/{roleId},/web/sys/role/roleModuleInfo', 'GET', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407791', '删除角色接口', '/web/sys/role/delete/{roleId}', 'DELETE', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407811', '日程列表接口', '/web/sys/schedule/list', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407821', '保存日程接口', '/web/sys/schedule/add', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407841', null, '/eurekaTest', 'GET', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407851', '用户角色分配接口', '/web/sys/user/userRoleAssignment/{userId}', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407871', '用户模块分配接口', '/web/sys/user/userModuleAssignment/{userId}', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407881', '新增用户接口', '/web/sys/user/add', 'POST', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407901', '用户角色信息接口', '/web/sys/user/userRoleInfo/{userId},/web/sys/user/userRoleInfo', 'GET', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407921', '用户模块信息接口', '/web/sys/user/userModuleInfo/{userId},/web/sys/user/userModuleInfo', 'GET', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407941', '修改用户接口', '/web/sys/user/modify', 'PUT', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407951', '用户详情接口', '/web/sys/user/detail/{userId}', 'GET', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407981', '删除用户接口', '/web/sys/user/delete/{userId}', 'DELETE', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620407991', null, '/swagger-resources/configuration/security', '', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620408001', null, '/swagger-resources', '', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620408021', null, '/swagger-resources/configuration/ui', '', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141620408041', null, '/error', '', null, null, null, '0', null, '2020-04-14 16:20:40', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004141625283561', null, '/test', 'GET', null, null, null, '0', null, '2020-04-14 16:25:28', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004151025123271', '修改接口接口', '/web/sys/interfaces/modify', 'PUT', null, null, null, '0', null, '2020-04-15 10:25:12', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004151025123301', '接口详情接口', '/web/sys/interfaces/detail/{interfacesId}', 'GET', null, null, null, '0', null, '2020-04-15 10:25:12', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004231525337771', '数据权限分配接口', '/web/sys/interfaces/dataPermissionAssignment/{interfacesId}', 'POST', null, null, null, '0', null, '2020-04-23 15:25:33', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004270931168501', '用户下线接口', '/web/sys/onlineUser/offline/{key}', 'GET', null, null, null, '0', null, '2020-04-27 09:31:16', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004270931168531', '在线用户列表接口', '/web/sys/onlineUser/list', 'POST', null, null, null, '0', null, '2020-04-27 09:31:16', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004271148277281', '接口列表接口', '/web/sys/interfaces/list', 'POST', null, null, null, '0', null, '2020-04-27 11:48:27', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004271148525481', '查询用户接口', '/web/sys/user/list', 'POST', null, null, null, '1', null, '2020-04-27 11:48:52', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');
INSERT INTO `interfaces` VALUES ('202004281006269811', '用户角色数据权限接口', '/web/sys/interfaces/userRoleDataPermission', 'POST', null, null, null, '0', null, '2020-04-28 10:06:26', 'admin123456', '2020-04-28 13:47:49', 'admin123456', '0');

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
INSERT INTO `module` VALUES ('202003282108350611', '系统管理', null, null, null, null, '1', '1', '1', 'sys', null, '总管理系统', null, 'fa fa-window-maximize', '1', '2018-02-08 17:02:21', 'admin123456', '2020-04-09 14:16:11', 'admin123456', '0');
INSERT INTO `module` VALUES ('202003282108350781', '用户管理', '/web/sys/user', null, '202003282108350611', null, '2', '1', '2', 'sys:user', null, '总管理系统', null, 'fa fa-user-circle', '1', '2018-02-08 17:02:21', 'admin123456', '2020-04-09 14:18:54', 'admin123456', '0');
INSERT INTO `module` VALUES ('202003282108350791', '角色管理', '/web/sys/role', null, '202003282108350611', null, '2', '2', '2', 'sys:role', null, '总管理系统', null, 'fa fa-address-card', '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282108350801', '模块管理', '/web/sys/module', null, '202003282108350611', null, '2', '3', '2', 'sys:module', null, '总管理系统', null, 'fa fa-modx', '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282108350802', '字典管理', '/web/sys/dictionary', null, '202003282108350611', null, '2', '4', '2', 'sys:dictionary', null, '总管理系统', null, 'fa fa-book', '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282108350803', '操作日志管理', '/web/sys/operationLog', null, '202003282108350611', null, '2', '5', '2', 'sys:operationLog', null, '总管理系统', null, 'fa fa-wpforms', '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282108350804', '日程管理', '/web/sys/schedule', null, '202003282108350611', null, '2', '6', '2', 'sys:schedule', null, '总管理系统', null, 'fa fa-calendar', '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282108350805', '接口管理', '/web/sys/interfaces', null, '202003282108350611', null, '2', '7', '2', 'sys:interfaces', null, '总管理系统', null, 'fa fa-cogs', '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282108350806', '在线聊天管理', '/web/sys/onlineChat', null, '202003282108350611', null, '2', '8', '2', 'sys:onlineChat', null, '总管理系统', null, 'fa fa-snapchat-ghost', '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282108350807', '在线用户管理', '/web/sys/onlineUser', null, '202003282108350611', null, '3', '9', '2', 'sys:onlineUser', null, '总管理系统', null, 'fa fa-id-card-o', '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282108350831', '用户模块分配', '/web/sys/user/userModuleAssignment', '/web/sys/user/userModuleAssignment,/web/sys/user/userModuleInfo', '202003282108350781', null, '3', '7', '3', 'sys:user:module:assignment', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282108350861', '修改模块', '/web/sys/module/modify', '/web/sys/module/modify,/web/sys/module/getModuleIdAndNameList', '202003282108350801', null, '3', '5', '3', 'sys:module:modify', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282108350891', '删除角色', null, '/web/sys/role/delete', '202003282108350791', null, '3', '5', '3', 'sys:role:delete', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282108350931', '查询角色', '/web/sys/role/list', '/web/sys/role/list', '202003282108350791', null, '3', '3', '3', 'sys:role:list', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282108350991', '新增用户', '/web/sys/user/add', '/web/sys/user/add', '202003282108350781', null, '3', '2', '3', 'sys:user:add', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282115442191', '查询模块', '/web/sys/module/list', '/web/sys/module/list', '202003282108350801', null, '3', '1', '3', 'sys:module:list', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282115442421', '角色详情', '/web/sys/role/detail', '/web/sys/role/detail', '202003282108350791', null, '3', '4', '3', 'sys:role:detail', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282115442491', '删除模块', null, '/web/sys/module/delete', '202003282108350801', null, '3', '4', '3', 'sys:module:delete', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282115442541', '修改角色', '/web/sys/role/modify', '/web/sys/role/modify,/web/sys/role/detail', '202003282108350791', null, '3', '2', '3', 'sys:role:modifty', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282115442571', '模块详情', '/web/sys/module/detail', '/web/sys/module/detail', '202003282108350801', null, '3', '2', '3', 'sys:module:detail', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282116237201', '新增角色', '/web/sys/role/add', '/web/sys/role/add', '202003282108350791', null, '3', '1', '3', 'sys:role:add', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282116237411', '用户角色分配', '/web/sys/user/userRoleAssignment', '/web/sys/user/userRoleAssignment,/web/sys/user/userRoleInfo', '202003282108350781', null, '3', '6', '3', 'sys:user:role:assignment', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282116237491', '修改用户', '/web/sys/user/modify', '/web/sys/user/modify,/web/sys/user/detail', '202003282108350781', null, '3', '3', '3', 'sys:user:modify', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282116593201', '角色模块分配', '/web/sys/role/roleModuleAssignment', '/web/sys/role/roleModuleAssignment,/web/sys/role/roleModuleInfo', '202003282108350791', null, '3', '6', '3', 'sys:role:module:assignment', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282116593381', '删除用户', null, '/web/sys/user/delete', '202003282108350781', null, '3', '4', '3', 'sys:user:delete', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282116593521', '新增模块', '/web/sys/module/add', '/web/sys/module/add,/web/sys/module/getModuleIdAndNameList', '202003282108350801', null, '3', '3', '3', 'sys:module:add', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355621', '查询用户', '/web/sys/user/list', '/web/sys/user/list', '202003282108350781', null, '3', '1', '3', 'sys:user:list', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355941', '用户详情', '/web/sys/user/detail', '/web/sys/user/detail', '202003282108350781', null, '3', '5', '3', 'sys:user:detail', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355942', '新增字典', '/web/sys/dictionary/add', '/web/sys/dictionary/add', '202003282108350802', null, '3', '1', '3', 'sys:dictionary:add', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355943', '修改字典', '/web/sys/dictionary/modify', '/web/sys/dictionary/modify,/web/sys/dictionary/detail', '202003282108350802', null, '3', '2', '3', 'sys:dictionary:modify', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355944', '查询字典', '/web/sys/dictionary/list', '/web/sys/dictionary/list', '202003282108350802', null, '3', '3', '3', 'sys:dictionary:list', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355945', '删除字典', null, '/web/sys/dictionary/delete', '202003282108350802', null, '3', '4', '3', 'sys:dictionary:delete', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355946', '字典详情', '/web/sys/dictionary/detail', '/web/sys/dictionary/detail', '202003282108350802', null, '3', '5', '3', 'sys:dictionary:detail', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355947', '查询操作日志', '/web/sys/operationLog/list', '/web/sys/operationLog/list', '202003282108350803', null, '3', '1', '3', 'sys:operationLog:list', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355948', '查询日程', '/web/sys/schedule/list', '/web/sys/schedule/list', '202003282108350804', null, '3', '1', '3', 'sys:schedule:list', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355949', '保存日程', null, '/web/sys/schedule/add', '202003282108350804', null, '3', '2', '3', 'sys:schedule:add', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355950', '查询接口', '/web/sys/interfaces/list', '/web/sys/interfaces/list', '202003282108350805', null, '3', '1', '3', 'sys:interfaces:list', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355951', '接口详情', '/web/sys/interfaces/detail', '/web/sys/interfaces/detail', '202003282108350805', null, '3', '2', '3', 'sys:interfaces:detail', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355952', '修改接口', '/web/sys/interfaces/modify', '/web/sys/interfaces/modify,/web/sys/interfaces/detail', '202003282108350805', null, '3', '3', '3', 'sys:interfaces:modify', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355953', '数据权限分配', '/web/sys/interfaces/dataPermissionAssignment', '/web/sys/interfaces/userRoleDataPermission,/web/sys/interfaces/dataPermissionAssignment', '202003282108350805', null, '3', '4', '3', 'sys:interfaces:dataPermissionAssignment', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202003282117355954', '在线用户列表', null, '/web/sys/onlineUser/list', '202003282108350807', null, '3', '1', '3', 'sys:onlineUser:list', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `module` VALUES ('202004212010126742', '用户下线接口', null, '/web/sys/onlineUser/offline', '202003282108350807', null, '3', '2', '3', 'sys:onlineUser:offline', null, '总管理系统', null, null, '1', '2018-02-08 17:02:21', 'admin123456', null, null, '0');

-- ----------------------------
-- Table structure for `operation_log`
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `request_method` varchar(255) DEFAULT NULL,
  `request_parameter` varchar(255) DEFAULT NULL,
  `request_ip_address` varchar(255) DEFAULT NULL,
  `request_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of operation_log
-- ----------------------------
INSERT INTO `operation_log` VALUES ('202004100931410091', 'admin123456', 'http://client1:2001/web/sys/dictionary/delete/202004100928532131', 'DELETE', '[202004100928532131]', '10.131.9.221', '2020-04-10 09:31:41', null);
INSERT INTO `operation_log` VALUES ('202004100932106291', 'admin123456', 'http://client1:2001/web/sys/dictionary/add', 'POST', '[com.javaweb.web.po.Dictionary@652d8f47, com.javaweb.web.eo.TokenData@e34347e]', '10.131.9.221', '2020-04-10 09:32:10', null);
INSERT INTO `operation_log` VALUES ('202004100932225471', 'admin123456', 'http://client1:2001/web/sys/dictionary/delete/202004100932107541', 'DELETE', '[202004100932107541]', '10.131.9.221', '2020-04-10 09:32:22', null);

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
INSERT INTO `role` VALUES ('202004052117534261', '角色1', null, null, '0', '0', null, '备注1', '2019-12-21 11:22:33', 'admin123456', '2020-04-10 13:13:14', 'admin123456', '0');
INSERT INTO `role` VALUES ('202004052117534262', '角色2', null, null, '0', '0', null, '备注2', '2019-12-21 11:22:33', 'admin123456', null, null, '0');
INSERT INTO `role` VALUES ('202004052117534263', '角色3', null, null, '0', '0', null, '备注3', '2019-12-21 11:22:33', 'admin123456', null, null, '0');

-- ----------------------------
-- Table structure for `role_data`
-- ----------------------------
DROP TABLE IF EXISTS `role_data`;
CREATE TABLE `role_data` (
  `id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  `data_permission_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_data
-- ----------------------------
INSERT INTO `role_data` VALUES ('202004281340365911', '202004052117534261', '202004281340365901');

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
INSERT INTO `role_module` VALUES ('202004271404222251', '202004052117534261', '202003282108350611');
INSERT INTO `role_module` VALUES ('202004271404222261', '202004052117534261', '202003282108350781');
INSERT INTO `role_module` VALUES ('202004271404222281', '202004052117534261', '202003282117355621');
INSERT INTO `role_module` VALUES ('202004271404222291', '202004052117534261', '202003282108350991');
INSERT INTO `role_module` VALUES ('202004271404222311', '202004052117534261', '202003282116237491');
INSERT INTO `role_module` VALUES ('202004271404222331', '202004052117534261', '202003282116593381');
INSERT INTO `role_module` VALUES ('202004271404222341', '202004052117534261', '202003282117355941');
INSERT INTO `role_module` VALUES ('202004271404222361', '202004052117534261', '202003282108350805');
INSERT INTO `role_module` VALUES ('202004271404222371', '202004052117534261', '202003282117355950');
INSERT INTO `role_module` VALUES ('202004271404222391', '202004052117534261', '202003282117355951');
INSERT INTO `role_module` VALUES ('202004271404222401', '202004052117534261', '202003282117355952');
INSERT INTO `role_module` VALUES ('202004271404222421', '202004052117534261', '202003282117355953');
INSERT INTO `role_module` VALUES ('202004271404222431', '202004052117534261', '202003282116237411');
INSERT INTO `role_module` VALUES ('202004271404222451', '202004052117534261', '202003282108350831');
INSERT INTO `role_module` VALUES ('202004271404222471', '202004052117534261', '202003282108350806');

-- ----------------------------
-- Table structure for `schedule`
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `id` varchar(255) NOT NULL,
  `schedule_date` varchar(255) DEFAULT NULL,
  `schedule_type` int(11) DEFAULT NULL COMMENT '1:周末;2:正常;3:节假日;4:休假',
  `remark` varchar(255) DEFAULT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES ('202004261431244861', '2020-03-30', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431244871', '2020-03-31', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431244881', '2020-04-01', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431244901', '2020-04-02', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431244911', '2020-04-03', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431244931', '2020-04-04', '3', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431244941', '2020-04-05', '3', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431244951', '2020-04-06', '3', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431244961', '2020-04-07', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431244981', '2020-04-08', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431244991', '2020-04-09', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245011', '2020-04-10', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245031', '2020-04-11', '1', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245051', '2020-04-12', '1', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245061', '2020-04-13', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245081', '2020-04-14', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245091', '2020-04-15', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245111', '2020-04-16', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245131', '2020-04-17', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245141', '2020-04-18', '1', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245151', '2020-04-19', '1', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245171', '2020-04-20', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245191', '2020-04-21', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245201', '2020-04-22', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245211', '2020-04-23', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245231', '2020-04-24', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245251', '2020-04-25', '1', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245261', '2020-04-26', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245281', '2020-04-27', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245291', '2020-04-28', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245301', '2020-04-29', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245321', '2020-04-30', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245331', '2020-05-01', '3', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245351', '2020-05-02', '3', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245361', '2020-05-03', '3', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245381', '2020-05-04', '3', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245401', '2020-05-05', '3', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245411', '2020-05-06', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245431', '2020-05-07', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245441', '2020-05-08', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245461', '2020-05-09', '2', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');
INSERT INTO `schedule` VALUES ('202004261431245471', '2020-05-10', '1', null, '2020-04-26 14:31:24', 'admin123456', null, null, '0');

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
INSERT INTO `user` VALUES ('202004041557207981', 'username_9', 'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78', '用户_1', 'user14@qq.com', '11111111111', null, 'admin123456', null, '1', 'dad', '0', '2020-04-04 15:57:20', 'admin123456', null, null, '0');
INSERT INTO `user` VALUES ('202004041600480641', 'username_8', 'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78', '用户_2', 'user13@qq.com', '11111111111', null, 'admin123456', null, '1', 'dadasd', '0', '2020-04-04 16:00:48', 'admin123456', null, null, '0');
INSERT INTO `user` VALUES ('202004041601405691', 'username_2', 'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78', '用户_3', 'user12@qq.com', '11111111111', null, 'admin123456', null, '1', '备注', '0', '2020-04-04 16:01:40', 'admin123456', null, null, '0');
INSERT INTO `user` VALUES ('202004041609538011', 'username_1', 'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78', '用户_4', 'user11@qq.com', '2222222222', null, 'admin123456', null, '1', '这是用户1', '0', '2020-04-04 16:09:53', 'admin123456', '2020-04-04 20:03:18', 'admin123456', '0');
INSERT INTO `user` VALUES ('202004041609538012', 'username_3', 'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78', '用户_5', 'user10@qq.com', '11111111111', null, 'admin123456', null, '1', 'adad', '0', '2019-12-21 11:22:33', 'admin123456', null, null, '0');
INSERT INTO `user` VALUES ('202004041609538013', 'username_4', 'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78', '用户_6', 'user9@qq.com', '11111111111', null, 'admin123456', null, '1', 'adasd', '0', '2019-12-21 11:22:33', 'admin123456', null, null, '0');
INSERT INTO `user` VALUES ('202004041609538014', 'username_5', 'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78', '用户_7', 'user8@qq.com', '11111111111', null, 'admin123456', null, '1', 'asdas', '0', '2019-12-21 11:22:33', 'admin123456', null, null, '0');
INSERT INTO `user` VALUES ('202004041609538015', 'username_6', 'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78', '用户_8', 'user7@qq.com', '11111111111', null, 'admin123456', null, '1', 'dadasd', '0', '2019-12-21 11:22:33', 'admin123456', null, null, '0');
INSERT INTO `user` VALUES ('202004041609538016', 'username_7', 'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78', '用户_9', 'user6@qq.com', '11111111111', null, 'admin123456', null, '1', 'asdad', '0', '2019-12-21 11:22:33', 'admin123456', null, null, '0');
INSERT INTO `user` VALUES ('202004041609538017', 'username_10', 'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78', '用户_10', 'user5@qq.com', '11111111111', null, 'admin123456', null, '1', 'addasda', '0', '2019-12-11 11:22:33', 'admin123456', null, null, '0');
INSERT INTO `user` VALUES ('202004041609538018', 'username_11', 'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78', '用户_11', 'user4@qq.com', '11111111111', null, 'admin123456', null, '1', '123sa', '0', '2019-12-23 11:22:33', 'admin123456', null, null, '0');
INSERT INTO `user` VALUES ('202004041609538019', 'username_12', 'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78', '用户_12', 'user3@qq.com', '11111111111', null, 'admin123456', null, '1', 'dasd', '0', '2019-12-21 11:22:33', 'admin123456', null, null, '0');
INSERT INTO `user` VALUES ('202004041609538020', 'username_13', 'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78', '用户_13', 'user2@qq.com', '11111111111', null, 'admin123456', null, '1', 'adasd', '0', '2019-12-21 11:22:33', 'admin123456', null, null, '0');

-- ----------------------------
-- Table structure for `user_data`
-- ----------------------------
DROP TABLE IF EXISTS `user_data`;
CREATE TABLE `user_data` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `data_permission_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_data
-- ----------------------------
INSERT INTO `user_data` VALUES ('202004281340365591', '202004041609538011', '202004281340365581');

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
INSERT INTO `user_module` VALUES ('202004261340308661', '202004041609538011', '202003282108350791');
INSERT INTO `user_module` VALUES ('202004261340308681', '202004041609538011', '202003282116237201');
INSERT INTO `user_module` VALUES ('202004261340308691', '202004041609538011', '202003282115442541');
INSERT INTO `user_module` VALUES ('202004261340308701', '202004041609538011', '202003282108350931');
INSERT INTO `user_module` VALUES ('202004261340308721', '202004041609538011', '202003282115442421');
INSERT INTO `user_module` VALUES ('202004261340308731', '202004041609538011', '202003282108350891');
INSERT INTO `user_module` VALUES ('202004261340308751', '202004041609538011', '202003282116593201');
INSERT INTO `user_module` VALUES ('202004261340308761', '202004041609538011', '202003282108350611');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  `module_strategy` int(11) DEFAULT NULL,
  `data_strategy` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('202004230919318541', '202004041609538011', '202004052117534261', '1', '1');
