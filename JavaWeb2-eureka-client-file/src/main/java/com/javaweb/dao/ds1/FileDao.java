package com.javaweb.dao.ds1;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.mybatis.api.DaoForMySql;
import com.javaweb.po.File;

@Mapper
public interface FileDao extends DaoForMySql<File> {
	
}
