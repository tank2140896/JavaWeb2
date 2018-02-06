package com.javaweb.web.service;

import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.role.RoleListRequest;
import com.javaweb.web.po.Role;

public interface RoleService {
	
	public Page roleList(RoleListRequest roleListRequest);
	
	public void roleDelete(String roleId);

	public void roleAdd(Role role);

	public void roleModify(Role role);
	
	public Role roleDetail(String roleId);
	
}
