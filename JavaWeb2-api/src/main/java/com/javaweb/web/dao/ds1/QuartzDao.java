package com.javaweb.web.dao.ds1;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.base.BaseDaoForMySql;
import com.javaweb.web.po.Quartz;

@Mapper
public interface QuartzDao extends BaseDaoForMySql<Quartz> {
    
    public void updateAll(Integer status);
	
}