package com.javaweb.service.rbac;

import java.util.List;
import java.util.Map;

import com.javaweb.dataobject.eo.UserRoleModule;
import com.javaweb.dataobject.po.User;

public interface UserService {
	
	public void createUser(User user);

	public User getUserByUserId(String userId);
	
	public User getUserByUsernameAndPassword(Map<String,String> map);
	
	public List<UserRoleModule> getUserRoleModule(String userId);

}
