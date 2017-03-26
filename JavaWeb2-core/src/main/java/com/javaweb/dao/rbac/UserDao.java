package com.javaweb.dao.rbac;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.dataobject.po.User;

@Mapper
public interface UserDao {
	
	public void createUser(User user);

	public User getUserByUserId(String userId);
	
	public User getUserByUsernameAndPassword(Map<String,String> map);
	
}