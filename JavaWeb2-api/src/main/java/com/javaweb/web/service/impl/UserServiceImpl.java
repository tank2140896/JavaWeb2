package com.javaweb.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.util.core.PageUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.dao.ds1.UserDao;
import com.javaweb.web.eo.user.RoleInfoResponse;
import com.javaweb.web.eo.user.UserListRequest;
import com.javaweb.web.eo.user.UserListResponse;
import com.javaweb.web.eo.user.UserLoginRequest;
import com.javaweb.web.po.User;
import com.javaweb.web.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public User userLogin(UserLoginRequest userLogin) {
		return userDao.userLogin(userLogin);
	}

	public Page userList(UserListRequest userListRequest){
		Page page = new Page();
		List<UserListResponse> list = userDao.userList(userListRequest);
		long count = userDao.userListCount(userListRequest);
		page.setData(list);
		page.setCurrentPage(userListRequest.getCurrentPage());
		page.setPageSize(userListRequest.getPageSize());
		page.setTotalSize(count);
		page.setTotalPage(PageUtil.getTotalPage(page.getTotalSize(),page.getPageSize()));
		page.setPageList(PageUtil.getShowPages(userListRequest.getCurrentPage(),page.getTotalPage(),5L));
		return page;
	}

	@Transactional
	public void userDelete(String userId) {
		userDao.userDelete(userId);
	}
	
	@Transactional
	public void userAdd(User user) {
		userDao.insert(user);
	}

	@Transactional
	public void userModify(User user) {
		userDao.update(user);
	}

	public User userDetail(String userId) {
		return userDao.userDetail(userId);
	}

	public List<RoleInfoResponse> userRoleInfo(String userId) {
		return userDao.userRoleInfo(userId);
	}

	@Transactional
	public void roleAssignment(Map<String, Object> map) {
		userDao.roleAssignment(map);
	}
	
}
