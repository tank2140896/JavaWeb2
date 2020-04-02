package com.javaweb.web.service;

import java.util.List;

import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.module.ModuleIdAndNameResponse;
import com.javaweb.web.eo.module.ModuleListRequest;
import com.javaweb.web.po.Module;
import com.javaweb.web.po.Role;
import com.javaweb.web.po.User;

public interface ModuleService {
    
    public List<ModuleIdAndNameResponse> getModuleIdAndNameList(String moduleType);
	
	public List<Module> getModule(boolean adminFlag,String userId);
	
	public Page moduleList(ModuleListRequest moduleListRequest);
	
	public void moduleDelete(String moduleId);
	
	public void moduleAdd(Module module);
	
	public void moduleModify(Module module);
	
	public Module moduleDetail(String moduleId);
	
	public List<Role> getAllRoleByModuleId(String moduleId);
	
	public List<User> getAllUserByModuleId(String moduleId);
	
}
