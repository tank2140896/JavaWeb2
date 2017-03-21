package com.javaweb.service.rbac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.dao.rbac.UserDao;
import com.javaweb.dataobject.po.User;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void createUser(User user){
		userDao.createUser(user);
	}

	public User getUser(String userId){
		return userDao.getUser(userId); 
	}
	
	@Transactional
	public void updateUser(User user){
		userDao.updateUser(user);
	}
	
	@Transactional
	public void deleteUser(String userId){
		userDao.deleteUser(userId);
	}

}
