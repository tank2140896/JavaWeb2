package com.javaweb.web.service;

import com.javaweb.web.eo.PageData;
import com.javaweb.web.eo.user.UserListRequest;
import com.javaweb.web.eo.user.UserLoginRequest;
import com.javaweb.web.po.User;

public interface UserService {
	
	public User userLogin(UserLoginRequest userLogin);
	
	public PageData userList(UserListRequest userListRequest);
	
	public void userDelete(String userId);

	public void userAdd(User user);
	
}
