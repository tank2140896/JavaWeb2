package com.javaweb.service.rbac;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.dao.rbac.UserDao;
import com.javaweb.dataobject.eo.UserRoleModule;
import com.javaweb.dataobject.po.User;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void createUser(User user){
		userDao.createUser(user);
	}

	public User getUserByUserId(String userId){
		return userDao.getUserByUserId(userId); 
	}
	
	public User getUserByUsernameAndPassword(Map<String,String> map) {
		return userDao.getUserByUsernameAndPassword(map);
	}

	public List<UserRoleModule> getUserRoleModule(String userId) {
		return userDao.getUserRoleModule(userId);
	}

}
