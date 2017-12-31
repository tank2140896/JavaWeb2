package com.javaweb.web.dao.ds1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.base.BaseDao;
import com.javaweb.web.eo.UserLogin;
import com.javaweb.web.po.Module;
import com.javaweb.web.po.User;

@Mapper
public interface UserDao extends BaseDao<User> {
	
	public User userLogin(UserLogin userLogin);
	
	public List<Module> getUserRoleModule(Map<String, Object> map);
	
}