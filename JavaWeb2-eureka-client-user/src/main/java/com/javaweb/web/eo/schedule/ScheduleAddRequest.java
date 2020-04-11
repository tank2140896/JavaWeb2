package com.javaweb.web.eo.schedule;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleAddRequest  implements Serializable {

	private static final long serialVersionUID = 9133420258863916421L;
	
	private List<ScheduleListResponse> list;
	
	private String year;
	
	private String month;

}
