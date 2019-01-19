package com.javaweb.constant;

import java.util.regex.Pattern;

public class PatternConstant {
	
	private static final Pattern HEAD_TYPE_PATTERN = Pattern.compile(SystemConstant.HEAD_TYPE_PATTERN);
	
	public static boolean isHeadTypePattern(String type) {
		return HEAD_TYPE_PATTERN.matcher(type).matches();
	}

}
