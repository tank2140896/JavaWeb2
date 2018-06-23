package com.javaweb.task;

import org.springframework.stereotype.Component;

import com.javaweb.base.BaseService;

@Component
public class ScheduleTask extends BaseService {
	
	/** 广播
	@Autowired
	private WebSocketHandleService webSocketHandleService;
	
	User user = new User();
	user.setUserName("超级管理员");
	webSocketHandleService.onMessage("我是超级管理员",user);
	*/
	
	//参考:https://tool.lu/crontab
	//使用请解注:@Scheduled(fixedRate=10000)
	public void test(){
		System.out.println("我每隔10秒被输出一次");
	}

}
