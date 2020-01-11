package com.javaweb.config.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.javaweb.annotation.datasource.DataSource;
import com.javaweb.constant.SystemConstant;

@Aspect
@Component
public class DataSourceAspect {

	//这里写具体的数据源切换逻辑
	@Before(value=SystemConstant.DEFAULT_DATA_SOURCE_POINT_CUT)
	public void beforeMethod(JoinPoint joinPoint) throws Throwable {
	    Class<?> targetClass = joinPoint.getTarget().getClass();
	    if(targetClass!=null) {
	        for(Class<?> interfaceClass:targetClass.getInterfaces()) {//获得接口类
	            DataSource dataSource = interfaceClass.getAnnotation(DataSource.class);
	            if(dataSource!=null) {//优先注解
	                String value = dataSource.value();
	                if(SystemConstant.DATA_SOURCE_PACKAGE_NAME_1.equals(value)) {
	                    MultipleDataSourceManage.setDataSourceKey(SystemConstant.DATA_SOURCE_KEY_1);//数据源1的位置
	                }else if(SystemConstant.DATA_SOURCE_PACKAGE_NAME_2.equals(value)) {
	                    MultipleDataSourceManage.setDataSourceKey(SystemConstant.DATA_SOURCE_KEY_2);//数据源2的位置
	                }else {
	                    MultipleDataSourceManage.setDataSourceKey(SystemConstant.DATA_SOURCE_KEY_1);
	                }
	            }else {//次优先包
	                String packagePathDao = joinPoint.getStaticPart().getSignature().getDeclaringTypeName();
	                if(packagePathDao.contains(SystemConstant.DATA_SOURCE_PACKAGE_NAME_1)){//数据源1的位置
	                    MultipleDataSourceManage.setDataSourceKey(SystemConstant.DATA_SOURCE_KEY_1);
	                }else if(packagePathDao.contains(SystemConstant.DATA_SOURCE_PACKAGE_NAME_2)){//数据源2的位置
	                    MultipleDataSourceManage.setDataSourceKey(SystemConstant.DATA_SOURCE_KEY_2);
	                }else {//默认数据源
	                    MultipleDataSourceManage.setDataSourceKey(SystemConstant.DATA_SOURCE_KEY_1);
	                }
	            }
	        }
	    }
	}

}
