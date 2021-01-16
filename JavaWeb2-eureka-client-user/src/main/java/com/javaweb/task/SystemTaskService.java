package com.javaweb.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.javaweb.base.BaseService;
import com.javaweb.base.BaseSystemMemory;
import com.javaweb.web.service.DictionaryService;
import com.javaweb.web.service.InterfacesService;

@Component
//@Async//若该类所有方法都是异步执行方法,则直接在该类上加上@Async即可,类里的方法就不需要都加上@Async了
public class SystemTaskService extends BaseService {
	
	private Logger logger = LoggerFactory.getLogger(SystemTaskService.class);
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private InterfacesService interfacesService;
	
	//将字典表数据加载进内存
    @Async  
    public void task_loadDictionaryInSystemMemory() { 
    	BaseSystemMemory.dictionaryList = dictionaryService.selectAll(); 
    	logger.info("[将字典表数据加载进内存]执行完毕");
    } 
      
    //同步数据库中的接口信息表
    @Async  
    public void task_synchronizedInterfaces() {  
    	interfacesService.synchronizedInterfaces();//同步数据库中的接口信息表
    	logger.info("[同步数据库中的接口信息表]执行完毕");
    	BaseSystemMemory.interfacesList = interfacesService.getAll();
    	logger.info("[将所有接口信息加载进内存]执行完毕");
    	interfacesService.synchronizedRedisInterfaceHistoryTimes();//同步redis中的各个接口调用次数
    	logger.info("[同步redis中的各个接口调用次数]执行完毕");
    }
    
    //每隔1小时执行一次（整点）
    @Async
	@Scheduled(cron="0 0 */1 * * ?")//https://tool.lu/crontab
	public void cronTask_1(){
    	interfacesService.synchronizedRedisInterfaceHistoryTimes();//同步redis中的各个接口调用次数
    	logger.info("每隔1小时[同步redis中的各个接口调用次数]执行完毕");
	}
    
    /**
    //每隔60秒执行一次
    @Async
    @Scheduled(fixedDelay=60000)//@Scheduled(fixedRate=60000)
    public void cronTask_2() {
    	Runtime runtime = Runtime.getRuntime();
    	long maxMemory = runtime.maxMemory();
    	long totalMemory = runtime.totalMemory();
    	long freeMemory = runtime.freeMemory();
    	logger.info("每隔60秒[" + 
    	            "可以获得的最大总内存是：" + NumberFormatUtil.getdefaultFormatCapacity(new BigDecimal(maxMemory)) + 
    			    "，目前已经分配到的内存是：" + NumberFormatUtil.getdefaultFormatCapacity(new BigDecimal(totalMemory)) + 
    			    "，目前使用分配到的内存是：" + NumberFormatUtil.getdefaultFormatCapacity(new BigDecimal(totalMemory-freeMemory)) + 
    			    "，目前剩余分配到的内存是：" + NumberFormatUtil.getdefaultFormatCapacity(new BigDecimal(freeMemory)) + 
    			    "，还可以获得的最大剩余内存是：" + NumberFormatUtil.getdefaultFormatCapacity(new BigDecimal(maxMemory - (totalMemory - freeMemory))) + 
    			    "]执行完毕");
    }
    */
    
	/** 广播
	@Autowired
	private WebSocketHandleService webSocketHandleService;
	
	User user = new User();
	user.setUserName("超级管理员");
	webSocketHandleService.onMessage("我是超级管理员",user);
	*/
    
    /**
    @Async  
    //public Future<String> task3() throws InterruptedException {
    public void task3() throws InterruptedException {  
        long currentTimeMillis1 = System.currentTimeMillis();  
        Thread.sleep(3000);  
        long currentTimeMillis2 = System.currentTimeMillis();  
        System.out.println("task3任务耗时:"+(currentTimeMillis2-currentTimeMillis1)+"ms");  
        //return new AsyncResult<String>("task3执行完毕");  
    }
    */
    
}
