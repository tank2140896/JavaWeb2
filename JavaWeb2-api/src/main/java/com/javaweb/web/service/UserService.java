package com.javaweb.web.service;

import com.javaweb.web.eo.user.UserLogin;
import com.javaweb.web.po.User;

public interface UserService {
	
	public User userLogin(UserLogin userLogin);
	
}
