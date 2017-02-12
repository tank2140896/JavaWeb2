package com.javaweb.service.rbac;

import com.javaweb.dataobject.po.User;

public interface UserService {
	
	public void createUser(User user);

	public User getUser(String userId);
	
	public void updateUser(User user);
	
	public void deleteUser(String userId);

}
