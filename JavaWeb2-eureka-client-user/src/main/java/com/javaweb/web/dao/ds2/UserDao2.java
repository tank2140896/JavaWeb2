package com.javaweb.web.dao.ds2;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.dao.DaoForMySql;
import com.javaweb.web.po.User;

@Mapper
public interface UserDao2 extends DaoForMySql<User> {
	
	
}