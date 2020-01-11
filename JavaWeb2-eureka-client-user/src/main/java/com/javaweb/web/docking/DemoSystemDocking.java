package com.javaweb.web.docking;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.util.core.HttpUtil;

public class DemoSystemDocking {

    public static void main(String[] args) throws Exception {
        String out = HttpUtil.defaultGetRequest("http://www.www.com",null);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        DemoDockingEntity demoDockingEntity = objectMapper.readValue(out,DemoDockingEntity.class);
        System.out.println(demoDockingEntity.getId()+","+demoDockingEntity.getUserName()+","+demoDockingEntity.getAge());
    }
    
}
