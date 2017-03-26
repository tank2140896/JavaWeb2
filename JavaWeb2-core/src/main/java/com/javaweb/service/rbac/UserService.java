package com.javaweb.service.rbac;

import java.util.Map;

import com.javaweb.dataobject.po.User;

public interface UserService {
	
	public void createUser(User user);

	public User getUserByUserId(String userId);
	
	public User getUserByUsernameAndPassword(Map<String,String> map);

}
