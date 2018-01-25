package com.javaweb.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.util.core.PageUtil;
import com.javaweb.web.dao.ds1.UserDao;
import com.javaweb.web.eo.PageData;
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

	public PageData userList(UserListRequest userListRequest){
		PageData pageData = new PageData();
		List<UserListResponse> list = userDao.userList(userListRequest);
		long count = userDao.userListCount(userListRequest);
		pageData.setData(list);
		pageData.setCurrentPage(userListRequest.getCurrentPage());
		pageData.setPageSize(userListRequest.getPageSize());
		pageData.setTotalSize(count);
		pageData.setTotalPage(PageUtil.getTotalPage(pageData.getTotalSize(),pageData.getPageSize()));
		pageData.setPageList(PageUtil.getShowPages(userListRequest.getCurrentPage(),pageData.getTotalPage(),5L));
		return pageData;
	}

	@Transactional
	public void userDelete(String userId) {
		userDao.userDelete(userId);
	}
	
}
