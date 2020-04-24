package com.javaweb.web.service;

import java.util.List;

import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.interfaces.ExcludeInfoResponse;
import com.javaweb.web.eo.interfaces.InterfacesListRequest;
import com.javaweb.web.eo.interfaces.UserRolePermissionResponse;
import com.javaweb.web.po.Interfaces;

public interface InterfacesService {
	
	public void synchronizedInterfaces();
	
	public Page interfacesList(InterfacesListRequest interfacesListRequest);
	
	public Interfaces interfacesDetail(String interfacesId);
	
	public void interfacesModify(Interfaces interfaces);
	
	public UserRolePermissionResponse userRoleDataPermission(String interfacesId);
	
	public void dataPermissionAssignment(UserRolePermissionResponse userRolePermissionResponse,String interfacesId);
	
	public List<ExcludeInfoResponse> getExcludeInfoResponseList(String userId);

}
