package com.javaweb.web.service;

import java.util.List;

import com.javaweb.web.eo.schedule.ScheduleAddRequest;
import com.javaweb.web.eo.schedule.ScheduleListRequest;
import com.javaweb.web.eo.schedule.ScheduleListResponse;
import com.javaweb.web.po.User;

public interface ScheduleService {
	
	public List<ScheduleListResponse> getScheduleByDate(ScheduleListRequest scheduleListRequest);
	
	public void scheduleSave(ScheduleAddRequest scheduleAddRequest,User user);
	
}
