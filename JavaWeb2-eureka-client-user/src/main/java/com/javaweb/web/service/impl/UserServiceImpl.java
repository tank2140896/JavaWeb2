package com.javaweb.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.base.BaseService;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.role.ModuleInfoResponse;
import com.javaweb.web.eo.role.RoleIdAndStrategyRequest;
import com.javaweb.web.eo.user.RoleInfoResponse;
import com.javaweb.web.eo.user.UserListRequest;
import com.javaweb.web.eo.user.UserListResponse;
import com.javaweb.web.eo.user.UserLoginRequest;
import com.javaweb.web.po.User;
import com.javaweb.web.po.UserModule;
import com.javaweb.web.po.UserRole;
import com.javaweb.web.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl extends BaseService implements UserService {
	
	public User userLogin(UserLoginRequest userLogin) {
		return userDao.userLogin(userLogin);
	}

	public Page userList(UserListRequest userListRequest){
		List<UserListResponse> list = userDao.userList(userListRequest);
		long count = userDao.userListCount(userListRequest);
		Page page = new Page(userListRequest,list,count);
		return page;
	}

	@Transactional
	public void userDelete(String userId) {
		String userIds[] = userId.split(",");
		for(String id:userIds){
			userDao.userDelete(id);
		}
	}
	
	@Transactional
	public void userAdd(User user) {
		userDao.insertForMySql(user);
	}

	@Transactional
	public void userModify(User user) {
		userDao.updateForMySql(user);
	}

	public User userDetail(String userId) {
		return userDao.userDetail(userId);
	}
	
	public List<User> getUsersByUserId(List<String> list) {
		return userDao.getUsersByUserId(list);
	}

	public List<RoleInfoResponse> userRoleInfo(String userId) {
		return userDao.userRoleInfo(userId);
	}
	
	@Transactional
	public void userRoleAssignment(String userId,List<RoleIdAndStrategyRequest> list) {
		Map<String,Object> map = new HashMap<>();
		List<UserRole> userRoleList = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			UserRole userRole = new UserRole();
			userRole.setId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
			userRole.setUserId(userId);
			userRole.setRoleId(list.get(i).getRoleId());
			Integer moduleStrategy = list.get(i).getModuleStrategy();
			Integer dataStrategy = list.get(i).getDataStrategy();
			userRole.setModuleStrategy(moduleStrategy==null?0:moduleStrategy);
			userRole.setDataStrategy(dataStrategy==null?0:dataStrategy);
			userRoleList.add(userRole);
		}
		map.put("userId",userId);
		map.put("list",userRoleList);
		userDao.userRoleAssignment(map);
	}
	
	public List<ModuleInfoResponse> userModuleInfo(String userId) {
		List<ModuleInfoResponse> list = userDao.userModuleInfo(userId);
		list = setTreeList(list,null);
		return list;
	}
	
	//封装成树形结构集合
	private List<ModuleInfoResponse> setTreeList(List<ModuleInfoResponse> originList,ModuleInfoResponse moduleInfoResponse){
		List<ModuleInfoResponse> moduleList = new ArrayList<>();
		for (int i = 0; i < originList.size(); i++) {
			ModuleInfoResponse currentModule = originList.get(i);
			if((moduleInfoResponse!=null&&moduleInfoResponse.getModuleId().equals(currentModule.getParentId()))||(moduleInfoResponse==null&&currentModule.getParentId()==null)){
				currentModule.setList(setTreeList(originList,currentModule));
				moduleList.add(currentModule);
			}
		}
		return moduleList;
	}
	
	@Transactional
	public void userModuleAssignment(String userId,List<String> list) {
		Map<String,Object> map = new HashMap<>();
		List<UserModule> userModuleList = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			UserModule userModule = new UserModule();
			userModule.setId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
			userModule.setUserId(userId);
			userModule.setModuleId(list.get(i));
			userModuleList.add(userModule);
		}
		map.put("userId",userId);
		map.put("list",userModuleList);
		userDao.userModuleAssignment(map);
	}

}
