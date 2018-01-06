package com.javaweb.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.web.dao.ds1.ModuleDao;
import com.javaweb.web.po.Module;
import com.javaweb.web.service.ModuleService;

@Service("moduleServiceImpl")
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	private ModuleDao moduleDao;

	public List<Module> getUserRoleModule(Map<String, Object> map) {
		return moduleDao.getUserRoleModule(map);
	}

}
