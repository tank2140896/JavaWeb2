package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.db.mybatis.api.DaoWapper;
import com.javaweb.web.po.UserData;

@Mapper
public interface UserDataDao extends DaoWapper<UserData> {
	
	public List<UserData> selectAllByUserId(String userId);
	
}
