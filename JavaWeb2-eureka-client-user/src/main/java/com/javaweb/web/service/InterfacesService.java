package com.javaweb.web.service;

import java.util.List;

import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.interfaces.ExcludeInfoResponse;
import com.javaweb.web.eo.interfaces.InterfacesListRequest;
import com.javaweb.web.eo.interfaces.InterfacesTestRequest;
import com.javaweb.web.eo.interfaces.UserRoleDataPermissionRequest;
import com.javaweb.web.eo.interfaces.UserRolePermissionRequest;
import com.javaweb.web.po.Interfaces;
import com.javaweb.web.po.User;

public interface InterfacesService {
	
	public void synchronizedInterfaces();
	
	public Page interfacesList(InterfacesListRequest interfacesListRequest);
	
	public Interfaces interfacesDetail(String interfacesId);
	
	public void interfacesModify(Interfaces interfaces);
	
	public Page userRoleDataPermission(UserRoleDataPermissionRequest userRoleDataPermissionRequest);
	
	public void dataPermissionAssignment(UserRolePermissionRequest userRolePermissionResponse,String interfacesId,User user);
	
	public List<ExcludeInfoResponse> getExcludeInfoResponseList(String userId);
	
	public void synchronizedRedisInterfaceHistoryTimes();
	
	public List<Interfaces> getAll();
	
	public String interfacesTest(InterfacesTestRequest interfacesTestRequest);

}
