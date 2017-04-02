package com.javaweb.base;

import java.util.Date;

import com.javaweb.constant.SystemConstant;

public class BaseEntity {
	
	private Date createDate = new Date();//创建时间
	
	private String creator = SystemConstant.SYSTEM_DEFAULT_CREATOR;//创建者
	
	private Date updateDate = new Date();//更新时间
	
	private String updater = SystemConstant.SYSTEM_DEFAULT_UPDATER;//更新者
	
	private int delFlag = 0;//删除标记(0:未被删除;1:已被删除)

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	
}
