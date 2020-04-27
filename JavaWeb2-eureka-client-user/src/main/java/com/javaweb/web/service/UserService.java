package com.javaweb.web.service;

import java.util.List;

import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.role.ModuleInfoResponse;
import com.javaweb.web.eo.role.RoleIdAndStrategyRequest;
import com.javaweb.web.eo.user.RoleInfoResponse;
import com.javaweb.web.eo.user.UserListRequest;
import com.javaweb.web.eo.user.UserLoginRequest;
import com.javaweb.web.po.User;

public interface UserService {
	
	public User userLogin(UserLoginRequest userLogin);
	
	public Page userList(UserListRequest userListRequest);
	
	public void userDelete(String userId);

	public void userAdd(User user);

	public void userModify(User user);
	
	public User userDetail(String userId);
	
	public List<User> getUsersByUserId(List<String> list);
	
	public List<RoleInfoResponse> userRoleInfo(String userId);
	
	public void userRoleAssignment(String userId,List<RoleIdAndStrategyRequest> list);
	
	public List<ModuleInfoResponse> userModuleInfo(String userId);
	
	public void userModuleAssignment(String userId,List<String> list);
	
	public List<User> getAllUsers();
	
}
