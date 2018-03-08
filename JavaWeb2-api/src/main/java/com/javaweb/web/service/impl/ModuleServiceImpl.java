package com.javaweb.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.base.BaseService;
import com.javaweb.util.core.PageUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.module.ModuleLevelAndOrdersResponse;
import com.javaweb.web.eo.module.ModuleListRequest;
import com.javaweb.web.eo.module.ModuleListResponse;
import com.javaweb.web.po.Module;
import com.javaweb.web.service.ModuleService;

@Service("moduleServiceImpl")
public class ModuleServiceImpl extends BaseService implements ModuleService {
	
	public List<Module> getUserRoleModule(Map<String, Object> map) {
		return moduleDao.getUserRoleModule(map);
	}

	public Page moduleList(ModuleListRequest moduleListRequest) {
		Page page = new Page();
		List<ModuleListResponse> list = moduleDao.moduleList(moduleListRequest);
		long count = moduleDao.moduleListCount(moduleListRequest);
		page.setData(list);
		page.setCurrentPage(moduleListRequest.getCurrentPage());
		page.setPageSize(moduleListRequest.getPageSize());
		page.setTotalSize(count);
		page.setTotalPage(PageUtil.getTotalPage(page.getTotalSize(),page.getPageSize()));
		page.setPageList(PageUtil.getShowPages(moduleListRequest.getCurrentPage(),page.getTotalPage(),5L));
		return page;
	}

	@Transactional
	public void moduleDelete(String moduleId) {
		moduleDao.moduleDelete(moduleId);
	}

	@Transactional
	public void moduleAdd(Module module) {
		ModuleLevelAndOrdersResponse moduleLevelAndOrdersResponse = moduleDao.getModuleLevelAndOrdersByParentId(module.getParentId());
		if(moduleLevelAndOrdersResponse!=null){
			Integer level = moduleLevelAndOrdersResponse.getLevel();
			Integer orders = moduleLevelAndOrdersResponse.getOrders();
			module.setOrders(orders+1);
			module.setLevel(level);
		}else{
			module.setOrders(1);
			module.setLevel(1);
		}
		moduleDao.insert(module);
	}
	
	@Transactional
	public void moduleModify(Module module) {
		moduleDao.update(module);
	}

	public Module moduleDetail(String moduleId) {
		return moduleDao.selectByPk(moduleId);
	}

}
