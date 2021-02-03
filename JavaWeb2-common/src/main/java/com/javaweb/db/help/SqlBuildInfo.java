package com.javaweb.db.help;

import java.util.List;

import com.javaweb.enums.DbTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SqlBuildInfo {
	
	private String tableName;//表名
	
	private List<String> entityList;//实体类属性名称
	
	private List<Object> entityValueList;//实体类属性值
	
	private Object parameterValue;//获取参数值
	
	private List<String> columnList;//实体类对应表字段名称
	
	private String pk;//数据库主键名称
	
	private Boolean pkGenerate;//主键是否自增
	
	private String id;//自动生成ID名称

	private Boolean idAutoCreate;//是否自动生成

	private List<Boolean> canUpdateSetEmptyList;//更新时是否能设置NULL或者空
	
	private DbTypeEnum dbTypeEnum;//数据库类型
	
}
