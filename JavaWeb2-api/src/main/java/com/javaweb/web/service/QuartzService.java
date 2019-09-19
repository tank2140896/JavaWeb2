package com.javaweb.web.service;

import org.quartz.Job;

import com.javaweb.web.po.Quartz;

public interface QuartzService {
    
    public void save(Quartz quartz,Job job); 
	
}
