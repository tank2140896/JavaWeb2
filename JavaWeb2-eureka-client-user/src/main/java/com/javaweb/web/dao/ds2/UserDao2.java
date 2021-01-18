package com.javaweb.web.dao.ds2;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.db.mybatis.api.DaoWapper;
import com.javaweb.web.po.User;

@Mapper
public interface UserDao2 extends DaoWapper<User> {
	
	
}