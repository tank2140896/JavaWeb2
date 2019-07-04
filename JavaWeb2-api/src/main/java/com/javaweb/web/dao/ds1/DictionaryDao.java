package com.javaweb.web.dao.ds1;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.base.BaseDaoForMySql;
import com.javaweb.web.po.Dictionary;

@Mapper
public interface DictionaryDao extends BaseDaoForMySql<Dictionary> {
	
}