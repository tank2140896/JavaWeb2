package com.javaweb.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.javaweb.constant.CommonConstant;
import com.javaweb.web.po.Dictionary;
import com.javaweb.web.po.Interfaces;

public class BaseSystemMemory {
    
    public static List<Dictionary> dictionaryList = new ArrayList<>();
    
    public static List<Interfaces> interfacesList = new ArrayList<>();
    
    public static List<Dictionary> getDictionaryByDataType(String dataType){
        return dictionaryList.stream().filter(each->{return each.getDataType().equals(dataType);}).collect(Collectors.toList());
    }
    
    public static Dictionary getDictionaryByKey(String key){
    	Dictionary dictionary = null;
    	Optional<Dictionary> optional = dictionaryList.stream().filter(each->{return each.getKeyCode().equals(key);}).findFirst();
    	if(optional.isPresent()){
    		dictionary = optional.get();
    	}
    	return dictionary;
    }
    
    public static String getDictionaryValueByKey(String key){
    	String value = CommonConstant.NULL_VALUE;
    	Dictionary dictionary = getDictionaryByKey(key);
    	if(dictionary!=null){
    		value = dictionary.getValueCode();
    	}
    	return value;
    }
    
    public static String getDictionaryValueByKey(String key,String defaultValue){
    	String value = getDictionaryValueByKey(key);
    	if(value==CommonConstant.NULL_VALUE){
    		value = defaultValue;
    	}
    	return value;
    }
    
}
