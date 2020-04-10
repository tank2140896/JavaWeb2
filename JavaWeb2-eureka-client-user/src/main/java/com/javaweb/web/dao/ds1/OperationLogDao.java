package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.mybatis.api.DaoForMySql;
import com.javaweb.web.eo.operationLog.OperationLogListRequest;
import com.javaweb.web.eo.operationLog.OperationLogListResponse;
import com.javaweb.web.po.OperationLog;

@Mapper
public interface OperationLogDao extends DaoForMySql<OperationLog> {
	
	public List<OperationLogListResponse> operationLogList(OperationLogListRequest operationLogListRequest);
	
	public Long operationLogListCount(OperationLogListRequest operationLogListRequest);
	
}