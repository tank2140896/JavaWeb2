package com.javaweb.base;

import com.javaweb.interceptor.mybatis.Column;

public class BaseEntity extends BaseValidatedGroup {
	
	@Column(name="create_date")
	private String createDate;//创建时间
	
	@Column(name="creator")
	private String creator;//创建者
	
	@Column(name="update_date")
	private String updateDate;//更新时间
	
	@Column(name="updater")
	private String updater;//更新者
	
	@Column(name="del_flag")
	private Integer delFlag = 0;//删除标记(0:未被删除;1:已被删除)

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}
