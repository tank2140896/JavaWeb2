SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_data_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_permission`;
CREATE TABLE `sys_data_permission` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `exclude_field` varchar(1000) NOT NULL COMMENT '排除字段',
  `interfaces_id` varchar(255) NOT NULL COMMENT '接口ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注说明',
  `create_date` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_date` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新者',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标记(0:未被删除;1:已被删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统数据权限表';

-- ----------------------------
-- Records of sys_data_permission
-- ----------------------------
INSERT INTO `sys_data_permission` VALUES ('202005211447517191', 'status', '202005161425051821', null, '2020-05-21 14:47:51', 'admin123456', null, null, '0');

-- ----------------------------
-- Table structure for `sys_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `parent_id` varchar(255) DEFAULT NULL COMMENT '父ID',
  `fcode` varchar(255) DEFAULT NULL COMMENT '层级关系',
  `level` int(11) DEFAULT NULL COMMENT '第几级(0表示未定义层级数;层级数1为最高,即根节点)',
  `data_type` varchar(255) DEFAULT NULL COMMENT '数据类型',
  `key_code` varchar(255) DEFAULT NULL COMMENT 'key值',
  `value_code` varchar(255) DEFAULT NULL COMMENT 'value值',
  `category` varchar(255) DEFAULT NULL COMMENT '分类',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `means` varchar(255) DEFAULT NULL COMMENT '含义',
  `universally` tinyint(4) DEFAULT NULL COMMENT '是否通用(0:通用;1:不通用)',
  `system_id` varchar(255) DEFAULT NULL COMMENT '系统ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注说明',
  `create_date` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_date` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新者',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标记(0:未被删除;1:已被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统字典表';

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES ('202003282118348411', null, null, '0', 'del_flag', 'undelete', '0', '字典类', '1', '未删除', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202003282118348471', null, null, '0', 'user_role_strategy', 'intersection', '2', '字典类', '3', '交集', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202003282118348501', null, null, '0', 'user_role_strategy', 'union', '1', '字典类', '2', '并集', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202003282118348541', null, null, '0', 'client_type', 'web', '1', '字典类', '2', '网页端', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202003282118348571', null, null, '0', 'client_type', 'android', '2', '字典类', '3', '安卓端', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202003282119121411', null, null, '0', 'module_type', 'catalogue', '1', '字典类', '1', '目录', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202003282119121471', null, null, '0', 'module_type', 'function', '3', '字典类', '3', '功能', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202003282119121511', null, null, '0', 'client_type', 'ios', '3', '字典类', '4', 'IOS端', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202003282119121561', null, null, '0', 'user_role_strategy', 'basedOnRole', '4', '字典类', '5', '以角色权限为准', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202003282119469071', null, null, '0', 'module_type', 'menu', '2', '字典类', '2', '菜单', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202003282119469151', null, null, '0', 'del_flag', 'delete', '1', '字典类', '2', '删除', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202003282119469221', null, null, '0', 'user_role_strategy', 'basedOnUser', '3', '字典类', '4', '以用户权限为准', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202003282119469301', null, null, '0', 'user_role_strategy', 'customize', '0', '字典类', '1', '自定义', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202005142045309911', null, null, '0', 'operatelog', 'operatelog.aspect.open', 'true', '配置类', '0', '是否开启接口请求操作日志记录', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202005142045309912', null, null, '0', 'user_state', 'normal', '0', '字典类', '1', '正常', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202005142045309913', null, null, '0', 'user_state', 'forbid', '1', '字典类', '2', '禁用', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202005142045309914', null, null, '0', 'password', 'init.user.password', 'abcd1234', '配置类', '1', '初始化密码', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202005142045309917', null, null, '0', 'file_root_path', 'file.root.linux.path', '/tmp/file/', '配置类', '1', '文件根路径（linux）', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202005142045309918', null, null, '0', 'file_root_path', 'file.root.windows.path', 'E:\\\\file\\\\', '配置类', '2', '文件根路径（windows）', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202102191610430681', null, null, '0', 'time_unit', 'year', '1', '字典类', '1', '年', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202102191610430682', null, null, '0', 'time_unit', 'month', '2', '字典类', '2', '月', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202102191610430683', null, null, '0', 'time_unit', 'day', '3', '字典类', '3', '日', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202102191610430684', null, null, '0', 'time_unit', 'hour', '4', '字典类', '4', '时', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202102191610430685', null, null, '0', 'time_unit', 'minute', '5', '字典类', '5', '分', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');
INSERT INTO `sys_dictionary` VALUES ('202102191610430686', null, null, '0', 'time_unit', 'second', '6', '字典类', '6', '秒', '0', null, null, '2021-02-19 16:15:44', 'admin123456', null, null, '0');

-- ----------------------------
-- Table structure for `sys_file`
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `system_id` varchar(255) DEFAULT NULL COMMENT '系统ID',
  `file_unique_index` varchar(255) DEFAULT NULL COMMENT '文件唯一索引',
  `origin_file_name` varchar(255) DEFAULT NULL COMMENT '原始文件名称',
  `current_file_name` varchar(255) DEFAULT NULL COMMENT '现文件名称',
  `file_path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `file_full_path` varchar(255) DEFAULT NULL COMMENT '文件全路径',
  `file_suffix` varchar(255) DEFAULT NULL COMMENT '文件后缀',
  `file_size` bigint(19) DEFAULT NULL COMMENT '文件大小',
  `file_unit` varchar(255) DEFAULT NULL COMMENT '文件大小单位',
  `file_ser_no` varchar(255) DEFAULT NULL COMMENT '文件批次号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态(0:正常；1：禁用)',
  `file_use_type` int(11) DEFAULT NULL COMMENT '文件使用类型（1：临时文件；2：正式文件）',
  `create_date` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_date` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新者',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标记(0:未被删除;1:已被删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统文件表';

-- ----------------------------
-- Records of sys_file
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_interfaces`
-- ----------------------------
DROP TABLE IF EXISTS `sys_interfaces`;
CREATE TABLE `sys_interfaces` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '接口名称',
  `url` varchar(255) DEFAULT NULL COMMENT 'URL',
  `base_url` varchar(255) DEFAULT NULL COMMENT '基础URL',
  `method` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `times` varchar(255) DEFAULT NULL COMMENT '时间',
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `counts` varchar(255) DEFAULT NULL COMMENT '次数',
  `data_permission` tinyint(4) DEFAULT NULL COMMENT '数据权限（0:无数据权限；1：有数据权限）',
  `entity` varchar(255) DEFAULT NULL COMMENT '返回的实体类（主要数据对象）',
  `history_times` bigint(20) unsigned DEFAULT '0' COMMENT '历史接口被调用次数',
  `request_data_secret` tinyint(4) DEFAULT NULL COMMENT '求数据加密（0：不加密；1：加密）',
  `response_data_secret` tinyint(4) DEFAULT NULL COMMENT '返回数据加密（0：不加密；1：加密）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_date` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新者',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标记(0:未被删除;1:已被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统接口表';

-- ----------------------------
-- Records of sys_interfaces
-- ----------------------------
INSERT INTO `sys_interfaces` VALUES ('202005161425050731', '用户登录接口', '/webLogin', '/webLogin', 'POST', null, null, null, '0', null, '120', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425050781', '获取服务器时间接口', '/getServeTime', '/getServeTime', 'GET', null, null, null, '0', null, '160', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425050801', '请求失效接口', '/invalidRequest', '/invalidRequest', 'GET, POST, PUT, DELETE', null, null, null, '0', null, '747', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425050821', '请求接口不存在接口', '/notFound', '/notFound', 'GET, POST, PUT, DELETE', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425050841', '没有权限接口', '/noAuthory', '/noAuthory', 'GET, POST, PUT, DELETE', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425050861', '系统异常接口', '/internalError', '/internalError', 'GET, POST, PUT, DELETE', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425050881', '请求参数缺失接口', '/requestParameterLost', '/requestParameterLost', 'GET, POST, PUT, DELETE', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425050901', '请求参数错误接口', '/requestParameterError', '/requestParameterError', 'GET, POST, PUT, DELETE', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425050921', '数据库表详情接口', '/web/sys/dbTables/detail/{tableName}', '/web/sys/dbTables/detail', 'GET', null, null, null, '0', null, '2', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425050941', '数据库表列表接口', '/web/sys/dbTables/list', '/web/sys/dbTables/list', 'POST', null, null, null, '0', null, '34', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425050961', '数据库表代码生成接口', '/web/sys/dbTables/codeGenerate/{tableName}', '/web/sys/dbTables/codeGenerate', 'GET', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425050981', '查询字典接口', '/web/sys/dictionary/list', '/web/sys/dictionary/list', 'POST', null, null, null, '0', null, '65', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051001', '修改字典接口', '/web/sys/dictionary/modify', '/web/sys/dictionary/modify', 'PUT', null, null, null, '0', null, '4', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051021', '删除字典接口', '/web/sys/dictionary/delete/{dictionaryId}', '/web/sys/dictionary/delete', 'DELETE', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051041', '新增字典接口', '/web/sys/dictionary/add', '/web/sys/dictionary/add', 'POST', null, null, null, '0', null, '4', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051061', '字典详情接口', '/web/sys/dictionary/detail/{dictionaryId}', '/web/sys/dictionary/detail', 'GET', null, null, null, '0', null, '4', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051081', '接口列表接口', '/web/sys/interfaces/list', '/web/sys/interfaces/list', 'POST', null, null, null, '0', null, '279', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051131', '接口详情接口', '/web/sys/interfaces/detail/{interfacesId}', '/web/sys/interfaces/detail', 'GET', null, null, null, '0', null, '19', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051181', '修改接口接口', '/web/sys/interfaces/modify', '/web/sys/interfaces/modify', 'PUT', null, null, null, '0', null, '3', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051201', '用户角色数据权限接口', '/web/sys/interfaces/userRoleDataPermission', '/web/sys/interfaces/userRoleDataPermission', 'POST', null, null, null, '0', null, '30', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051221', '数据权限分配接口', '/web/sys/interfaces/dataPermissionAssignment/{interfacesId}', '/web/sys/interfaces/dataPermissionAssignment', 'POST', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051241', '用户登出接口', '/web/loginAccess/logout', '/web/loginAccess/logout', 'GET', null, null, null, '0', null, '166', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051261', '获得字典信息接口', '/web/loginAccess/getDictionary', '/web/loginAccess/getDictionary', 'POST', null, null, null, '0', null, '148', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051281', '获取redis中的token信息接口', '/web/loginAccess/getRedisTokenData', '/web/loginAccess/getRedisTokenData', 'GET', null, null, null, '0', null, '253', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051291', '查询模块接口', '/web/sys/module/list', '/web/sys/module/list', 'POST', null, null, null, '0', null, '117', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051311', '修改模块接口', '/web/sys/module/modify', '/web/sys/module/modify', 'PUT', null, null, null, '0', null, '3', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051331', '模块详情接口', '/web/sys/module/detail/{moduleId}', '/web/sys/module/detail', 'GET', null, null, null, '0', null, '87', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051351', '新增模块接口', '/web/sys/module/add', '/web/sys/module/add', 'POST', null, null, null, '0', null, '21', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051371', '删除模块接口', '/web/sys/module/delete/{moduleId}', '/web/sys/module/delete', 'DELETE', null, null, null, '0', null, '6', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051391', '获取模块ID和模块名称列表接口', '/web/sys/module/getModuleIdAndNameList/{moduleType}', '/web/sys/module/getModuleIdAndNameList', 'GET', null, null, null, '0', null, '18', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051411', '在线用户列表接口', '/web/sys/onlineUser/list', '/web/sys/onlineUser/list', 'POST', null, null, null, '0', null, '41', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051441', '用户下线接口', '/web/sys/onlineUser/offline/{key}', '/web/sys/onlineUser/offline', 'GET', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051461', '查询操作日志接口', '/web/sys/operationLog/list', '/web/sys/operationLog/list', 'POST', null, null, null, '0', null, '57', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051481', '删除角色接口', '/web/sys/role/delete/{roleId}', '/web/sys/role/delete', 'DELETE', null, null, null, '0', null, '2', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051501', '角色模块信息接口', '/web/sys/role/roleModuleInfo/{roleId},/web/sys/role/roleModuleInfo', '/web/sys/role/roleModuleInfo', 'GET', null, null, null, '0', null, '9', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051521', '修改角色接口', '/web/sys/role/modify', '/web/sys/role/modify', 'PUT', null, null, null, '0', null, '37', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051541', '新增角色接口', '/web/sys/role/add', '/web/sys/role/add', 'POST', null, null, null, '0', null, '15', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051561', '角色详情接口', '/web/sys/role/detail/{roleId}', '/web/sys/role/detail', 'GET', null, null, null, '0', null, '13', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051581', '查询角色接口', '/web/sys/role/list', '/web/sys/role/list', 'POST', null, null, null, '0', null, '150', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051601', '角色模块分配接口', '/web/sys/role/roleModuleAssignment/{roleId}', '/web/sys/role/roleModuleAssignment', 'POST', null, null, null, '0', null, '2', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051621', '日程列表接口', '/web/sys/schedule/list', '/web/sys/schedule/list', 'POST', null, null, null, '0', null, '21', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051651', '保存日程接口', '/web/sys/schedule/add', '/web/sys/schedule/add', 'POST', null, null, null, '0', null, '3', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051741', '修改用户接口', '/web/sys/user/modify', '/web/sys/user/modify', 'PUT', null, null, null, '0', null, '16', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051761', '删除用户接口', '/web/sys/user/delete/{userId}', '/web/sys/user/delete', 'DELETE', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051781', '用户角色信息接口', '/web/sys/user/userRoleInfo/{userId},/web/sys/user/userRoleInfo', '/web/sys/user/userRoleInfo', 'GET', null, null, null, '0', null, '4', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051801', '用户模块信息接口', '/web/sys/user/userModuleInfo/{userId},/web/sys/user/userModuleInfo', '/web/sys/user/userModuleInfo', 'GET', null, null, null, '0', null, '22', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051821', '查询用户接口', '/web/sys/user/list', '/web/sys/user/list', 'POST', '1', '秒', '3', '1', '[userId, userName, personName, status, createDate, roleName]', '200', '0', '0', '1秒3次', '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051841', '用户详情接口', '/web/sys/user/detail/{userId}', '/web/sys/user/detail', 'GET', null, null, null, '0', null, '15', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051861', '初始化密码接口', '/web/sys/user/initPassword/{userId}', '/web/sys/user/initPassword', 'GET', null, null, null, '0', null, '2', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051881', '新增用户接口', '/web/sys/user/add', '/web/sys/user/add', 'POST', null, null, null, '0', null, '7', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051901', '用户模块分配接口', '/web/sys/user/userModuleAssignment/{userId}', '/web/sys/user/userModuleAssignment', 'POST', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051921', '用户角色分配接口', '/web/sys/user/userRoleAssignment/{userId}', '/web/sys/user/userRoleAssignment', 'POST', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005161425051991', null, '/error', '/error', '', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202005201102090531', '用户头像上传接口', '/web/sys/user/portraitUpload/{userId}', '/web/sys/user/portraitUpload', 'POST', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202006042043581991', '获取用户头像接口', '/web/sys/user/userPortrait', '/web/sys/user/userPortrait', 'GET', null, null, null, '0', null, '24', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202006131245542761', '接口测试接口', '/web/sys/interfaces/test', '/web/sys/interfaces/test', 'POST', null, null, null, '0', null, '19', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202012231534471661', '文件列表接口', '/web/sys/file/list', '/web/sys/file/list', 'POST', null, null, null, '0', null, '109', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202012231534471691', '文件下载接口', '/web/sys/file/downloadFile/{fileId}', '/web/sys/file/downloadFile', 'GET', null, null, null, '0', null, '11', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202012231534471701', '文件上传接口', '/web/sys/file/uploadFile', '/web/sys/file/uploadFile', 'POST', null, null, null, '0', null, '12', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202012231958062701', '文件删除接口', '/web/sys/file/delete/{fileId}', '/web/sys/file/delete', 'GET', null, null, null, '0', null, '4', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202012231958062731', '文件详情接口', '/web/sys/file/detail/{fileId}', '/web/sys/file/detail', 'GET', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202012231958062811', null, '/deleteTest', '/deleteTest', 'DELETE', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202012231958062831', null, '/getQrCode', '/getQrCode', 'GET', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202012231958062851', null, '/postTest', '/postTest', 'POST', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202012231958062871', null, '/putTest', '/putTest', 'PUT', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202012231958062891', null, '/getTest', '/getTest', 'GET', null, null, null, '0', null, '27', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');
INSERT INTO `sys_interfaces` VALUES ('202101141522555221', '请求接口受限接口', '/requestLimit', '/requestLimit', 'GET, POST, PUT, DELETE', null, null, null, '0', null, '0', '0', '0', null, '2021-01-29 09:51:49', 'admin123456', '2021-03-03 14:11:53', 'admin123456', '0');

-- ----------------------------
-- Table structure for `sys_module`
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module` (
  `module_id` varchar(255) NOT NULL COMMENT '主键ID',
  `module_name` varchar(255) NOT NULL COMMENT '模块名称',
  `page_url` varchar(255) DEFAULT NULL COMMENT '页面URL',
  `api_url` varchar(10000) DEFAULT NULL COMMENT 'API的URL',
  `parent_id` varchar(255) DEFAULT NULL COMMENT '模块的上级ID',
  `fcode` varchar(255) DEFAULT NULL COMMENT '层级关系',
  `level` int(11) DEFAULT NULL COMMENT '第几级(0表示未定义层级数;层级数1为最高,即根节点)',
  `orders` int(11) DEFAULT NULL COMMENT '模块顺序(0表示没有顺序;顺序从1开始)',
  `module_type` int(11) DEFAULT NULL COMMENT '模块类型(0:未定义模块类型;1:目录;2:菜单；3:功能)',
  `alias` varchar(255) DEFAULT NULL COMMENT '别名',
  `parent_alias` varchar(255) DEFAULT NULL COMMENT '上级别名',
  `system_id` varchar(255) DEFAULT NULL COMMENT '系统ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `type` int(11) DEFAULT NULL COMMENT '类型(0:未定义类型,作为全端通用接口使用;1:PC端;2:安卓端;3:IOS端) ',
  `create_date` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_date` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新者',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标记(0:未被删除;1:已被删除)',
  PRIMARY KEY (`module_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统模块表';

-- ----------------------------
-- Records of sys_module
-- ----------------------------
INSERT INTO `sys_module` VALUES ('202003282108350611', '系统管理', null, null, null, null, '1', '1', '1', 'sys', null, '1', null, 'fa fa-window-maximize', '0', '2018-02-08 17:02:21', 'admin123456', '2020-04-09 14:16:11', 'admin123456', '0');
INSERT INTO `sys_module` VALUES ('202003282108350781', '用户管理', '/web/sys/user', null, '202003282108350611', null, '2', '1', '2', 'sys:user', null, '1', null, 'fa fa-user-circle', '0', '2018-02-08 17:02:21', 'admin123456', '2020-04-09 14:18:54', 'admin123456', '0');
INSERT INTO `sys_module` VALUES ('202003282108350791', '角色管理', '/web/sys/role', null, '202003282108350611', null, '2', '2', '2', 'sys:role', null, '1', null, 'fa fa-address-card', '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350801', '模块管理', '/web/sys/module', null, '202003282108350611', null, '2', '3', '2', 'sys:module', null, '1', null, 'fa fa-modx', '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350802', '字典管理', '/web/sys/dictionary', null, '202003282108350611', null, '2', '4', '2', 'sys:dictionary', null, '1', null, 'fa fa-book', '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350803', '操作日志管理', '/web/sys/operationLog', null, '202003282108350611', null, '2', '5', '2', 'sys:operationLog', null, '1', null, 'fa fa-wpforms', '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350804', '日程管理', '/web/sys/schedule', null, '202003282108350611', null, '2', '6', '2', 'sys:schedule', null, '1', null, 'fa fa-calendar', '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350805', '接口管理', '/web/sys/interfaces', null, '202003282108350611', null, '2', '7', '2', 'sys:interfaces', null, '1', null, 'fa fa-cogs', '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350806', '在线聊天管理', '/web/sys/onlineChat', null, '202003282108350611', null, '2', '8', '2', 'sys:onlineChat', null, '1', null, 'fa fa-snapchat-ghost', '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350807', '在线用户管理', '/web/sys/onlineUser', null, '202003282108350611', null, '2', '9', '2', 'sys:onlineUser', null, '1', null, 'fa fa-id-card-o', '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350808', '数据库表管理', '/web/sys/dbTables', null, '202003282108350611', null, '2', '10', '2', 'sys:dbTables', null, '1', null, 'fa fa-database', '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350809', '系统日志管理', '/web/sys/systemLog', null, '202003282108350611', null, '2', '11', '2', 'sys:systemLog', null, '1', null, 'fa fa-pencil', '0', '2020-12-03 09:58:00', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350810', '文件管理', '/web/sys/file', null, '202003282108350611', null, '2', '12', '2', 'sys:file', null, '1', null, 'fa fa-book', '0', '2020-12-03 09:58:00', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350831', '用户模块分配', '/web/sys/user/userModuleAssignment', '/web/sys/user/userModuleAssignment,/web/sys/user/userModuleInfo', '202003282108350781', null, '3', '7', '3', 'sys:user:module:assignment', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350861', '修改模块', '/web/sys/module/modify', '/web/sys/module/modify,/web/sys/module/getModuleIdAndNameList', '202003282108350801', null, '3', '5', '3', 'sys:module:modify', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350891', '删除角色', null, '/web/sys/role/delete', '202003282108350791', null, '3', '5', '3', 'sys:role:delete', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350931', '查询角色', '/web/sys/role/list', '/web/sys/role/list', '202003282108350791', null, '3', '3', '3', 'sys:role:list', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282108350991', '新增用户', '/web/sys/user/add', '/web/sys/user/add,/web/sys/user/portraitUpload', '202003282108350781', null, '3', '2', '3', 'sys:user:add', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282115442191', '查询模块', '/web/sys/module/list', '/web/sys/module/list', '202003282108350801', null, '3', '1', '3', 'sys:module:list', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282115442421', '角色详情', '/web/sys/role/detail', '/web/sys/role/detail', '202003282108350791', null, '3', '4', '3', 'sys:role:detail', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282115442491', '删除模块', null, '/web/sys/module/delete', '202003282108350801', null, '3', '4', '3', 'sys:module:delete', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282115442541', '修改角色', '/web/sys/role/modify', '/web/sys/role/modify,/web/sys/role/detail', '202003282108350791', null, '3', '2', '3', 'sys:role:modifty', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282115442571', '模块详情', '/web/sys/module/detail', '/web/sys/module/detail', '202003282108350801', null, '3', '2', '3', 'sys:module:detail', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282116237201', '新增角色', '/web/sys/role/add', '/web/sys/role/add', '202003282108350791', null, '3', '1', '3', 'sys:role:add', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282116237411', '用户角色分配', '/web/sys/user/userRoleAssignment', '/web/sys/user/userRoleAssignment,/web/sys/user/userRoleInfo', '202003282108350781', null, '3', '6', '3', 'sys:user:role:assignment', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282116237491', '修改用户', '/web/sys/user/modify', '/web/sys/user/modify,/web/sys/user/detail,/web/sys/user/userPortrait', '202003282108350781', null, '3', '3', '3', 'sys:user:modify', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282116593201', '角色模块分配', '/web/sys/role/roleModuleAssignment', '/web/sys/role/roleModuleAssignment,/web/sys/role/roleModuleInfo', '202003282108350791', null, '3', '6', '3', 'sys:role:module:assignment', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282116593381', '删除用户', null, '/web/sys/user/delete', '202003282108350781', null, '3', '4', '3', 'sys:user:delete', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282116593521', '新增模块', '/web/sys/module/add', '/web/sys/module/add,/web/sys/module/getModuleIdAndNameList', '202003282108350801', null, '3', '3', '3', 'sys:module:add', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355621', '查询用户', '/web/sys/user/list', '/web/sys/user/list', '202003282108350781', null, '3', '1', '3', 'sys:user:list', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355941', '用户详情', '/web/sys/user/detail', '/web/sys/user/detail', '202003282108350781', null, '3', '5', '3', 'sys:user:detail', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355942', '新增字典', '/web/sys/dictionary/add', '/web/sys/dictionary/add', '202003282108350802', null, '3', '1', '3', 'sys:dictionary:add', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355943', '修改字典', '/web/sys/dictionary/modify', '/web/sys/dictionary/modify,/web/sys/dictionary/detail', '202003282108350802', null, '3', '2', '3', 'sys:dictionary:modify', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355944', '查询字典', '/web/sys/dictionary/list', '/web/sys/dictionary/list', '202003282108350802', null, '3', '3', '3', 'sys:dictionary:list', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355945', '删除字典', null, '/web/sys/dictionary/delete', '202003282108350802', null, '3', '4', '3', 'sys:dictionary:delete', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355946', '字典详情', '/web/sys/dictionary/detail', '/web/sys/dictionary/detail', '202003282108350802', null, '3', '5', '3', 'sys:dictionary:detail', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355947', '查询操作日志', '/web/sys/operationLog/list', '/web/sys/operationLog/list', '202003282108350803', null, '3', '1', '3', 'sys:operationLog:list', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355948', '查询日程', '/web/sys/schedule/list', '/web/sys/schedule/list', '202003282108350804', null, '3', '1', '3', 'sys:schedule:list', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355949', '保存日程', null, '/web/sys/schedule/add', '202003282108350804', null, '3', '2', '3', 'sys:schedule:add', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355950', '查询接口', '/web/sys/interfaces/list', '/web/sys/interfaces/list', '202003282108350805', null, '3', '1', '3', 'sys:interfaces:list', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355951', '接口详情', '/web/sys/interfaces/detail', '/web/sys/interfaces/detail', '202003282108350805', null, '3', '2', '3', 'sys:interfaces:detail', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355952', '修改接口', '/web/sys/interfaces/modify', '/web/sys/interfaces/modify,/web/sys/interfaces/detail', '202003282108350805', null, '3', '3', '3', 'sys:interfaces:modify', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355953', '数据权限分配', '/web/sys/interfaces/dataPermissionAssignment', '/web/sys/interfaces/userRoleDataPermission,/web/sys/interfaces/dataPermissionAssignment', '202003282108350805', null, '3', '4', '3', 'sys:interfaces:dataPermissionAssignment', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202003282117355954', '在线用户列表', null, '/web/sys/onlineUser/list', '202003282108350807', null, '3', '1', '3', 'sys:onlineUser:list', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202004212010126742', '用户下线接口', null, '/web/sys/onlineUser/offline', '202003282108350807', null, '3', '2', '3', 'sys:onlineUser:offline', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202004212010126743', '数据库表列表接口', '/web/sys/dbTables/list', '/web/sys/dbTables/list', '202003282108350808', null, '3', '1', '3', 'sys:dbTables:list', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202004212010126744', '数据库表详情接口', '/web/sys/dbTables/detail', '/web/sys/dbTables/detail', '202003282108350808', null, '3', '2', '3', 'sys:dbTables:detail', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202004212010126745', '数据库表代码生成接口', null, '/web/sys/dbTables/codeGenerate', '202003282108350808', null, '3', '3', '3', 'sys:dbTables:codeGenerate', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202005161348165371', '初始化密码', null, '/web/sys/user/initPassword', '202003282108350781', null, '3', '8', '3', 'sys:user:initPassword', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202005161348165381', '接口测试', '/web/sys/interfaces/test', '/web/sys/interfaces/test', '202003282108350805', null, '3', '5', '3', 'sys:interfaces:test', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202005161348165382', '文件列表', '/web/sys/file/list', '/web/sys/file/list', '202003282108350810', null, '3', '1', '3', 'sys:file:list', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202005161348165383', '上传文件', '/web/sys/file/uploadFile', '/web/sys/file/uploadFile', '202003282108350810', null, '3', '2', '3', 'sys:file:uploadFile', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202005161348165384', '下载文件', '/web/sys/file/downloadFile', '/web/sys/file/downloadFile', '202003282108350810', null, '3', '3', '3', 'sys:file:downloadFile', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202005161348165385', '文件详情', '/web/sys/file/detail', '/web/sys/file/detail', '202003282108350810', null, '3', '4', '3', 'sys:file:detail', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');
INSERT INTO `sys_module` VALUES ('202005161348165386', '删除文件', '/web/sys/file/delete', '/web/sys/file/delete', '202003282108350810', null, '3', '5', '3', 'sys:file:delete', null, '1', null, null, '0', '2018-02-08 17:02:21', 'admin123456', null, null, '0');

-- ----------------------------
-- Table structure for `sys_operation_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `url` varchar(255) DEFAULT NULL COMMENT '请求地址',
  `request_method` varchar(255) DEFAULT NULL COMMENT '请求方法',
  `request_parameter` varchar(20000) DEFAULT NULL COMMENT '请求参数',
  `request_ip_address` varchar(255) DEFAULT NULL COMMENT '请求IP地址',
  `request_time` varchar(255) DEFAULT NULL COMMENT '请求时间',
  `login_way` tinyint(4) DEFAULT NULL COMMENT '登录方式（1：账号密码登录（默认）；2：二维码扫码登录；3：短信验证码登录）',
  `client_type` tinyint(4) DEFAULT NULL COMMENT '客户端类型（1：页面端（默认）；2：安卓端；3：IOS端）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统操作日志表';

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
INSERT INTO `sys_operation_log` VALUES ('202103031426322541', '202103031401530211', 'http://localhost:2001/web/sys/file/delete/202103031345331461', 'GET', null, '192.168.1.1', '2021-03-03 14:26:32', '1', '1', null);
INSERT INTO `sys_operation_log` VALUES ('202103031426341181', '202103031401530211', 'http://localhost:2001/web/sys/file/delete/202103031345331551', 'GET', null, '192.168.1.1', '2021-03-03 14:26:34', '1', '1', null);
INSERT INTO `sys_operation_log` VALUES ('202103031426361221', '202103031401530211', 'http://localhost:2001/web/sys/file/delete/202103031345338331', 'GET', null, '192.168.1.1', '2021-03-03 14:26:36', '1', '1', null);

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(255) NOT NULL COMMENT '角色ID',
  `role_name` varchar(255) NOT NULL COMMENT '角色名称',
  `role_code` varchar(255) DEFAULT NULL COMMENT '角色代码',
  `parent_id` varchar(255) DEFAULT NULL COMMENT '角色的上级ID',
  `fcode` varchar(255) DEFAULT NULL COMMENT '层级关系',
  `level` int(11) DEFAULT '0' COMMENT '第几级(0表示未定义层级数;层级数1为最高,即根节点)',
  `type` int(11) DEFAULT '0' COMMENT '类型(0:未定义类型,作为全端通用接口使用;1:PC端;2:安卓端;3:IOS端)',
  `system_id` varchar(255) DEFAULT NULL COMMENT '系统ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_date` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新者',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标记(0:未被删除;1:已被删除)',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('202004052117534261', '超级管理员', 'admin', null, null, '0', '0', null, '超级管理员拥有所有权限', '2019-12-21 11:22:33', 'admin123456', '2021-03-03 13:56:15', 'admin123456', '0');

-- ----------------------------
-- Table structure for `sys_role_data`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_data`;
CREATE TABLE `sys_role_data` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `role_id` varchar(255) NOT NULL COMMENT '角色ID',
  `data_permission_id` varchar(255) NOT NULL COMMENT '数据权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色数据表';

-- ----------------------------
-- Records of sys_role_data
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role_module`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_module`;
CREATE TABLE `sys_role_module` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `role_id` varchar(255) NOT NULL COMMENT '角色ID',
  `module_id` varchar(255) NOT NULL COMMENT '模块ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统角色模块表';

-- ----------------------------
-- Records of sys_role_module
-- ----------------------------
INSERT INTO `sys_role_module` VALUES ('202103031358328271', '202004052117534261', '202003282108350611');
INSERT INTO `sys_role_module` VALUES ('202103031358328281', '202004052117534261', '202003282108350781');
INSERT INTO `sys_role_module` VALUES ('202103031358328301', '202004052117534261', '202003282117355621');
INSERT INTO `sys_role_module` VALUES ('202103031358328311', '202004052117534261', '202003282108350991');
INSERT INTO `sys_role_module` VALUES ('202103031358328321', '202004052117534261', '202003282116237491');
INSERT INTO `sys_role_module` VALUES ('202103031358328331', '202004052117534261', '202003282116593381');
INSERT INTO `sys_role_module` VALUES ('202103031358328341', '202004052117534261', '202003282117355941');
INSERT INTO `sys_role_module` VALUES ('202103031358328351', '202004052117534261', '202003282116237411');
INSERT INTO `sys_role_module` VALUES ('202103031358328371', '202004052117534261', '202003282108350831');
INSERT INTO `sys_role_module` VALUES ('202103031358328381', '202004052117534261', '202005161348165371');
INSERT INTO `sys_role_module` VALUES ('202103031358328401', '202004052117534261', '202003282108350791');
INSERT INTO `sys_role_module` VALUES ('202103031358328421', '202004052117534261', '202003282116237201');
INSERT INTO `sys_role_module` VALUES ('202103031358328431', '202004052117534261', '202003282115442541');
INSERT INTO `sys_role_module` VALUES ('202103031358328441', '202004052117534261', '202003282108350931');
INSERT INTO `sys_role_module` VALUES ('202103031358328461', '202004052117534261', '202003282115442421');
INSERT INTO `sys_role_module` VALUES ('202103031358328471', '202004052117534261', '202003282108350891');
INSERT INTO `sys_role_module` VALUES ('202103031358328481', '202004052117534261', '202003282116593201');
INSERT INTO `sys_role_module` VALUES ('202103031358328501', '202004052117534261', '202003282108350801');
INSERT INTO `sys_role_module` VALUES ('202103031358328511', '202004052117534261', '202003282115442191');
INSERT INTO `sys_role_module` VALUES ('202103031358328521', '202004052117534261', '202003282115442571');
INSERT INTO `sys_role_module` VALUES ('202103031358328541', '202004052117534261', '202003282116593521');
INSERT INTO `sys_role_module` VALUES ('202103031358328551', '202004052117534261', '202003282115442491');
INSERT INTO `sys_role_module` VALUES ('202103031358328571', '202004052117534261', '202003282108350861');
INSERT INTO `sys_role_module` VALUES ('202103031358328591', '202004052117534261', '202003282108350802');
INSERT INTO `sys_role_module` VALUES ('202103031358328601', '202004052117534261', '202003282117355942');
INSERT INTO `sys_role_module` VALUES ('202103031358328621', '202004052117534261', '202003282117355943');
INSERT INTO `sys_role_module` VALUES ('202103031358328641', '202004052117534261', '202003282117355944');
INSERT INTO `sys_role_module` VALUES ('202103031358328651', '202004052117534261', '202003282117355945');
INSERT INTO `sys_role_module` VALUES ('202103031358328661', '202004052117534261', '202003282117355946');
INSERT INTO `sys_role_module` VALUES ('202103031358328681', '202004052117534261', '202003282108350803');
INSERT INTO `sys_role_module` VALUES ('202103031358328701', '202004052117534261', '202003282117355947');
INSERT INTO `sys_role_module` VALUES ('202103031358328711', '202004052117534261', '202003282108350804');
INSERT INTO `sys_role_module` VALUES ('202103031358328731', '202004052117534261', '202003282117355948');
INSERT INTO `sys_role_module` VALUES ('202103031358328751', '202004052117534261', '202003282117355949');
INSERT INTO `sys_role_module` VALUES ('202103031358328771', '202004052117534261', '202003282108350805');
INSERT INTO `sys_role_module` VALUES ('202103031358328791', '202004052117534261', '202003282117355950');
INSERT INTO `sys_role_module` VALUES ('202103031358328801', '202004052117534261', '202003282117355951');
INSERT INTO `sys_role_module` VALUES ('202103031358328811', '202004052117534261', '202003282117355952');
INSERT INTO `sys_role_module` VALUES ('202103031358328831', '202004052117534261', '202003282117355953');
INSERT INTO `sys_role_module` VALUES ('202103031358328841', '202004052117534261', '202005161348165381');
INSERT INTO `sys_role_module` VALUES ('202103031358328861', '202004052117534261', '202003282108350806');
INSERT INTO `sys_role_module` VALUES ('202103031358328881', '202004052117534261', '202003282108350807');
INSERT INTO `sys_role_module` VALUES ('202103031358328901', '202004052117534261', '202003282117355954');
INSERT INTO `sys_role_module` VALUES ('202103031358328911', '202004052117534261', '202004212010126742');
INSERT INTO `sys_role_module` VALUES ('202103031358328931', '202004052117534261', '202003282108350808');
INSERT INTO `sys_role_module` VALUES ('202103031358328941', '202004052117534261', '202004212010126743');
INSERT INTO `sys_role_module` VALUES ('202103031358328961', '202004052117534261', '202004212010126744');
INSERT INTO `sys_role_module` VALUES ('202103031358328971', '202004052117534261', '202004212010126745');
INSERT INTO `sys_role_module` VALUES ('202103031358328981', '202004052117534261', '202003282108350809');
INSERT INTO `sys_role_module` VALUES ('202103031358328991', '202004052117534261', '202003282108350810');
INSERT INTO `sys_role_module` VALUES ('202103031358329011', '202004052117534261', '202005161348165382');
INSERT INTO `sys_role_module` VALUES ('202103031358329031', '202004052117534261', '202005161348165383');
INSERT INTO `sys_role_module` VALUES ('202103031358329041', '202004052117534261', '202005161348165384');
INSERT INTO `sys_role_module` VALUES ('202103031358329061', '202004052117534261', '202005161348165385');
INSERT INTO `sys_role_module` VALUES ('202103031358329071', '202004052117534261', '202005161348165386');

-- ----------------------------
-- Table structure for `sys_schedule`
-- ----------------------------
DROP TABLE IF EXISTS `sys_schedule`;
CREATE TABLE `sys_schedule` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `schedule_date` varchar(255) DEFAULT NULL COMMENT '日期',
  `schedule_type` int(11) DEFAULT NULL COMMENT '日程类型（1:周末;2:正常;3:节假日;4:休假）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_date` varchar(255) DEFAULT NULL COMMENT '更新时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新者',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标记(0:未被删除;1:已被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日程表';

-- ----------------------------
-- Records of sys_schedule
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(255) NOT NULL COMMENT '用户ID',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `person_name` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `id_card` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `portrait` varchar(255) DEFAULT NULL COMMENT '头像',
  `parent_id` varchar(255) DEFAULT NULL COMMENT '创建该用户的ID',
  `fcode` varchar(255) DEFAULT NULL COMMENT '层级关系',
  `level` int(11) DEFAULT '0' COMMENT '第几级（层级数0为最高，即根节点）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '账号状态（0：正常；1：禁用）',
  `real_name_status` int(11) DEFAULT '0' COMMENT '实名认证状态（0：未实名认证；1：已实名认证）',
  `create_date` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_date` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新者',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标记(0:未被删除;1:已被删除)',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('202103031401530211', 'abcd1234', 'e9cee71ab932fde863338d08be4de9dfe39ea049bdafb342ce659ec5450b69ae', '测试用户', null, null, null, null, 'admin123456', null, '1', null, '0', '0', '2021-03-03 14:01:53', 'admin123456', '2021-03-03 14:01:57', 'admin123456', '0');

-- ----------------------------
-- Table structure for `sys_user_data`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_data`;
CREATE TABLE `sys_user_data` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `user_id` varchar(255) NOT NULL COMMENT '用户ID',
  `data_permission_id` varchar(255) NOT NULL COMMENT '数据权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户数据表';

-- ----------------------------
-- Records of sys_user_data
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user_module`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_module`;
CREATE TABLE `sys_user_module` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `user_id` varchar(255) NOT NULL COMMENT '用户ID',
  `module_id` varchar(255) NOT NULL COMMENT '模块ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户模块表';

-- ----------------------------
-- Records of sys_user_module
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `user_id` varchar(255) NOT NULL COMMENT '用户ID',
  `role_id` varchar(255) NOT NULL COMMENT '角色ID',
  `module_strategy` int(11) DEFAULT NULL COMMENT '权限获取策略(0:自定义;1:并集;2:交集;3:以用户权限为准;4:以角色权限为准;其它:默认为未定义,作为并集处理)',
  `data_strategy` int(11) DEFAULT NULL COMMENT '数据获取策略(0:自定义;1:并集;2:交集;3:以用户权限为准;4:以角色权限为准;其它:默认为未定义,作为并集处理)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('202103031402310281', '202103031401530211', '202004052117534261', '1', '1');
