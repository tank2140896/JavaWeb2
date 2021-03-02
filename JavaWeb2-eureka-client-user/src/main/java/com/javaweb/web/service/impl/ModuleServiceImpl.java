package com.javaweb.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.base.BaseService;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.db.query.QueryWapper;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.module.ModuleIdAndNameResponse;
import com.javaweb.web.eo.module.ModuleListRequest;
import com.javaweb.web.eo.module.ModuleListResponse;
import com.javaweb.web.po.Module;
import com.javaweb.web.po.Role;
import com.javaweb.web.po.User;
import com.javaweb.web.po.UserRole;
import com.javaweb.web.service.ModuleService;

@Service("moduleServiceImpl")
public class ModuleServiceImpl extends BaseService implements ModuleService {
	
	public List<Module> getModule(boolean adminFlag,String userId) {
		if(adminFlag){//管理员
			return moduleDao.selectAll();
		}else{//非管理员
			//1.获得user_role的所有对应关系
			List<UserRole> userRoleList = userRoleDao.selectList(new QueryWapper<UserRole>().eq(UserRole.userIdColumn,userId));
			//2.获得所有模块ID
			List<String> allList = new ArrayList<>(); 
			for(int i=0;i<userRoleList.size();i++){
				List<String> list = null;
				UserRole userRole = userRoleList.get(i);
				int strategy = userRole.getModuleStrategy();//权限获取策略(0:自定义;1:并集;2:交集;3:差集;4:以用户权限为准;5:以角色权限为准;其它:默认为未定义,作为并集处理)
				List<String> list1 = userModuleDao.getModuleIdsByUserId(userRole.getUserId());
				List<String> list2 = roleModuleDao.getModuleIdsByRoleId(userRole.getRoleId());
				if(strategy==1){
					list = Stream.concat(list1.stream(),list2.stream()).distinct().collect(Collectors.toList());
				}else if(strategy==2){
					list = list1.stream().filter(item->list2.contains(item)).distinct().collect(Collectors.toList());
				}else if(strategy==3){
					List<String> list3 = list1.stream().filter(item->list2.contains(item)).distinct().collect(Collectors.toList());//交集
					List<String> list4 = Stream.concat(list1.stream(),list2.stream()).distinct().collect(Collectors.toList());//并集
					list3.stream().forEach(item->list4.remove(item));
					list = list4;
				}else if(strategy==4){
					list = list1;
				}else if(strategy==5){
					list = list2;
				}else{
					list = Stream.concat(list1.stream(),list2.stream()).distinct().collect(Collectors.toList());//自定义暂做并集处理
				}
				allList = Stream.concat(allList.stream(),list.stream()).distinct().collect(Collectors.toList());
			}
			//3.获得模块集合
			return moduleDao.getModuleByModuleId(allList);
		}
	}

	public Page moduleList(ModuleListRequest moduleListRequest) {
		List<ModuleListResponse> list = moduleDao.moduleList(moduleListRequest);
		long count = moduleDao.moduleListCount(moduleListRequest);
		Page page = new Page(moduleListRequest,list,count);
		return page;
	}

	@Transactional
	public void moduleDelete(String moduleIds[]) {
		for(String id:moduleIds){
			moduleDao.moduleDelete(id);
		}
	}

	@Transactional
	public void moduleAdd(User user,Module module) {
		module.setCreateDate(DateUtil.getDefaultDate());
		module.setCreator(user.getUserId());
		module.setDelFlag(0);
		if(module.getModuleType()==1){//目录
			module.setApiUrl(null);
			module.setPageUrl(null);
		}else if(module.getModuleType()==2){//菜单
			module.setApiUrl(null);
		}else if(module.getModuleType()==3){//功能
			module.setPageUrl(null);
		}
		if(module.getParentId()==null||CommonConstant.EMPTY_VALUE.equals(module.getParentId().trim())){
			module.setParentId(null);
		}
		if(module.getParentId()==null){//表示是顶级目录
			module.setLevel(1);
		}else{
			Module parentModuleInfo = moduleDao.moduleDetail(module.getParentId());
			module.setLevel(parentModuleInfo.getLevel()+1);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("parentId",module.getParentId());
		map.put("level",module.getLevel());
		Long orders = moduleDao.getOrders(map);
		orders = (orders==null?1:orders);
		module.setOrders(orders.intValue());
		module.setSystemId(SystemConstant.SYSTEM_NO);
		moduleDao.insert(module);
	}
	
	@Transactional
	public void moduleModify(User user,Module module) {
		module.setLevel(null);
		module.setType(null);
		module.setUpdateDate(DateUtil.getDefaultDate());
		module.setUpdater(user.getUserId());
		if(module.getParentId()==null||CommonConstant.EMPTY_VALUE.equals(module.getParentId().trim())){
			module.setParentId(null);
			moduleDao.setModuleParentIdNull(module);//当parentId为null表示设置为根目录，此时需要特殊处理，因为大部分更新操作代码逻辑对null值是不更新数据库数据的
		}
		moduleDao.update(module);
	}

	public Module moduleDetail(String moduleId) {
		return moduleDao.moduleDetail(moduleId);
	}

    public List<ModuleIdAndNameResponse> getModuleIdAndNameList(String moduleType) {
    	/** 这里约定：
    	1、选择目录（1）需要返回可选的上级模块列表，条件为moduleType=1
        2、选择菜单（2）需要返回可选的上级模块列表，条件为moduleType=1
        3、选择功能（3）需要返回可选的上级模块列表，条件为moduleType=2且pageUrl不为null
        */
        Map<String,String> map = new HashMap<>();
        map.put("moduleType",moduleType);
        return moduleDao.getModuleIdAndNameList(map);
    }

	public List<Role> getAllRoleByModuleId(String moduleId) {
		return moduleDao.getAllRoleByModuleId(moduleId);
	}
	
	public List<User> getAllUserByModuleId(String moduleId) {
		return moduleDao.getAllUserByModuleId(moduleId);
	}

	public List<Module> getModuleByParentId(String parentId) {
		return moduleDao.getModuleByParentId(parentId);
	}

}
