package com.javaweb.base;

import com.javaweb.annotation.sql.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity extends BaseValidatedGroup {
	
	/**
	import java.util.Date;
	import com.fasterxml.jackson.annotation.JsonFormat;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name="create_date")
	private Date createDate;
	*/
	@Column(name="create_date")
	private String createDate;//创建时间
	
	@Column(name="creator")
	private String creator;//创建者
	
	/**
	import java.util.Date;
	import com.fasterxml.jackson.annotation.JsonFormat;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name="update_date")
	private Date updateDate;
	*/
	@Column(name="update_date")
	private String updateDate;//更新时间
	
	@Column(name="updater")
	private String updater;//更新者
	
	@Column(name="del_flag")
	private Integer delFlag = 0;//删除标记(0:未被删除;1:已被删除)

}
