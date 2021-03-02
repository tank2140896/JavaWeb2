package com.javaweb.web.po;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;
import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="sys_module")
public class Module extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 8495542220648641440L;

	private List<Module> list;/**辅助属性*/
	
	private String parentName;/**辅助属性（模块的上级模块名称）*/
	
	@NotEmpty(groups={update.class},message="validated.module.moduleId.notEmpty")
	@Column(name="module_id",pk=true,idAutoCreate=true,columnDesc="主键ID")
	private String moduleId;//模块ID
	
	@NotEmpty(groups={add.class,update.class},message="validated.module.moduleName.notEmpty")
	@Size(groups={add.class,update.class},max=20,message="validated.module.moduleName.maxLength.limit")
	@Column(name="module_name",columnDesc="模块名称")
	private String moduleName;//模块名称
	
	@Column(name="page_url",columnDesc="页面URL")
	private String pageUrl;//页面URL
	
	@Column(name="api_url",columnDesc="API的URL")
	private String apiUrl;//API的URL
	
	@Column(name="parent_id",columnDesc="模块的上级ID")
	private String parentId;//模块的上级ID
	
	@Column(name="fcode",columnDesc="层级关系")
	private String fcode;//层级关系
	
	@Column(name="level",columnDesc="第几级(0表示未定义层级数;层级数1为最高,即根节点)")
	private Integer level = 0;//第几级(0表示未定义层级数;层级数1为最高,即根节点)
	
	@Column(name="orders",columnDesc="模块顺序(0表示没有顺序;顺序从1开始)")
	private Integer orders = 0;//模块顺序(0表示没有顺序;顺序从1开始)
	
	@Column(name="module_type",columnDesc="模块类型(0:未定义模块类型;1:目录;2:菜单；3:功能)")
	private Integer moduleType = 0;//模块类型(0:未定义模块类型;1:目录;2:菜单；3:功能)
	
	@NotEmpty(groups={add.class,update.class},message="validated.module.alias.notEmpty")
	@Size(groups={add.class,update.class},max=20,message="validated.module.alias.maxLength.limit")
	@Column(name="alias",columnDesc="别名")
	private String alias;//别名
	
	@Column(name="parent_alias",columnDesc="上级别名")
	private String parentAlias;//上级别名
	
	@Column(name="system_id",columnDesc="系统ID")
	private String systemId;//系统ID
	
	@Column(name="remark",columnDesc="备注")
	private String remark;//备注
	
	@Column(name="icon",columnDesc="图标")
	private String icon;//图标
	
	@Column(name="type",columnDesc="类型(0:未定义类型,作为全端通用接口使用;1:PC端;2:安卓端;3:IOS端) ")
	private Integer type = 0;//类型(0:未定义类型,作为全端通用接口使用;1:PC端;2:安卓端;3:IOS端) 

	public static final String moduleIdColumn = "module_id";
	public static final String moduleNameColumn = "module_name";
	public static final String pageUrlColumn = "page_url";
	public static final String apiUrlColumn = "api_url";
	public static final String parentIdColumn = "parent_id";
	public static final String fcodeColumn = "fcode";
	public static final String levelColumn = "level";
	public static final String ordersColumn = "orders";
	public static final String moduleTypeColumn = "module_type";
	public static final String aliasColumn = "alias";
	public static final String parentAliasColumn = "parent_alias";
	public static final String systemIdColumn = "system_id";
	public static final String remarkColumn = "remark";
	public static final String iconColumn = "icon";
	public static final String typeColumn = "type";
	
}
