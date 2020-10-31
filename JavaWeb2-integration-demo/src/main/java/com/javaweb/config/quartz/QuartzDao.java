package com.javaweb.config.quartz;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.mybatis.api.DaoWapper;
import com.javaweb.po.Quartz;

@Mapper
public interface QuartzDao extends DaoWapper<Quartz> {
    
    public void updateAll(Integer status);
	
}