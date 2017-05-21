package com.javaweb.dataobject.eo;

import java.util.List;

import com.javaweb.dataobject.po.User;

public class TokenData {
	
	private User user;
	
	private List<UserRoleModule> moduleList;
	
	private List<UserRoleModule> menuList;
	
	private List<UserRoleModule> authOperateList;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<UserRoleModule> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<UserRoleModule> moduleList) {
		this.moduleList = moduleList;
	}

	public List<UserRoleModule> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<UserRoleModule> menuList) {
		this.menuList = menuList;
	}

	public List<UserRoleModule> getAuthOperateList() {
		return authOperateList;
	}

	public void setAuthOperateList(List<UserRoleModule> authOperateList) {
		this.authOperateList = authOperateList;
	}

}