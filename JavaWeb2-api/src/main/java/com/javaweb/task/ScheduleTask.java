package com.javaweb.task;

import org.springframework.stereotype.Component;

import com.javaweb.base.BaseService;

@Component
public class ScheduleTask extends BaseService {
	
	//参考:https://tool.lu/crontab
	//使用请解注:@Scheduled(fixedRate=10000)
	public void test(){
		System.out.println("我每隔10秒被输出一次");
	}

}
