package com.javaweb.config.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//多个任务就用多个类实现Job
public class QuartzTask implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
         System.out.println("此处为定时任务的逻辑");
    }

}
