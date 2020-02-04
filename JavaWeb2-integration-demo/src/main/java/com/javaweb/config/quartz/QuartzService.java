package com.javaweb.config.quartz;

import org.quartz.Job;

import com.javaweb.po.Quartz;

public interface QuartzService {
    
    public void save(Quartz quartz,Job job); 
    
    public void update(Quartz quartz);
    
    public String getState(Quartz quartz);
    
    public void pauseAll();
    
    public void resumeAll();
    
    public void pause(Quartz quartz);
    
    public void resume(Quartz quartz);
    
    public void delete(Quartz quartz);
	
}
