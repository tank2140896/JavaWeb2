package com.javaweb.config.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {
	
	//该注解一般作用于serviceImplment的方法中,若要作用于@Autowired标注的dao或service接口难度较大,个人未找到较好的办法
	//@Before("execution(* com.javaweb.web.dao..*.*(..))")
	@Pointcut("@annotation(com.javaweb.config.datasource.TargetDataSource)")
	public void dataSourcePointCut() {	
		
	}

	@Around("dataSourcePointCut()")
	//这里写具体的数据源切换逻辑
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = methodSignature.getMethod();
		TargetDataSource targetDataSource = method.getAnnotation(TargetDataSource.class);
		if(targetDataSource==null){
			DynamicDataSource.setDataSource(DataSourceName.MYSQL_DS_1);
		}else{
			String dataSourceName = DataSourceName.DATA_SOURCE_MAP.get(targetDataSource.name());
			if(dataSourceName==null){
				DynamicDataSource.setDataSource(DataSourceName.MYSQL_DS_1);
			}else{
				DynamicDataSource.setDataSource(dataSourceName);
			}
		}
		try{
			return proceedingJoinPoint.proceed();
		}finally{
			DynamicDataSource.clearDataSource();
		}
	}

}
