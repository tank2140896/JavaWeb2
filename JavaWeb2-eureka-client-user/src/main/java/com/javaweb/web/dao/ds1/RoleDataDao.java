package com.javaweb.web.dao.ds1;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.mybatis.api.DaoForMySql;
import com.javaweb.web.po.RoleData;

@Mapper
public interface RoleDataDao extends DaoForMySql<RoleData> {
	
}
