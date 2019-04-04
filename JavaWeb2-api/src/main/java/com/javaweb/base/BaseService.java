package com.javaweb.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.javaweb.web.dao.ds1.CommonDao;
import com.javaweb.web.dao.ds1.ModuleDao;
import com.javaweb.web.dao.ds1.RoleDao;
import com.javaweb.web.dao.ds1.RoleModuleDao;
import com.javaweb.web.dao.ds1.UserDao;
import com.javaweb.web.dao.ds1.UserModuleDao;
import com.javaweb.web.dao.ds1.UserRoleDao;

public class BaseService extends BaseTool {
	
	@Autowired
	protected CommonDao commonDao;
	
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

}
