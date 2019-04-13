package com.javaweb.web.po;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.javaweb.base.BaseEntity;
import com.javaweb.interceptor.mybatis.Column;
import com.javaweb.interceptor.mybatis.Table;

@Table(name="module")
public class Module extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 8495542220648641440L;

	private List<Module> list;/**辅助属性*/
	
	@NotNull(groups={update.class,delete.class},message="validated.module.moduleId.notNull")
	@Column(name="module_id",pk=true)
	private String moduleId;//模块ID
	
	@NotNull(groups={add.class,update.class},message="validated.module.moduleName.notNull")
	@Column(name="module_name")
	private String moduleName;//模块名称
	
	@Column(name="page_url")
	private String pageUrl;//页面URL
	
	@Column(name="api_url")
	private String apiUrl;//API的URL
	
	@Column(name="parent_id")
	private String parentId;//模块的上级ID
	
	@Column(name="fcode")
	private String fcode;//层级关系
	
	@Column(name="level")
	private Integer level = 0;//第几级(0表示未定义层级数;层级数1为最高,即根节点)
	
	@Column(name="orders")
	private Integer orders = 0;//模块顺序(0表示没有顺序;顺序从1开始)
	
	@Column(name="module_type")
	private Integer moduleType = 0;//模块类型(0:未定义模块类型;1:菜单;2:操作)
	
	@Column(name="alias")
	private String alias;//别名
	
	@Column(name="parent_alias")
	private String parentAlias;//上级别名
	
	@Column(name="remark")
	private String remark;//备注
	
	@Column(name="icon")
	private String icon;//图标
	
	@Column(name="type")
	private Integer type = 0;//类型(0:未定义类型,作为全端通用接口使用;1:PC端;2:安卓端;3:IOS端) 

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getFcode() {
		return fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public Integer getModuleType() {
		return moduleType;
	}

	public void setModuleType(Integer moduleType) {
		this.moduleType = moduleType;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getParentAlias() {
		return parentAlias;
	}

	public void setParentAlias(String parentAlias) {
		this.parentAlias = parentAlias;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Module> getList() {
		return list;
	}

	public void setList(List<Module> list) {
		this.list = list;
	}

}