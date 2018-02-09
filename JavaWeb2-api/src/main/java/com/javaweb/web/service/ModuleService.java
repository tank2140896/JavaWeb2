package com.javaweb.web.service;

import java.util.List;
import java.util.Map;

import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.module.ModuleListRequest;
import com.javaweb.web.po.Module;

public interface ModuleService {
	
	public List<Module> getUserRoleModule(Map<String,Object> map);
	
	public Page moduleList(ModuleListRequest moduleListRequest);
	
	public void moduleDelete(String moduleId);
	
	public void moduleAdd(Module module);
	
}
