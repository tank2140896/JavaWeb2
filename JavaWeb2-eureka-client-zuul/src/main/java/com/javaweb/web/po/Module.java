package com.javaweb.web.po;

import java.util.List;

import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Module extends BaseEntity {
	
    private List<Module> list;/**辅助属性*/
	
	private String moduleId;//模块ID
	
	private String moduleName;//模块名称
	
	private String pageUrl;//页面URL
	
	private String apiUrl;//API的URL
	
	private String parentId;//模块的上级ID
	
	private String parentName;//模块的上级模块名称(辅助属性)
	
	private String fcode;//层级关系
	
	private Integer level = 0;//第几级(0表示未定义层级数;层级数1为最高,即根节点)
	
	private Integer orders = 0;//模块顺序(0表示没有顺序;顺序从1开始)
	
	private Integer moduleType = 0;//模块类型(0:未定义模块类型;1:目录;2:菜单；3:功能)
	
	private String alias;//别名
	
	private String parentAlias;//上级别名
	
	private String remark;//备注
	
	private String icon;//图标
	
	private Integer type = 0;//类型(0:未定义类型,作为全端通用接口使用;1:PC端;2:安卓端;3:IOS端) 

}