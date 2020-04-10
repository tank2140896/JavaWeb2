package com.javaweb.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.base.BaseService;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.operationLog.OperationLogListRequest;
import com.javaweb.web.eo.operationLog.OperationLogListResponse;
import com.javaweb.web.po.OperationLog;
import com.javaweb.web.service.OperationLogService;

@Service("operationLogServiceImpl")
public class OperationLogServiceImpl extends BaseService implements OperationLogService {

	@Transactional
	public void saveOperationLog(OperationLog operationLog) {
		operationLogDao.insertForMySql(operationLog);
	}

	public Page operationLogList(OperationLogListRequest operationLogListRequest) {
		List<OperationLogListResponse> list = operationLogDao.operationLogList(operationLogListRequest);
		long count = operationLogDao.operationLogListCount(operationLogListRequest);
		Page page = new Page(operationLogListRequest,list,count);
		return page;
	}

}
