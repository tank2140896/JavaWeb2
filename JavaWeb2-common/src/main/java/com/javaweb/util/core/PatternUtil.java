package com.javaweb.util.core;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import com.javaweb.constant.CommonConstant;

public class PatternUtil {
    
	public static final Pattern NUMBER_PATTERN = Pattern.compile("[\\d]+[.]?[\\d]*");//数字的正则
    
	public static final Pattern LETTER_PATTERN = Pattern.compile("(^[a-zA-Z]*)");//字母正则
	
	public static final Pattern NUMBER_LETTER_PATTERN = Pattern.compile("[0-9a-zA-Z]+");//数字字母正则
    
	public static final Pattern ZERO_PATTERN = Pattern.compile("[+-]?0.?[0]*");//0的正则
    
	//是否匹配正则
	public static boolean isPattern(String str,Pattern pattern) {
		if(str==CommonConstant.NULL_VALUE||CommonConstant.EMPTY_VALUE.equals(str)){
			return false;
		}
		Predicate<String> predicate = obj -> pattern.matcher(obj).matches();
		return predicate.test(str);
	}
	
	//正则替换。如将非数字替换为空就是：patternReplace("abc123cba",Pattern.compile("[^0-9]"),"")
	public static String patternReplace(String str,Pattern pattern,String replacement) {
		if(str==CommonConstant.NULL_VALUE){
			return str;
		}
		return pattern.matcher(str).replaceAll(replacement).trim();
	}

}
