package com.javaweb.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.javaweb.config.quartz.QuartzDao;

public class BaseService extends BaseTool {
    
	@Autowired
	protected QuartzDao quartzDao;

}
