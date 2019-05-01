package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.base.BaseDaoForMySql;
import com.javaweb.web.po.RoleModule;

@Mapper
public interface RoleModuleDao extends BaseDaoForMySql<RoleModule> {
	
	public List<String> getModuleIdsByRoleId(String roleId);
	
}