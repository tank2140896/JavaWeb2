package com.javaweb.util.core;

import java.math.RoundingMode;
import java.text.NumberFormat;

import com.javaweb.constant.CommonConstant;

public class NumberFormatUtil {
	
	/**
	示例一：
	BigDecimal bigDecimal = new BigDecimal("0.000000");
	bigDecimal = bigDecimal.setScale(3,RoundingMode.HALF_UP);
	示例二：
	BigDecimal bigDecimal = new BigDecimal("20.786");
	bigDecimal = bigDecimal.add(new BigDecimal("1.00000"));
	bigDecimal = bigDecimal.setScale(2,RoundingMode.HALF_UP);
	*/
	//保留小数位数(new DecimalFormat("#,##0.00").format(number))
	public static String roundNumber(Object number,int degree){
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMaximumFractionDigits(degree);
		return numberFormat.format(number);
	}
	
	//保留小数位数(new DecimalFormat("#,##0.00").format(number))
	public static String roundNumber(Object number,int degree,RoundingMode roundingMode){
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMaximumFractionDigits(degree);
		numberFormat.setRoundingMode(roundingMode);
		return numberFormat.format(number);
	}
	
	//任意形式的0格式字符串转成纯0的字符串
	public static String zeroFormat(String zeroString) {
	    if(PatternUtil.ZERO_PATTERN.matcher(zeroString).matches()) {
	        return CommonConstant.ZERO_STRING_VALUE;
	    }
	    return zeroString;
	}

}
