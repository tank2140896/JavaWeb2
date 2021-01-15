package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;
import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="${tableName}")
public class ${className} extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
<#list propertyList as pl>    
    @Column(name="${pl.tableColumn}"<#if pl.key == true>,pk=true</#if>)
    private ${pl.javaType} ${pl.javaPropertyName};
    
</#list>
<#list propertyList as pl>
	public static final String ${pl.javaPropertyName}Column = "${pl.tableColumn}";
	
</#list>
}
