package com.javaweb.web.dao.ds1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.base.MySqlBaseDao;
import com.javaweb.web.eo.module.ModuleLevelAndOrdersResponse;
import com.javaweb.web.eo.module.ModuleListRequest;
import com.javaweb.web.eo.module.ModuleListResponse;
import com.javaweb.web.po.Module;

@Mapper
public interface ModuleDao extends MySqlBaseDao<Module> {
	
	public List<Module> getUserRoleModule(Map<String, Object> map);
	
	public List<ModuleListResponse> moduleList(ModuleListRequest moduleListRequest);
	
	public Long moduleListCount(ModuleListRequest moduleListRequest);
	
	public void moduleDelete(String moduleId);
	
	public ModuleLevelAndOrdersResponse getModuleLevelAndOrdersByParentId(String parentId);
	
	public Module moduleDetail(String moduleId);
	
}