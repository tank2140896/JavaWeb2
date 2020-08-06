package com.javaweb.config.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.base.BaseService;
import com.javaweb.po.Quartz;
import com.javaweb.util.core.DateUtil;

@Service("quartzServiceImpl")
public class QuartzServiceImpl extends BaseService implements QuartzService {
    
    @Autowired
    private Scheduler scheduler;
    
    public void save(Quartz quartz,Job job) {
        JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(quartz.getJobId(),quartz.getGroupName()).build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression()).withMisfireHandlingInstructionDoNothing();
        CronTrigger trigger = null;
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(quartz.getJobId(),quartz.getGroupName());
        if(quartz.getExecuteStartTime()==null) {
            triggerBuilder = triggerBuilder.startNow();
        }else {
            triggerBuilder = triggerBuilder.startAt(DateUtil.localDateTimeToDate(DateUtil.getDateTime(quartz.getExecuteStartTime(),DateUtil.DEFAULT_DATETIME_PATTERN)));
        }
        trigger = triggerBuilder.withSchedule(scheduleBuilder).build();
        //trigger.getJobDataMap().put("invokeParam",quartz.getClass().getName());    
        boolean executeSuccess = true;
        try{
            scheduler.scheduleJob(jobDetail,trigger);//quartz定时任务保存成功
        }catch(Exception e) {
            executeSuccess = false;
        }
        if(executeSuccess) {
            quartz.setClassName(job.getClass().getName());
            quartz.setParam(quartz.getClass().getName());
            quartz.setStatus(1);
            quartz.setCreateDate(DateUtil.getDefaultDate());
            quartzDao.insertForMySql(quartz);//quartz定时任务记录保存成功
        }
    }

    public void update(Quartz quartz) {
        JobKey jobKey = new JobKey(quartz.getJobId(),quartz.getGroupName());
        TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobId(),quartz.getGroupName());            
        boolean executeSuccess = true;
        try{
            if(scheduler.checkExists(jobKey)&&scheduler.checkExists(triggerKey)){
                CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);            
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression()).withMisfireHandlingInstructionDoNothing();
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                //if(!trigger.getJobDataMap().get("invokeParam").equals(quartz.getParam())) {
                //    trigger.getJobDataMap().put("invokeParam",quartz.getParam());
                //}                
                scheduler.rescheduleJob(triggerKey, trigger);   
            }
        }catch(Exception e) {
            executeSuccess = false;
        }
        if(executeSuccess) {
            quartz.setUpdateDate(DateUtil.getDefaultDate());
            quartzDao.updateForMySql(quartz);
        }
    }

    public void delete(Quartz quartz) {
        boolean executeSuccess = true;
        try{
            JobKey jobKey = new JobKey(quartz.getJobId(),quartz.getGroupName());
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if(jobDetail!=null&&scheduler.checkExists(jobKey)){
                scheduler.deleteJob(jobKey);
            }
        }catch(Exception e){
            executeSuccess = false;
        }
        if(executeSuccess) {
            quartzDao.deleteForMySql(quartz.getJobId());
        }
    }

    public String getState(Quartz quartz) {
        TriggerKey triggerKey = new TriggerKey(quartz.getJobId(),quartz.getGroupName());    
        try{
            return scheduler.getTriggerState(triggerKey).name();
        }catch(Exception e){
            return null;
        }
    }

    public void pauseAll() {
        boolean executeSuccess = true;
        try{
            scheduler.pauseAll();
        }catch(Exception e){
            executeSuccess = false;
        }
        if(executeSuccess) {
            quartzDao.updateAll(2);
        }
    }

    public void resumeAll() {
        boolean executeSuccess = true;
        try{
            scheduler.resumeAll();
        }catch(Exception e){
            executeSuccess = false;
        }
        if(executeSuccess) {
            quartzDao.updateAll(1);
        }
    }

    public void pause(Quartz quartz) {
        boolean executeSuccess = true;
        try{
            JobKey jobKey = new JobKey(quartz.getJobId(),quartz.getGroupName());
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail!=null) {
                scheduler.pauseJob(jobKey);
            }
        }catch(Exception e){
            executeSuccess = false;
        }
        if(executeSuccess) {
            quartz.setStatus(2);
            quartz.setUpdateDate(DateUtil.getDefaultDate());
            quartzDao.updateForMySql(quartz);
        }
    }

    public void resume(Quartz quartz) {
        boolean executeSuccess = true;
        try{
            JobKey jobKey = new JobKey(quartz.getJobId(),quartz.getGroupName());
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail!=null) {
                scheduler.resumeJob(jobKey);
            }
        }catch(Exception e){
            executeSuccess = false;
        }
        if(executeSuccess) {
            quartz.setStatus(1);
            quartz.setUpdateDate(DateUtil.getDefaultDate());
            quartzDao.updateForMySql(quartz);
        }
    }

}
