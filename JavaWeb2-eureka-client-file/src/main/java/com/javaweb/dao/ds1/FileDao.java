package com.javaweb.dao.ds1;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.mybatis.api.DaoWapper;
import com.javaweb.po.File;

@Mapper
public interface FileDao extends DaoWapper<File> {
	
}
