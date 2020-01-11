package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.dao.DaoForMySql;
import com.javaweb.web.po.RoleModule;

@Mapper
public interface RoleModuleDao extends DaoForMySql<RoleModule> {
	
	public List<String> getModuleIdsByRoleId(String roleId);
	
}