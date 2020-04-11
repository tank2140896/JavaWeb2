package com.javaweb.web.eo.schedule;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleListRequest implements Serializable {
	
	private static final long serialVersionUID = -8098120895877733962L;

	private String year;
	
	private String month;

}
