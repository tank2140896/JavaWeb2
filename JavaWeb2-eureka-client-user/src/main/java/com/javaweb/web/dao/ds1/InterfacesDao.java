package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.db.mybatis.api.DaoWapper;
import com.javaweb.web.eo.interfaces.InterfacesListRequest;
import com.javaweb.web.eo.interfaces.RolePermissionResponse;
import com.javaweb.web.eo.interfaces.UserPermissionResponse;
import com.javaweb.web.eo.interfaces.UserRoleDataPermissionRequest;
import com.javaweb.web.po.Interfaces;

@Mapper
public interface InterfacesDao extends DaoWapper<Interfaces>{
	
	public void interfacesBatchInsert(List<Interfaces> list);
	
	public void interfacesBatchDelete(List<String> list);
	
	public List<Interfaces> interfacesList(InterfacesListRequest interfacesListRequest);
	
	public Long interfacesListCount(InterfacesListRequest interfacesListRequest);
	
	public List<UserPermissionResponse> userPermissionList(UserRoleDataPermissionRequest userRoleDataPermissionRequest);
	
	public Long userPermissionListCount(UserRoleDataPermissionRequest userRoleDataPermissionRequest);
	
	public List<RolePermissionResponse> rolePermissionList(UserRoleDataPermissionRequest userRoleDataPermissionRequest);
	
	public Long rolePermissionListCount(UserRoleDataPermissionRequest userRoleDataPermissionRequest);
	
	public void clearUserRoleDataPermission(); 
	
	public void deleteUserDataPermission(List<String> userIds);

	public void deleteRoleDataPermission(List<String> roleIds);

}
