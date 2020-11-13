package com.javaweb.context;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//new ClassPathXmlApplicationContext(xmlFilePath).getBean(beanName)
public class ApplicationContextHelper implements ApplicationContextAware { 
	
    private static ApplicationContext applicationContext;  
    
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {   
    	ApplicationContextHelper.applicationContext = applicationContext;    
    }  
        
    public static ApplicationContext getApplicationContext(){  
        return applicationContext;  
    }  
   
    public static Object getBean(String beanName) {    
        return applicationContext.getBean(beanName);    
    }
    
    /**
          使用示例：
    @Component
	public class IdGenerator implements IdentifierGenerator {
		public Long nextId(Object entity) {
			return SecretUtil.defaultGenPkNumForLong(String.valueOf(SystemConstant.SYSTEM_NO));
	 	}
	}
	Map<String,IdentifierGenerator> map = applicationContext.getBeansOfType(IdentifierGenerator.class);
	IdentifierGenerator ig = map.get("idGenerator");
	System.out.println(ig.nextId(null));
	//ServiceLoader<IdentifierGenerator> loader = ServiceLoader.load(IdentifierGenerator.class);
	//System.err.println(loader);        
    */
    public static <T> Map<String,? extends T> getInterfaceImpl(Class<T> interfaceClass){
    	return applicationContext.getBeansOfType(interfaceClass);
    }
    
}   
