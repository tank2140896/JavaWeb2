package com.javaweb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.annotation.url.ControllerMethod;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.operationLog.OperationLogListRequest;
import com.javaweb.web.service.OperationLogService;

//登录且需要权限才可访问的操作日志管理模块
@RestController
@RequestMapping(ApiConstant.WEB_OPERATIONLOG_PREFIX)
public class OperationLogController extends BaseController {

    @Autowired
    private OperationLogService operationLogService;

    @PostMapping(ApiConstant.OPERATIONLOG_LIST)
    @ControllerMethod(interfaceName="查询操作日志接口")
    public BaseResponseResult operationLogList(@RequestBody OperationLogListRequest operationLogListRequest) {
        Page page = operationLogService.operationLogList(operationLogListRequest);
        return getBaseResponseResult(HttpCodeEnum.SUCCESS,"operationlog.list.success",page);
    }
    
}