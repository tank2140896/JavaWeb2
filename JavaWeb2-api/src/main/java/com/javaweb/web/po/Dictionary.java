package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;
import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="dictionary")
public class Dictionary extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6987225808532290813L;
    
    @Column(name="id",pk=true)
    private String id;//字典ID
    
    @Column(name="parent_id")
    private String parentId;//父ID
    
    @Column(name="data_type")
    private String dataType;//数据类型
    
    @Column(name="key_code")
    private String keyCode;//key值
    
    @Column(name="value_code")
    private String valueCode;//value值
    
    @Column(name="category")
    private String category;//分类
    
    @Column(name="sort")
    private String sort;//序号
    
    @Column(name="means")
    private String means;//含义
    
    @Column(name="remark")
    private String remark;//备注说明

}
