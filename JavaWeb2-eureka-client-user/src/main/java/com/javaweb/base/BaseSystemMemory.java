package com.javaweb.base;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.javaweb.web.po.Dictionary;

public class BaseSystemMemory {
    
    public static List<Dictionary> dictionaryList = new ArrayList<>();
    
    public static List<Dictionary> getDictionaryByDataType(String dataType){
        return dictionaryList.stream().filter(each->{return each.getDataType().equals(dataType);}).collect(Collectors.toList());
    }
    
    public static Dictionary getDictionaryByKey(String key){
    	List<Dictionary> list = dictionaryList.stream().filter(each->{return each.getKeyCode().equals(key);}).collect(Collectors.toList());
    	if(list==null||list.size()!=1){
    		return null;
    	}else{
    		return list.get(0);
    	}
    }
    
}
