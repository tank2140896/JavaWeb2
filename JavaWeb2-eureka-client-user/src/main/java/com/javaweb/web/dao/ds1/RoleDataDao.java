package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.mybatis.api.DaoForMySql;
import com.javaweb.web.po.RoleData;

@Mapper
public interface RoleDataDao extends DaoForMySql<RoleData> {
	
	public List<RoleData> selectAllByRoleIds(List<String> roleIdList);
	
}
