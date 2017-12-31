package com.javaweb.web.service;

import java.util.List;
import java.util.Map;

import com.javaweb.web.eo.UserLogin;
import com.javaweb.web.po.Module;
import com.javaweb.web.po.User;

public interface UserService {
	
	public User userLogin(UserLogin userLogin);
	
	public List<Module> getUserRoleModule(Map<String,Object> map);
	
}
