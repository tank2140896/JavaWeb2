package com.javaweb.web.dao.ds1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.base.MySqlBaseDao;
import com.javaweb.web.eo.user.RoleInfoResponse;
import com.javaweb.web.eo.user.UserListRequest;
import com.javaweb.web.eo.user.UserListResponse;
import com.javaweb.web.eo.user.UserLoginRequest;
import com.javaweb.web.po.User;

@Mapper
public interface UserDao extends MySqlBaseDao<User> {
	
	public User userLogin(UserLoginRequest userLogin);
	
	public List<UserListResponse> userList(UserListRequest userListRequest);
	
	public Long userListCount(UserListRequest userListRequest);
	
	public void userDelete(String userId);
	
	public void userAdd(User user);
	
	public User userDetail(String userId);
	
	public List<RoleInfoResponse> userRoleInfo(String userId);
	
	public void roleAssignment(Map<String,Object> map);
	
}