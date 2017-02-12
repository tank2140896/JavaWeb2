package com.javaweb.dao.datasource1.rbac;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.dataobject.po.User;

@Mapper
public interface UserDao {
	
	//创建单个用户信息
	public void createUser(User user);

	//根据用户ID获取单个用户信息
	public User getUser(String userId);
	
	//更新单个用户信息
	public void updateUser(User user);
	
	//根据用户ID删除单个用户信息
	public void deleteUser(String userId);
	
}