package com.javaweb.web.dao.ds2;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.base.MySqlBaseDao;
import com.javaweb.web.po.User;

@Mapper
public interface UserDao2 extends MySqlBaseDao<User> {
	
	
}