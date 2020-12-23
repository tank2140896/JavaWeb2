package com.javaweb.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.javaweb.web.dao.ds1.CommonDao;
import com.javaweb.web.dao.ds1.DataPermissionDao;
import com.javaweb.web.dao.ds1.DbTablesDao;
import com.javaweb.web.dao.ds1.DictionaryDao;
import com.javaweb.web.dao.ds1.FileDao;
import com.javaweb.web.dao.ds1.InterfacesDao;
import com.javaweb.web.dao.ds1.ModuleDao;
import com.javaweb.web.dao.ds1.OperationLogDao;
import com.javaweb.web.dao.ds1.RoleDao;
import com.javaweb.web.dao.ds1.RoleDataDao;
import com.javaweb.web.dao.ds1.RoleModuleDao;
import com.javaweb.web.dao.ds1.ScheduleDao;
import com.javaweb.web.dao.ds1.UserDao;
import com.javaweb.web.dao.ds1.UserDataDao;
import com.javaweb.web.dao.ds1.UserModuleDao;
import com.javaweb.web.dao.ds1.UserRoleDao;

public class BaseService extends BaseTool {
    
	@Autowired
	protected CommonDao commonDao;
	
    @Autowired
    protected DictionaryDao dictionaryDao;
	
	@Autowired
	protected UserDao userDao;
	
	@Autowired
	protected RoleDao roleDao;
	
	@Autowired
	protected ModuleDao moduleDao;
	
	@Autowired
	protected RoleModuleDao roleModuleDao;
	
	@Autowired
	protected UserModuleDao userModuleDao;
	
	@Autowired
	protected UserRoleDao userRoleDao;
	
	@Autowired
	protected OperationLogDao operationLogDao;
	
	@Autowired
	protected ScheduleDao scheduleDao;
	
	@Autowired
	protected InterfacesDao interfacesDao;
	
	@Autowired
	protected DataPermissionDao dataPermissionDao;
	
	@Autowired
	protected UserDataDao userDataDao;
	
	@Autowired
	protected RoleDataDao roleDataDao;
	
	@Autowired
	protected DbTablesDao dbTablesDao;
	
	@Autowired
	protected FileDao fileDao;

}
