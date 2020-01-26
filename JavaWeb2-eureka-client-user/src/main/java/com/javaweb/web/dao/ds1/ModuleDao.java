package com.javaweb.web.dao.ds1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.mybatis.api.DaoForMySql;
import com.javaweb.web.eo.module.ModuleIdAndNameResponse;
import com.javaweb.web.eo.module.ModuleLevelAndOrdersResponse;
import com.javaweb.web.eo.module.ModuleListRequest;
import com.javaweb.web.eo.module.ModuleListResponse;
import com.javaweb.web.po.Module;

@Mapper
public interface ModuleDao extends DaoForMySql<Module> {
	
	public List<Module> getModuleByModuleId(List<String> list);
	
	public List<ModuleListResponse> moduleList(ModuleListRequest moduleListRequest);
	
	public Long moduleListCount(ModuleListRequest moduleListRequest);
	
	public void moduleDelete(String moduleId);
	
	public Module moduleDetail(String moduleId);
	
	public ModuleLevelAndOrdersResponse getModuleLevelAndOrdersByParentId(String parentId);
	
	public ModuleLevelAndOrdersResponse getModuleLevelAndOrdersWithoutParentId();
	
	public List<ModuleIdAndNameResponse> getModuleIdAndNameList(Map<String,String> map);
	
}