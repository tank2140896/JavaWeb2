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
    
	@Column(name="fcode")
	private String fcode;//层级关系
	
	@Column(name="level")
	private Integer level = 0;//第几级(0表示未定义层级数;层级数1为最高,即根节点)
    
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
    
    @Column(name="universally")
    private Integer universally;//是否通用(0:通用;1:不通用)
    
    @Column(name="system_id")
	private String systemId;//系统ID
    
    @Column(name="remark")
    private String remark;//备注说明

}
