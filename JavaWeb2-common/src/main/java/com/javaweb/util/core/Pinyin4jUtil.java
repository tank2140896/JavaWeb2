package com.javaweb.util.core;

import com.javaweb.constant.CommonConstant;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

public class Pinyin4jUtil {
	
	//获得字符串首个词的拼音大写字母
	public static String getFirstWord(String str){
	    if(StringUtil.handleNullString(str).trim().equals(CommonConstant.EMPTY_VALUE)){
	    	return "#";
	    }
	    char ch = str.charAt(0);
	    if(ch>='a'&&ch<='z'){
	        return (char)(ch-'a'+'A')+CommonConstant.EMPTY_VALUE;
	    }
	    if(ch>='A'&&ch<='Z'){
	        return ch+CommonConstant.EMPTY_VALUE;
	    }
	    try{
	        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
	        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);//设置大小写格式
	        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//设置声调格式
	        if(Character.toString(ch).matches("[\\u4E00-\\u9FA5]+")){//汉字范围
	            String[] array = PinyinHelper.toHanyuPinyinStringArray(ch,defaultFormat);
	            if(array!=null){
	                return array[0].charAt(0)+CommonConstant.EMPTY_VALUE;
	            }
	        }
	    }catch(Exception e){
	        //do nothing
	    }
	    return "#";
	}
	
}
