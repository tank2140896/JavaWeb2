package com.javaweb.web.service;

import java.util.List;
import java.util.Map;

import com.javaweb.web.po.Module;

public interface ModuleService {
	
	public List<Module> getUserRoleModule(Map<String,Object> map);
	
}
