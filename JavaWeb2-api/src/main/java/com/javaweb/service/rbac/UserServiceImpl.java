package com.javaweb.service.rbac;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.dao.rbac.UserDao;
import com.javaweb.dataobject.eo.Page;
import com.javaweb.dataobject.eo.UserRoleModule;
import com.javaweb.dataobject.eo.UserSearchCondition;
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

	public List<UserRoleModule> getUserRoleModule(Map<String,Object> map) {
		return userDao.getUserRoleModule(map);
	}

	public Page listUser(UserSearchCondition userSearchCondition) {
		Map<String,Object> map = new HashMap<>();
		map.put("userName", userSearchCondition.getUserName());
		map.put("startDate", userSearchCondition.getStartDate());
		map.put("endDate", userSearchCondition.getEndDate());
		map.put("currentStart", (userSearchCondition.getCurrentPage()-1)*userSearchCondition.getPageSize());
		map.put("currentSize", userSearchCondition.getPageSize());
		List<List<?>> list = userDao.listUser(map);
		Page page = new Page();
		page.setCurrentPage(userSearchCondition.getCurrentPage());
		page.setPageSize(userSearchCondition.getPageSize());
		page.setData(list.get(0));
		page.setTotalSize((Long)list.get(1).get(0));
		page.setTotalPage(page.getTotalSize()%page.getPageSize()==0?page.getTotalSize()/page.getPageSize():page.getTotalSize()/page.getPageSize()+1);
		return page;
	}

}
