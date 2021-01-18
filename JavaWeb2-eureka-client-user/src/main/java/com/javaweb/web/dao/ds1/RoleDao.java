package com.javaweb.web.dao.ds1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.javaweb.db.mybatis.api.DaoWapper;
import com.javaweb.web.eo.role.ModuleInfoResponse;
import com.javaweb.web.eo.role.RoleListRequest;
import com.javaweb.web.eo.role.RoleListResponse;
import com.javaweb.web.po.Role;

@Mapper
public interface RoleDao extends DaoWapper<Role> {
	
	public List<RoleListResponse> roleList(RoleListRequest roleListRequest);
	
	public Long roleListCount(RoleListRequest roleListRequest);
	
	public void roleDelete(String roleId);
	
	public Role roleDetail(String roleId);
	
	public List<ModuleInfoResponse> roleModuleInfo(String roleId);
	
	public void roleModuleAssignment(Map<String,Object> map);
	
}