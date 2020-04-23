package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;
import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="data_permission")
public class DataPermission extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -4584788854424411139L;
	
    @Column(name="id",pk=true)
    private String id;//ID
    
    @Column(name="exclude_field")
    private String excludeField;//排除字段
    
    @Column(name="interfaces_id")
    private String interfacesId;//接口ID
    
    @Column(name="remark")
    private String remark;//备注说明

}
