package com.javaweb.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.annotation.token.TokenDataAnnotation;
import com.javaweb.annotation.url.ControllerMethod;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.schedule.ScheduleAddRequest;
import com.javaweb.web.eo.schedule.ScheduleListRequest;

//登录且需要权限才可访问的日程管理模块
@RestController
@RequestMapping(ApiConstant.WEB_SCHEDULE_PREFIX)
public class ScheduleController extends BaseController {
	
	@PostMapping(value=ApiConstant.SCHEDULE_LIST)
	@ControllerMethod(interfaceName="日程列表接口")
	public BaseResponseResult scheduleList(@RequestBody ScheduleListRequest scheduleListRequest) {
		return new BaseResponseResult(200,"schedule.list.success",scheduleService.getScheduleByDate(scheduleListRequest));
	}
	
	@PostMapping(value=ApiConstant.SCHEDULE_ADD)
	@ControllerMethod(interfaceName="保存日程接口")
	public BaseResponseResult scheduleAdd(@RequestBody ScheduleAddRequest scheduleAddRequest,@TokenDataAnnotation TokenData tokenData) {
		scheduleService.scheduleSave(scheduleAddRequest,tokenData.getUser());
		return new BaseResponseResult(200,"schedule.add.success",null);
	}
	
}
