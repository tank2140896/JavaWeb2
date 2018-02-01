package com.javaweb.web.service;

import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.role.RoleListRequest;

public interface RoleService {
	
	public Page roleList(RoleListRequest roleListRequest);
	
}
