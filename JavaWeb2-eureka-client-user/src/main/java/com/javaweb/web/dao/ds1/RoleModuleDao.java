package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.db.mybatis.api.DaoWapper;
import com.javaweb.web.po.RoleModule;

@Mapper
public interface RoleModuleDao extends DaoWapper<RoleModule> {
	
	public List<String> getModuleIdsByRoleId(String roleId);
	
}