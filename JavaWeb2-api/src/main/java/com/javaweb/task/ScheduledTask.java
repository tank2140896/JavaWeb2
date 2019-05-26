package com.javaweb.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;

//@Component//使用请解注
public class ScheduledTask implements SchedulingConfigurer {
	
	//一般用于读取数据的定时任务操作
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
		scheduledTaskRegistrar.addFixedRateTask(()->System.out.println("我每隔3秒被输出一次"),3000);
		
		TriggerTask triggrtTask = new TriggerTask(
			()->{System.out.println("我每隔4秒被输出一次");},
			triggerContext->{
				return new CronTrigger("0/4 * * * * ?").nextExecutionTime(triggerContext);
			});
		scheduledTaskRegistrar.addTriggerTask(triggrtTask);
		
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(()->System.out.println("我每隔5秒被输出一次"),0,5,TimeUnit.SECONDS);
		scheduledExecutorService.scheduleAtFixedRate(()->System.out.println("我每隔6秒被输出一次"),0,6,TimeUnit.SECONDS);
	}
	
}
