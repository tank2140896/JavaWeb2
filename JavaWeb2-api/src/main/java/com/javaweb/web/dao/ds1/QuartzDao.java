package com.javaweb.web.dao.ds1;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.dao.DaoForMySql;
import com.javaweb.web.po.Quartz;

@Mapper
public interface QuartzDao extends DaoForMySql<Quartz> {
    
    public void updateAll(Integer status);
	
}