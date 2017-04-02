package com.javaweb.dao.rbac;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.dataobject.eo.UserRoleModule;
import com.javaweb.dataobject.eo.UserSearchCondition;
import com.javaweb.dataobject.po.User;

@Mapper
public interface UserDao {
	
	public void createUser(User user);

	public User getUserByUserId(String userId);
	
	public User getUserByUsernameAndPassword(Map<String,String> map);
	
	public List<UserRoleModule> getUserRoleModule(Map<String,Object> map);
	
	public List<List<?>> listUser(UserSearchCondition UserSearchCondition);
	
}