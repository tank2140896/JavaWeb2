package com.javaweb.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.annotation.token.TokenDataAnnotation;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.SwaggerConstant;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.schedule.ScheduleAddRequest;
import com.javaweb.web.eo.schedule.ScheduleListRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_SCHEDULE_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_SCHEDULE_PREFIX)
public class ScheduleController extends BaseController {
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_SCHEDULE_LIST)
	@PostMapping(value=ApiConstant.SCHEDULE_LIST)
	public BaseResponseResult scheduleList(@RequestBody ScheduleListRequest scheduleListRequest) {
		return new BaseResponseResult(200,"schedule.list.success",scheduleService.getScheduleByDate(scheduleListRequest));
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_SCHEDULE_ADD)
	@PostMapping(value=ApiConstant.SCHEDULE_ADD)
	public BaseResponseResult scheduleAdd(@RequestBody ScheduleAddRequest scheduleAddRequest,@TokenDataAnnotation TokenData tokenData) {
		scheduleService.scheduleSave(scheduleAddRequest,tokenData.getUser());
		return new BaseResponseResult(200,"schedule.add.success",null);
	}
	
}
