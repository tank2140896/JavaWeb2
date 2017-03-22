package com.javaweb.dao.rbac;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.dataobject.po.User;

@Mapper
public interface UserDao {
	
	public void createUser(User user);

	public User getUserByUserId(String userId);
	
	public User getUserByUserName(String userName);
	
}