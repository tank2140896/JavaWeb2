package com.javaweb.web.dao.ds1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonDao {
	
	List<Map<String,Object>> sqlSelect(String sqlSelect);
	
	Integer sqlUpdate(String sqlUpdate);
	
	Integer sqlInsert(String sqlInsert);
	
	Integer sqlDelete(String sqlDelete);
	
}