package com.javaweb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.SwaggerConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.operationLog.OperationLogListRequest;
import com.javaweb.web.service.OperationLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_OPERATIONLOG_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_OPERATIONLOG_PREFIX)
public class OperationLogController extends BaseController {

    @Autowired
    private OperationLogService operationLogService;

    @ApiOperation(value=SwaggerConstant.SWAGGER_OPERATIONLOG_LIST)
    @PostMapping(ApiConstant.OPERATIONLOG_LIST)
    public BaseResponseResult operationLogList(@RequestBody OperationLogListRequest operationLogListRequest) {
        Page page = operationLogService.operationLogList(operationLogListRequest);
        return getBaseResponseResult(HttpCodeEnum.SUCCESS,"operationlog.list.success",page);
    }
}