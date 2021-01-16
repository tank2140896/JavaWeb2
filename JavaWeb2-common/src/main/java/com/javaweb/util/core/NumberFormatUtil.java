package com.javaweb.util.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import com.javaweb.constant.CommonConstant;

public class NumberFormatUtil {
	
    public static final BigDecimal BASE_NUM = new BigDecimal(1024);
    public static final BigDecimal BYTE = new BigDecimal("1");
    public static final BigDecimal KILO_BYTE = BASE_NUM.multiply(BYTE);//KB
    public static final BigDecimal MEGA_BYTE = BASE_NUM.multiply(KILO_BYTE);//MB
    public static final BigDecimal GIGA_BYTE = BASE_NUM.multiply(MEGA_BYTE);//GB
    public static final BigDecimal TERA_BYTE = BASE_NUM.multiply(GIGA_BYTE);//TB
    
    public static final String BYTE_SHORT = "byte";
    public static final String KILO_BYTE_SHORT = "KB";
    public static final String MEGA_BYTE_SHORT = "MB";
    public static final String GIGA_BYTE_SHORT = "GB";
    public static final String TERA_BYTE_SHORT = "TB";
    
    //容量转换
    public static String getdefaultFormatCapacity(BigDecimal byteNum){
    	return getFormatCapacity(byteNum,1,RoundingMode.HALF_UP);
    }
    
    //容量转换
    public static String getFormatCapacity(BigDecimal byteNum,int scale,RoundingMode roundingMode){
    	if(!MathUtil.bigDecimalCompare(byteNum,BASE_NUM)){//byte
    		return byteNum.toString() + BYTE_SHORT;
    	}
    	if(!MathUtil.bigDecimalCompare(byteNum,MEGA_BYTE)){//KB
    		return byteNum.divide(KILO_BYTE,scale,roundingMode).toString() + KILO_BYTE_SHORT;
    	}
    	if(!MathUtil.bigDecimalCompare(byteNum,GIGA_BYTE)){//MB
    		return byteNum.divide(MEGA_BYTE,scale,roundingMode).toString() + MEGA_BYTE_SHORT;
    	}
    	if(!MathUtil.bigDecimalCompare(byteNum,TERA_BYTE)){//GB
    		return byteNum.divide(GIGA_BYTE,scale,roundingMode).toString() + GIGA_BYTE_SHORT;
    	}
    	return byteNum.divide(TERA_BYTE,scale,roundingMode).toString() + TERA_BYTE_SHORT;//TB
    }
	
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
