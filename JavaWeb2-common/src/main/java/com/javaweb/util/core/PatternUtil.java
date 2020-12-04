package com.javaweb.util.core;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import com.javaweb.constant.CommonConstant;

public class PatternUtil {
    
    public static final Pattern NUMBER_PATTERN = Pattern.compile("[\\d]+[.]?[\\d]*");//数字的正则
    
    public static final Pattern ZERO_PATTERN = Pattern.compile("[+-]?0.?[0]*");//0的正则
    
    public static boolean isPattern(String str,Pattern pattern) {
    	if(str==CommonConstant.NULL_VALUE||CommonConstant.EMPTY_VALUE.equals(str)){
			return false;
		}
        Predicate<String> predicate = obj -> pattern.matcher(obj).matches();
        return predicate.test(str);
    }

}
