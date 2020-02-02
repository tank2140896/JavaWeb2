package com.javaweb.config.neo4j;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RelationshipEntity(type="relation")
//人物关系节点实体类
public class PersonRelationNodeEntity {
    
    @Id
    private String id;

    private String relationName;
     
    //关系的一端节点
    @StartNode
    private PersonNodeEntity pne1;
   
    //关系的另一端节点
    @EndNode
    private PersonNodeEntity pne2;
   
}
