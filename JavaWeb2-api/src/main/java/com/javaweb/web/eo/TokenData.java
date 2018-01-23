package com.javaweb.web.eo;

import java.io.Serializable;
import java.util.List;

import com.javaweb.web.po.Module;
import com.javaweb.web.po.User;

public class TokenData implements Serializable {
	
	private static final long serialVersionUID = -6256223897799749383L;

	private String token;
	
	private User user;
	
	private List<Module> moduleList;
	
	private List<Module> menuList;
	
	private List<Module> authOperateList;
	
	public TokenData(){	}
	
	public TokenData(String token,User user,List<Module> moduleList,List<Module> menuList,List<Module> authOperateList){
		this.token = token;
		this.user = user;
		this.moduleList = moduleList;
		this.menuList = menuList;
		this.authOperateList = authOperateList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public List<Module> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Module> menuList) {
		this.menuList = menuList;
	}

	public List<Module> getAuthOperateList() {
		return authOperateList;
	}

	public void setAuthOperateList(List<Module> authOperateList) {
		this.authOperateList = authOperateList;
	}
	
}
