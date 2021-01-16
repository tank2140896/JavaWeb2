package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;
import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="sys_file")
public class File extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 5918807982525592278L;

	@Column(name="id",pk=true,columnDesc="主键ID")
	private String id;//主键ID
	
	@Column(name="system_id",columnDesc="系统ID")
	private String systemId;//系统ID
	
	@Column(name="file_unique_index",columnDesc="文件唯一索引")
	private String fileUniqueIndex;//文件唯一索引
	
	@Column(name="origin_file_name",columnDesc="原始文件名称")
	private String originFileName;//原始文件名称
	
	@Column(name="current_file_name",columnDesc="现文件名称")
	private String currentFileName;//现文件名称

	@Column(name="file_path",columnDesc="文件路径")
	private String filePath;//文件路径
	
	@Column(name="file_full_path",columnDesc="文件全路径")
	private String fileFullPath;//文件全路径
	
	@Column(name="file_suffix",columnDesc="文件后缀")
	private String fileSuffix;//文件后缀
	
	@Column(name="file_size",columnDesc="文件大小")
	private Long fileSize;//文件大小
	
	@Column(name="file_unit",columnDesc="文件大小单位")
	private String fileUnit;//文件大小单位
	
	@Column(name="file_ser_no",columnDesc="文件批次号")
	private String fileSerNo;//文件批次号
	
	@Column(name="remark",columnDesc="备注")
	private String remark;//备注
	
	@Column(name="status",columnDesc="状态(0:正常；1：禁用)")
	private Integer status = 0;//状态(0:正常；1：禁用)
	
	@Column(name="file_use_type",columnDesc="文件使用类型（1：临时文件；2：正式文件）") 
	private Integer fileUseType;//文件使用类型（1：临时文件；2：正式文件）
	
	public static final String idColumn = "id";
	public static final String systemIdColumn = "system_id";
	public static final String fileUniqueIndexColumn = "file_unique_index";
	public static final String originFileNameColumn = "origin_file_name";
	public static final String currentFileNameColumn = "current_file_name";
	public static final String filePathColumn = "file_path";
	public static final String fileFullPathColumn = "file_full_path";
	public static final String fileSuffixColumn = "file_suffix";
	public static final String fileSizeColumn = "file_size";
	public static final String fileUnitColumn = "file_unit";
	public static final String fileSerNoColumn = "file_ser_no";
	public static final String remarkColumn = "remark";
	public static final String statusColumn = "status";
	public static final String fileUseTypeColumn = "file_use_type";

}
