package com.javaweb.web.dao.ds1;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.base.BaseDao;
import com.javaweb.web.eo.user.UserLogin;
import com.javaweb.web.po.User;

@Mapper
public interface UserDao extends BaseDao<User> {
	
	public User userLogin(UserLogin userLogin);
	
}