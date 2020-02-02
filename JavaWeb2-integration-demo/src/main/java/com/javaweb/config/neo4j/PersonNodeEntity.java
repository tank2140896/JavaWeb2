package com.javaweb.config.neo4j;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NodeEntity
//人物节点实体类
public class PersonNodeEntity {
    
    @Id
    private String id;//ID
    
    private String name;//姓名
    
    private String imgSrc;//图片链接

}
