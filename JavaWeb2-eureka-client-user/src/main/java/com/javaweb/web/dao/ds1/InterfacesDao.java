package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.mybatis.api.DaoForMySql;
import com.javaweb.web.eo.interfaces.InterfacesListRequest;
import com.javaweb.web.po.Interfaces;

@Mapper
public interface InterfacesDao extends DaoForMySql<Interfaces>{
	
	public void interfacesBatchInsert(List<Interfaces> list);
	
	public void interfacesBatchDelete(List<String> list);
	
	public List<Interfaces> interfacesList(InterfacesListRequest interfacesListRequest);
	
	public Long interfacesListCount(InterfacesListRequest interfacesListRequest);

}
