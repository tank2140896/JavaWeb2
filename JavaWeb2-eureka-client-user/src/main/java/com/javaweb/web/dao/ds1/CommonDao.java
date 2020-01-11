package com.javaweb.web.dao.ds1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonDao {
	
	public List<Map<String,Object>> sqlSelect(String sqlSelect);
	
	public Integer sqlUpdate(String sqlUpdate);
	
	public Integer sqlInsert(String sqlInsert);
	
	public Integer sqlDelete(String sqlDelete);
	
}