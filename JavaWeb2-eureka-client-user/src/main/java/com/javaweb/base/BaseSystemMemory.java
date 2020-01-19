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
    
}
