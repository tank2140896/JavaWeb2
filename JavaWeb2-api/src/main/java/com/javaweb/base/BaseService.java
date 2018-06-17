package com.javaweb.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.javaweb.web.dao.ds1.CommonDao;
import com.javaweb.web.dao.ds1.ModuleDao;
import com.javaweb.web.dao.ds1.RoleDao;
import com.javaweb.web.dao.ds1.UserDao;

public class BaseService extends BaseTool {
	
	@Autowired
	protected CommonDao commonDao;
	
	@Autowired
	protected UserDao userDao;
	
	@Autowired
	protected RoleDao roleDao;
	
	@Autowired
	protected ModuleDao moduleDao;

}
