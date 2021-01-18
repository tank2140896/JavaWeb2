package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.db.mybatis.api.DaoWapper;
import com.javaweb.web.eo.interfaces.ExcludeInfoResponse;
import com.javaweb.web.po.DataPermission;

@Mapper
public interface DataPermissionDao extends DaoWapper<DataPermission> {
	
	public List<ExcludeInfoResponse> selectExcludeInfo(List<String> list);
	
	public List<DataPermission> selectAllByInterfacesId(String interfacesId);
	
}
