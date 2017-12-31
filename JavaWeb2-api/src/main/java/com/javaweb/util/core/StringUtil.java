package com.javaweb.util.core;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Optional;

import com.javaweb.constant.CommonConstant;

public class StringUtil{
	
	//处理字符串的null值,如果为null返回空
	public static String handleNullString(String str) {
		Optional<String> optional = Optional.ofNullable(str);
		return optional.map(String::toString).orElse(CommonConstant.EMPTY_VALUE);
	}
	
	//判断是否含有中文字符
	public static boolean isContainChinese(String str){
		str = handleNullString(str);
		if(str.length()==str.getBytes().length){
			return false;
		}
		return true;
	}
	
	//或取某个类的绝对路径
	public static String getClassAbsolutePath(Class<?> c){
		//ArrayUtil.class.getClass().getResource("/").getPath();
		String filePath = c.getProtectionDomain().getCodeSource().getLocation().getFile();
		try {
			//结果如下->/C:/Users/admin/Desktop/util-collections/target/classes/
			return URLDecoder.decode(filePath,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
}
