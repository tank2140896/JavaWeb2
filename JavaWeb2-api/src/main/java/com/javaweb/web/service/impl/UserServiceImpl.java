package com.javaweb.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.web.dao.ds1.UserDao;
import com.javaweb.web.eo.UserLogin;
import com.javaweb.web.po.Module;
import com.javaweb.web.po.User;
import com.javaweb.web.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public User userLogin(UserLogin userLogin) {
		return userDao.userLogin(userLogin);
	}

	public List<Module> getUserRoleModule(Map<String, Object> map) {
		return userDao.getUserRoleModule(map);
	}

}
