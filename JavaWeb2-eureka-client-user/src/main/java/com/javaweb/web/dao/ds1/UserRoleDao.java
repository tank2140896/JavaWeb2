package com.javaweb.web.dao.ds1;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.db.mybatis.api.DaoWapper;
import com.javaweb.web.po.UserRole;

@Mapper
public interface UserRoleDao extends DaoWapper<UserRole> {
	
}