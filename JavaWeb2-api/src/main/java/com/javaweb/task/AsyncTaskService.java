package com.javaweb.task;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.javaweb.base.BaseService;

@Component
public class AsyncTaskService extends BaseService {
	
	@Async  
    public Future<String> task1() throws InterruptedException{  
        long currentTimeMillis1 = System.currentTimeMillis();  
        Thread.sleep(1000);  
        long currentTimeMillis2 = System.currentTimeMillis();
        System.out.println(userDao.selectAll().size());
        System.out.println("task1任务耗时:"+(currentTimeMillis2-currentTimeMillis1)+"ms");  
        return new AsyncResult<String>("task1执行完毕");  
    }  
      
    @Async  
    public Future<String> task2() throws InterruptedException{  
        long currentTimeMillis1 = System.currentTimeMillis();  
        Thread.sleep(2000);  
        long currentTimeMillis2 = System.currentTimeMillis();  
        System.out.println("task2任务耗时:"+(currentTimeMillis2-currentTimeMillis1)+"ms");  
        return new AsyncResult<String>("task2执行完毕");  
    }  

}
