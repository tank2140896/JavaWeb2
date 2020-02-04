package com.javaweb.config.quartz;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.mybatis.api.DaoForMySql;
import com.javaweb.po.Quartz;

@Mapper
public interface QuartzDao extends DaoForMySql<Quartz> {
    
    public void updateAll(Integer status);
	
}