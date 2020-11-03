package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;
import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="sys_schedule")
public class Schedule extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -3844395392415923202L;

	@Column(name="id",pk=true,columnDesc="主键ID")
	private String id;//主键ID
	
	@Column(name="schedule_date",columnDesc="日期")
	private String scheduleDate;//日期
	
	@Column(name="schedule_type",columnDesc="日程类型（1:周末;2:正常;3:节假日;4:休假）")
	private Integer scheduleType;//日程类型（1:周末;2:正常;3:节假日;4:休假）
	
	@Column(name="remark",columnDesc="备注")
	private String remark;//备注
	
}
