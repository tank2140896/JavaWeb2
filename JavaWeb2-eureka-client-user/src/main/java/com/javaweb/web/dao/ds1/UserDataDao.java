package com.javaweb.web.dao.ds1;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.mybatis.api.DaoForMySql;
import com.javaweb.web.po.UserData;

@Mapper
public interface UserDataDao extends DaoForMySql<UserData> {
	
}
