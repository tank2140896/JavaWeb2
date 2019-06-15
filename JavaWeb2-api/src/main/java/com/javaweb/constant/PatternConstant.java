package com.javaweb.constant;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class PatternConstant {
	
	public static final Pattern HEAD_TYPE_PATTERN = Pattern.compile(SystemConstant.HEAD_TYPE_PATTERN);
	
	public static boolean isPattern(String str,Pattern pattern) {
		Predicate<String> predicate = obj -> pattern.matcher(obj).matches();
		return predicate.test(str);
	}

}
