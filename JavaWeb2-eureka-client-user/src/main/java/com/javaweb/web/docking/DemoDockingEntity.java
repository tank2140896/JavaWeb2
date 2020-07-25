package com.javaweb.web.docking;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Getter;
import lombok.Setter;

//注：既然@JSONField用的是com.alibaba.fastjson那么转化时也要用com.alibaba.fastjson，即JSONObject.parseObject(data,DemoDockingEntity.class)
@Getter
@Setter
public class DemoDockingEntity implements Serializable {

    private static final long serialVersionUID = 4498052334687883485L;
    
    @JSONField(name="ID")
    private String id;
    
    @JSONField(name="USERNAME")
    private String userName;
    
    @JSONField(name="AGE")
    private int age;
    
}
