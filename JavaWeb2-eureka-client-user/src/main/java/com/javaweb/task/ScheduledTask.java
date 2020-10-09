package com.javaweb.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;

//使用请解注
//@Component
public class ScheduledTask implements SchedulingConfigurer {
	
	//一般用于读取数据的定时任务操作
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
	    Timer timer = new Timer();
        try {
            timer.schedule(new TimerTask() {
                public void run() {
                    System.out.println("时间到了，我要执行了");
                    timer.cancel();
                }
            },new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-07-07 07:07:07"));
        } catch (ParseException e) {
             e.printStackTrace();
        }
	    
	    ConcurrentTaskScheduler concurrentTaskScheduler = new ConcurrentTaskScheduler();
	    concurrentTaskScheduler.execute(()->System.out.println("我只会被执行一次"));
	    concurrentTaskScheduler.schedule(()->System.out.println("我会被多次执行"),new CronTrigger("0/2 * * * * ?"));

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
		ScheduledFuture<String> scheduledFuture = scheduledExecutorService.schedule(new Callable<String>() {
			public String call() throws Exception {
				TimeUnit.SECONDS.sleep(2);
				return "2秒间隔后只会输出一次";
			}
		},7,TimeUnit.SECONDS);
		try {
            System.out.println(scheduledFuture.get());
        } catch (Exception e) {
             e.printStackTrace();
        }
	}
	
}
