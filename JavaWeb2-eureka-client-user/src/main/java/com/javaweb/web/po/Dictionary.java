package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;
import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="sys_dictionary")
public class Dictionary extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6987225808532290813L;
    
    @Column(name="id",pk=true,columnDesc="主键ID")
    private String id;//主键ID
    
    @Column(name="parent_id",columnDesc="父ID")
    private String parentId;//父ID
    
	@Column(name="fcode",columnDesc="层级关系")
	private String fcode;//层级关系
	
	@Column(name="level",columnDesc="第几级(0表示未定义层级数;层级数1为最高,即根节点)")
	private Integer level = 0;//第几级(0表示未定义层级数;层级数1为最高,即根节点)
    
    @Column(name="data_type",columnDesc="数据类型")
    private String dataType;//数据类型
    
    @Column(name="key_code",columnDesc="key值")
    private String keyCode;//key值
    
    @Column(name="value_code",columnDesc="value值")
    private String valueCode;//value值
    
    @Column(name="category",columnDesc="分类")
    private String category;//分类
    
    @Column(name="sort",columnDesc="序号")
    private String sort;//序号
    
    @Column(name="means",columnDesc="含义")
    private String means;//含义
    
    @Column(name="universally",columnDesc="是否通用(0:通用;1:不通用)")
    private Integer universally;//是否通用(0:通用;1:不通用)
    
    @Column(name="system_id",columnDesc="系统ID")
	private String systemId;//系统ID
    
    @Column(name="remark",columnDesc="备注说明")
    private String remark;//备注说明
    
    public static final String idColumn = "id";
    public static final String parentIdColumn = "parent_id";
    public static final String fcodeColumn = "fcode";
    public static final String levelColumn = "level";
    public static final String dataTypeColumn = "data_type";
    public static final String keyCodeColumn = "key_code";
    public static final String valueCodeColumn = "value_code";
    public static final String categoryColumn = "category";
    public static final String sortColumn = "sort";
    public static final String meansColumn = "means";
    public static final String universallyColumn = "universally";
    public static final String systemIdColumn = "system_id";
    public static final String remarkColumn = "remark";

}
