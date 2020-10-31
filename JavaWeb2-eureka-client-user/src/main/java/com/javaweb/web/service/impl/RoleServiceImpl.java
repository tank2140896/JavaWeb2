package com.javaweb.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.base.BaseService;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.role.ModuleInfoResponse;
import com.javaweb.web.eo.role.RoleListRequest;
import com.javaweb.web.eo.role.RoleListResponse;
import com.javaweb.web.po.Role;
import com.javaweb.web.po.RoleModule;
import com.javaweb.web.service.RoleService;

@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseService implements RoleService {
	
	public Page roleList(RoleListRequest roleListRequest){
		List<RoleListResponse> list = roleDao.roleList(roleListRequest);
		long count = roleDao.roleListCount(roleListRequest);
		Page page = new Page(roleListRequest,list,count);
		return page;
	}

	@Transactional
	public void roleDelete(String roleId) {
		String roleIds[] = roleId.split(",");
		for(String id:roleIds){
			roleDao.roleDelete(id);
		}
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
		return roleDao.roleDetail(roleId);
	}

	public List<ModuleInfoResponse> roleModuleInfo(String roleId) {
		List<ModuleInfoResponse> list = roleDao.roleModuleInfo(roleId);
		list = setTreeList(list,null);
		return list;
	}
	
	//封装成树形结构集合
	private List<ModuleInfoResponse> setTreeList(List<ModuleInfoResponse> originList,ModuleInfoResponse moduleInfoResponse){
		List<ModuleInfoResponse> moduleList = new ArrayList<>();
		for (int i = 0; i < originList.size(); i++) {
			ModuleInfoResponse currentModule = originList.get(i);
			if((moduleInfoResponse!=null&&moduleInfoResponse.getModuleId().equals(currentModule.getParentId()))||(moduleInfoResponse==null&&currentModule.getParentId()==null)){
				currentModule.setList(setTreeList(originList,currentModule));
				moduleList.add(currentModule);
			}
		}
		return moduleList;
	}

	@Transactional
	public void roleModuleAssignment(String roleId,List<String> list) {
		Map<String,Object> map = new HashMap<>();
		List<RoleModule> roleModuleList = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			RoleModule roleModule = new RoleModule();
			roleModule.setId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
			roleModule.setRoleId(roleId);
			roleModule.setModuleId(list.get(i));
			roleModuleList.add(roleModule);
		}
		map.put("roleId",roleId);
		map.put("list",roleModuleList);
		roleDao.roleModuleAssignment(map);
	}

}
