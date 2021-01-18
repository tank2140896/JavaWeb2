package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.db.mybatis.api.DaoWapper;
import com.javaweb.web.po.UserModule;

@Mapper
public interface UserModuleDao extends DaoWapper<UserModule> {
	
	public List<String> getModuleIdsByUserId(String userId);
	
}