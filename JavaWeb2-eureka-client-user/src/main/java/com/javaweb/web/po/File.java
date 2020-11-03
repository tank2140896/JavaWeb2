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
	
	@Column(name="user_id",columnDesc="用户ID")
	private String userId;//用户ID
	
	@Column(name="system_id",columnDesc="系统ID")
	private String systemId;//系统ID
	
	@Column(name="file_name",columnDesc="文件名称")
	private String fileName;//文件名称

	@Column(name="file_path",columnDesc="文件路径")
	private String filePath;//文件路径
	
	@Column(name="file_full_path",columnDesc="文件全路径")
	private String fileFullPath;//文件全路径
	
	@Column(name="file_size",columnDesc="文件大小")
	private String fileSize;//文件大小
	
	@Column(name="file_unit",columnDesc="文件大小单位")
	private String fileUnit;//文件大小单位
	
	@Column(name="file_ser_no",columnDesc="文件批次号")
	private String fileSerNo;//文件批次号
	
	@Column(name="remark",columnDesc="备注")
	private String remark;//备注
	
	@Column(name="status",columnDesc="状态(0:正常；1：禁用)")
	private Integer status = 0;//状态(0:正常；1：禁用)

}
