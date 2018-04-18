package com.javaweb.config.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHelper implements ApplicationContextAware { 
	
    private static ApplicationContext applicationContext;    

    public void setApplicationContext(ApplicationContext ac) throws BeansException {    
    	applicationContext = ac;    
    }  
        
    public static ApplicationContext getApplicationContext(){  
        return applicationContext;  
    }  
   
    public static Object getBean(String beanName) {    
        return applicationContext.getBean(beanName);    
    }  
    
}   