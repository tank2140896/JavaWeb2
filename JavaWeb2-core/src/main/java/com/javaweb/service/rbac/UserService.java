package com.javaweb.service.rbac;

import com.javaweb.dataobject.po.User;

public interface UserService {
	
	public void createUser(User user);

	public User getUserByUserId(String userId);
	
	public User getUserByUserName(String userName);

}
