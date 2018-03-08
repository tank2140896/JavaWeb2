package com.javaweb.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.base.BaseService;
import com.javaweb.util.core.PageUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.role.ModuleInfoResponse;
import com.javaweb.web.eo.role.RoleListRequest;
import com.javaweb.web.eo.role.RoleListResponse;
import com.javaweb.web.po.Role;
import com.javaweb.web.service.RoleService;

@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseService implements RoleService {
	
	public Page roleList(RoleListRequest roleListRequest){
		Page page = new Page();
		List<RoleListResponse> list = roleDao.roleList(roleListRequest);
		long count = roleDao.roleListCount(roleListRequest);
		page.setData(list);
		page.setCurrentPage(roleListRequest.getCurrentPage());
		page.setPageSize(roleListRequest.getPageSize());
		page.setTotalSize(count);
		page.setTotalPage(PageUtil.getTotalPage(page.getTotalSize(),page.getPageSize()));
		page.setPageList(PageUtil.getShowPages(roleListRequest.getCurrentPage(),page.getTotalPage(),5L));
		return page;
	}

	@Transactional
	public void roleDelete(String roleId) {
		roleDao.roleDelete(roleId);
	}

	@Transactional
	public void roleAdd(Role role) {
		roleDao.insert(role);
	}

	@Transactional
	public void roleModify(Role role) {
		roleDao.update(role);
	}

	public Role roleDetail(String roleId) {
		return roleDao.selectByPk(roleId);
	}

	public Map<String,Object> roleModuleInfo(String roleId) {
		Map<String,Object> map = new HashMap<>();
		Role role = roleDao.selectByPk(roleId);
		List<ModuleInfoResponse> list = roleDao.roleModuleInfo(roleId);
		list = setTreeList(list,null);
		map.put("role",role);
		map.put("list",list);
		return map;
	}
	
	//封装成树形结构集合
	private List<ModuleInfoResponse> setTreeList(List<ModuleInfoResponse> originList,ModuleInfoResponse moduleInfoResponse){
		List<ModuleInfoResponse> moduleList = new ArrayList<>();
		for (int i = 0; i < originList.size(); i++) {
			ModuleInfoResponse currentModule = originList.get(i);
			if((moduleInfoResponse!=null&&moduleInfoResponse.getModuleId().equals(currentModule.getParentId()))||(moduleInfoResponse==null&&currentModule.getParentId()==null)){
				currentModule.setList(setTreeList(originList, currentModule));
				moduleList.add(currentModule);
			}
		}
		return moduleList;
	}

	@Transactional
	public void moduleAssignment(Map<String, Object> map) {
		roleDao.moduleAssignment(map);
	}

}
