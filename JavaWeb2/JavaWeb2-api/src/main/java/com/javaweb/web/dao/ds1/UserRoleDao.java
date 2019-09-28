package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.base.BaseDaoForMySql;
import com.javaweb.web.po.UserRole;

@Mapper
public interface UserRoleDao extends BaseDaoForMySql<UserRole> {
	
	public List<UserRole> getUserRoleByUserId(String userId);
	
}