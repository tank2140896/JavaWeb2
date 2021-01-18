package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.db.mybatis.api.DaoWapper;
import com.javaweb.web.po.RoleData;

@Mapper
public interface RoleDataDao extends DaoWapper<RoleData> {
	
	public List<RoleData> selectAllByRoleIds(List<String> roleIdList);
	
}
