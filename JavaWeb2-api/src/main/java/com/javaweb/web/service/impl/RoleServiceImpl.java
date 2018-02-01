package com.javaweb.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.util.core.PageUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.dao.ds1.RoleDao;
import com.javaweb.web.eo.role.RoleListRequest;
import com.javaweb.web.eo.role.RoleListResponse;
import com.javaweb.web.service.RoleService;

@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

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

}
