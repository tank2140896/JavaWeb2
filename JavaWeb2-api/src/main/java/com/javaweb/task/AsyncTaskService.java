package com.javaweb.task;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.javaweb.base.BaseService;

@Component
//@Async//若该类所有方法都是异步执行方法,则直接在该类上加上@Async即可,类里的方法就不需要都加上@Async了
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
    
    @Async  
    public /*Future<String>*/ void task3() throws InterruptedException{  
        long currentTimeMillis1 = System.currentTimeMillis();  
        Thread.sleep(3000);  
        long currentTimeMillis2 = System.currentTimeMillis();  
        System.out.println("task3任务耗时:"+(currentTimeMillis2-currentTimeMillis1)+"ms");  
        //return new AsyncResult<String>("task3执行完毕");  
    }  

}