package com.javaweb.web.dao.ds1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.base.MySqlBaseDao;
import com.javaweb.web.eo.role.RoleListRequest;
import com.javaweb.web.eo.role.RoleListResponse;
import com.javaweb.web.po.Role;

@Mapper
public interface RoleDao extends MySqlBaseDao<Role> {
	
	public List<RoleListResponse> roleList(RoleListRequest roleListRequest);
	
	public Long roleListCount(RoleListRequest roleListRequest);
	
	public void roleDelete(String roleId);
	
	public Role roleDetail(String roleId);
	
}