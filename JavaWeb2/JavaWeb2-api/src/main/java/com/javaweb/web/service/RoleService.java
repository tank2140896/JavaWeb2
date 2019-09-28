package com.javaweb.web.service;

import java.util.List;

import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.role.ModuleInfoResponse;
import com.javaweb.web.eo.role.RoleListRequest;
import com.javaweb.web.po.Role;

public interface RoleService {
	
	public Page roleList(RoleListRequest roleListRequest);
	
	public void roleDelete(String roleId);

	public void roleAdd(Role role);

	public void roleModify(Role role);
	
	public Role roleDetail(String roleId);
	
	public List<ModuleInfoResponse> roleModuleInfo(String roleId);
	
	public void roleModuleAssignment(String roleId,List<String> list);
	
}
