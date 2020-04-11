package com.javaweb.web.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;
import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="schedule")
public class Schedule extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -3844395392415923202L;

	@Column(name="id",pk=true)
	private String id;//主键ID
	
	@Column(name="schedule_date")
	private String scheduleDate;//日期
	
	@Column(name="schedule_type")
	private Integer scheduleType;//日程类型（1:周末;2:正常;3:节假日;4:休假）
	
	@Column(name="remark")
	private String remark;//备注
	
}
