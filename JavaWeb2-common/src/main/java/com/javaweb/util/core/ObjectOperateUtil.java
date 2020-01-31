package com.javaweb.util.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ObjectOperateUtil {
	
	private static final String METHOD_GET = "get"; 
	
	private static final String METHOD_SET = "set";
	
	private static final Object[] NULL_OBJECT = new Object[]{};//无返回值（void）
	
	private static final Class<?>[] NULL_CLASS = new Class[]{};//无参
	
	//对象映射转换
	public static List<Object> objectMapperConversion(List<Object> sourceList,Class<?> target,Map<String,String> map) throws Exception {
		List<Object> targetList = new ArrayList<>();//目标映射列表
		String sourceFiledName,sourceMethodName,targetFiledName,targetMethodName;
		Method sourcetMethod,targetMethod;
		for(int i=0;i<sourceList.size();i++){//遍历源映射列表中的每一个对象
			Object sourceObject = sourceList.get(i);//获得源映射列表中的每一个对象
			Object tarjetObject = target.newInstance();//生成目标对象的实例
			Field[] sourceFields = sourceObject.getClass().getDeclaredFields();//获得源对象的所有属性
			for(int j=0;j<sourceFields.length;j++){//获得源对象的每个属性
				sourceFiledName = sourceFields[j].getName();//获得源对象的属性名称
				targetFiledName = map.get(sourceFiledName);//通过map映射获得目标对象的属性名称
				if(targetFiledName==null){
					continue;
				}
				//得到源对象对应的get方法名称
				sourceMethodName = METHOD_GET+sourceFiledName.substring(0,1).toUpperCase()+sourceFiledName.substring(1,sourceFiledName.length());
				//拼接获得目标对象的set方法名称
				targetMethodName = METHOD_SET+targetFiledName.substring(0,1).toUpperCase()+targetFiledName.substring(1,targetFiledName.length());
				//得到源对象对应的get方法
				sourcetMethod = sourceObject.getClass().getMethod(sourceMethodName,NULL_CLASS);
				//得到目标对象对应的set方法
				targetMethod = tarjetObject.getClass().getMethod(targetMethodName,sourceFields[j].getType());
				//唤醒目标对象的set方法和源对象的get方法
				targetMethod.invoke(tarjetObject,sourcetMethod.invoke(sourceObject,NULL_OBJECT));
			}
			targetList.add(tarjetObject);
		}
		return targetList;
	}
	
	//获取简单类名
	public static String getSimpleClassName(Class<?> c,boolean firstWordLowerCase){
		String simpleName = c.getSimpleName(); 
		if(firstWordLowerCase){
			return simpleName.substring(0,1).toLowerCase()+simpleName.substring(1,simpleName.length());
		}else{
			return simpleName;
		}
	}
	
	//属性排除
	public static String excludeField(Object obj,String[] excludes,boolean isArray){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		if(isArray){
			return JSONArray.fromObject(obj,jsonConfig).toString();
		}else{
			return JSONObject.fromObject(obj,jsonConfig).toString();
		}
	}

}
