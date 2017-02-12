package com.javaweb.dao.datasource2.test1;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDao {
	
	public String getTest();
	
}