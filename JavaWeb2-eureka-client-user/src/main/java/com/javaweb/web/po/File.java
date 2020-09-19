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

	@Column(name="file_id",pk=true)
	private String fileId;//文件ID
	
	@Column(name="user_id")
	private String userId;//用户ID
	
	@Column(name="micro_sys_no")
	private String microSysNo;//微服务系统编号
	
	@Column(name="file_name")
	private String fileName;//文件名称

	@Column(name="file_path")
	private String filePath;//文件路径
	
	@Column(name="file_full_path")
	private String fileFullPath;//文件全路径
	
	@Column(name="file_size")
	private String fileSize;//文件大小
	
	@Column(name="file_unit")
	private String fileUnit;//文件大小单位
	
	@Column(name="file_ser_no")
	private String fileSerNo;//文件批次号
	
	@Column(name="remark")
	private String remark;//备注
	
	@Column(name="status")
	private Integer status = 0;//账号状态(0:正常；1：禁用)

}
