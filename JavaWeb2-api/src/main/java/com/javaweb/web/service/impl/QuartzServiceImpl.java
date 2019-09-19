package com.javaweb.web.service.impl;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.base.BaseService;
import com.javaweb.util.core.DateUtil;
import com.javaweb.web.po.Quartz;
import com.javaweb.web.service.QuartzService;

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
            triggerBuilder = triggerBuilder.startAt(DateUtil.LocalDateTimeToDate(DateUtil.getDateTime(quartz.getExecuteStartTime(),DateUtil.DEFAULT_DATETIME_PATTERN)));
        }
        trigger = triggerBuilder.withSchedule(scheduleBuilder).build();
        //trigger.getJobDataMap().put("invokeParam",quartz.getClass().getName());    
        boolean executeSuccess = true;
        try{
            scheduler.scheduleJob(jobDetail,trigger);//quartz定时任务保存成功
        }catch(SchedulerException e) {
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
    
    //TODO
    /*
    Quartz quartz = new Quartz();
    quartz.setJobId(SecretUtil.defaultGenUniqueStr());
    quartz.setCronExpression("0/4 * * * * ?");//每4秒执行一次
    quartz.setCreator(SystemConstant.ADMIN);
    quartzService.save(quartz,new QuartzTask());
    */
    /**
    //获取Job状态
    public String getJobState(String jobName, String jobGroup) throws SchedulerException {             
        TriggerKey triggerKey = new TriggerKey(jobName, jobGroup);    
        return scheduler.getTriggerState(triggerKey).name();
    }
    
    //暂停所有任务
    public void pauseAllJob() throws SchedulerException {            
        scheduler.pauseAll();
    }
   
   //暂停任务
   public String pauseJob(String jobName, String jobGroup) throws SchedulerException {            
       JobKey jobKey = new JobKey(jobName, jobGroup);
       JobDetail jobDetail = scheduler.getJobDetail(jobKey);
       if (jobDetail == null) {
            return "fail";
       }else {
            scheduler.pauseJob(jobKey);
            return "success";
       }
   }
   
   //恢复所有任务
   public void resumeAllJob() throws SchedulerException {            
       scheduler.resumeAll();
   }
   
   // 恢复某个任务
   public String resumeJob(String jobName, String jobGroup) throws SchedulerException {
       JobKey jobKey = new JobKey(jobName, jobGroup);
       JobDetail jobDetail = scheduler.getJobDetail(jobKey);
       if (jobDetail == null) {
           return "fail";
       }else {               
           scheduler.resumeJob(jobKey);
           return "success";
       }
   }
   
   //删除某个任务
   public String  deleteJob(AppQuartz appQuartz) throws SchedulerException {            
       JobKey jobKey = new JobKey(appQuartz.getJobName(), appQuartz.getJobGroup());
       JobDetail jobDetail = scheduler.getJobDetail(jobKey);
       if (jobDetail == null ) {
            return "jobDetail is null";
       }else if(!scheduler.checkExists(jobKey)) {
           return "jobKey is not exists";
       }else {
            scheduler.deleteJob(jobKey);
            return "success";
       }  
   }
   
   //修改任务
   public String  modifyJob(AppQuartz appQuartz) throws SchedulerException {            
       if (!CronExpression.isValidExpression(appQuartz.getCronExpression())) {
           return "Illegal cron expression";
       }
       TriggerKey triggerKey = TriggerKey.triggerKey(appQuartz.getJobName(),appQuartz.getJobGroup());            
       JobKey jobKey = new JobKey(appQuartz.getJobName(),appQuartz.getJobGroup());
       if (scheduler.checkExists(jobKey) && scheduler.checkExists(triggerKey)) {
           CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);            
           //表达式调度构建器,不立即执行
           CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(appQuartz.getCronExpression()).withMisfireHandlingInstructionDoNothing();
           //按新的cronExpression表达式重新构建trigger
           trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
               .withSchedule(scheduleBuilder).build();
           //修改参数
           if(!trigger.getJobDataMap().get("invokeParam").equals(appQuartz.getInvokeParam())) {
               trigger.getJobDataMap().put("invokeParam",appQuartz.getInvokeParam());
           }                
           //按新的trigger重新设置job执行
           scheduler.rescheduleJob(triggerKey, trigger);                                                
           return "success";                    
       }else {
           return "job or trigger not exists";
       }
   }
   */

}
