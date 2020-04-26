package com.javaweb.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.javaweb.annotation.url.DataPermission;
import com.javaweb.base.BaseService;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.interfaces.ExcludeInfoResponse;
import com.javaweb.web.eo.interfaces.InterfacesListRequest;
import com.javaweb.web.eo.interfaces.RolePermissionResponse;
import com.javaweb.web.eo.interfaces.UserPermissionResponse;
import com.javaweb.web.eo.interfaces.UserRolePermissionResponse;
import com.javaweb.web.po.Interfaces;
import com.javaweb.web.po.RoleData;
import com.javaweb.web.po.User;
import com.javaweb.web.po.UserData;
import com.javaweb.web.po.UserRole;
import com.javaweb.web.service.InterfacesService;

import io.swagger.annotations.ApiOperation;

@Service("interfacesServiceImpl")
public class InterfacesServiceImpl extends BaseService implements InterfacesService {
	
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	public Page interfacesList(InterfacesListRequest interfacesListRequest) {
		List<Interfaces> list = interfacesDao.interfacesList(interfacesListRequest);
		long count = interfacesDao.interfacesListCount(interfacesListRequest);
		Page page = new Page(interfacesListRequest,list,count);
		return page;
	}
	
	public Interfaces interfacesDetail(String interfacesId) {
		return interfacesDao.selectByPkForMySql(interfacesId);
	}

	@Transactional
	public void interfacesModify(Interfaces interfaces) {
		interfacesDao.updateForMySql(interfaces);
	}

	@Transactional
	public void synchronizedInterfaces() {
		List<Interfaces> projectList = getAllInterfaces();//获得本项目中的所有接口
		List<Interfaces> dbList = interfacesDao.selectAllForMySql();//获得数据库中的所有接口
		List<Interfaces> forUpdate = new ArrayList<>();
		List<Interfaces> forInsert = new ArrayList<>();
		for(int i=0;i<projectList.size();i++){
			Interfaces projectListEach = projectList.get(i);
			boolean continueFlag = true;
			for(int j=0;j<dbList.size();j++){
				Interfaces dbListEach = dbList.get(j);
				if(projectListEach.getUrl().trim().equals(dbListEach.getUrl().trim())){//存在则更新
					dbListEach.setName(projectListEach.getName());
					dbListEach.setUrl(projectListEach.getUrl());
					dbListEach.setMethod(projectListEach.getMethod());
					dbListEach.setDataPermission(projectListEach.getDataPermission());
					dbListEach.setUpdateDate(DateUtil.getDefaultDate());
					dbListEach.setUpdater(SystemConstant.SYSTEM_DEFAULT_USER_ID);
					forUpdate.add(dbListEach);
					dbList.remove(dbListEach);
					continueFlag = false;
					break;
				}
			}
			if(continueFlag){
				projectListEach.setId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
				projectListEach.setCreateDate(DateUtil.getDefaultDate());
				projectListEach.setCreator(SystemConstant.SYSTEM_DEFAULT_USER_ID);
				projectListEach.setDelFlag(CommonConstant.ZERO_NUMBER_VALUE);
				forInsert.add(projectListEach);
			}
		}
		if(dbList.size()>0){
			interfacesDao.interfacesBatchDelete(dbList.stream().map(e->e.getId()).collect(Collectors.toList()));
		}
		if(forUpdate.size()>0){
			for(int k=0;k<forUpdate.size();k++){
				interfacesDao.updateForMySql(forUpdate.get(k));
			}
		}
		if(forInsert.size()>0){
			interfacesDao.interfacesBatchInsert(forInsert);
		}
	}
	
	private List<Interfaces> getAllInterfaces(){
		List<Interfaces> list = new ArrayList<>();
		Map<RequestMappingInfo,HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
		Set<RequestMappingInfo> set = map.keySet();
		Iterator<RequestMappingInfo> iterator = set.iterator();
		Map<String,String> urlMap = new HashMap<>();
		while(iterator.hasNext()){
			Interfaces interfaces = new Interfaces();
			RequestMappingInfo requestMappingInfo = iterator.next();
			String methodName = requestMappingInfo.getMethodsCondition().getMethods().toString().replace("[","").replace("]","");
			String url = requestMappingInfo.getPatternsCondition().toString().replace("[","").replace("]", "").replace(" || ",",");
			String swaggerApiName = null;
			DataPermission dataPermission = null;
			if(urlMap.get(url)==null){
				try{
					swaggerApiName = map.get(requestMappingInfo).getMethodAnnotation(ApiOperation.class).value();
				}catch(Exception e){
					//do nothing
				}
				interfaces.setDataPermission(0);
				try{
					dataPermission = map.get(requestMappingInfo).getMethodAnnotation(DataPermission.class);
					if(dataPermission!=null){
						interfaces.setDataPermission(1);
					}
				}catch(Exception e){
					//do nothing
				}
				//url.matches("/web(?!/loginAccess).*")&&methodName.matches("GET||POST")
				interfaces.setName(swaggerApiName);
				interfaces.setUrl(url);
				interfaces.setMethod(methodName);
				list.add(interfaces);
				urlMap.put(url,url);
			}
		}
		return list;
	}

	public UserRolePermissionResponse userRoleDataPermission(String interfacesId) {
		//获得所有用户及其设定的排除字段（这里做的比较简单，没有分页和筛选查询，实际项目这部分请自行改造）
		List<UserPermissionResponse> userPermissionResponseList = interfacesDao.userPermissionList(interfacesId);
		//获得所有角色及其设定的排除字段（这里做的比较简单，没有分页和筛选查询，实际项目这部分请自行改造）
		List<RolePermissionResponse> rolePermissionResponseList = interfacesDao.rolePermissionList(interfacesId);
		UserRolePermissionResponse userRolePermissionResponse = new UserRolePermissionResponse();
		userRolePermissionResponse.setUserPermissionResponseList(userPermissionResponseList);
		userRolePermissionResponse.setRolePermissionResponseList(rolePermissionResponseList);
		return userRolePermissionResponse;
	}

	@Transactional
	public void dataPermissionAssignment(UserRolePermissionResponse userRolePermissionResponse,String interfacesId,User user) {
		List<UserPermissionResponse> userPermissionResponseList = userRolePermissionResponse.getUserPermissionResponseList();
		List<RolePermissionResponse> rolePermissionResponseList = userRolePermissionResponse.getRolePermissionResponseList();
		userPermissionResponseList = userPermissionResponseList.stream().filter(each->(each.getExcludeField()!=null)&&(!each.getExcludeField().trim().equals(CommonConstant.EMPTY_VALUE))).collect(Collectors.toList());
		rolePermissionResponseList = rolePermissionResponseList.stream().filter(each->(each.getExcludeField()!=null)&&(!each.getExcludeField().trim().equals(CommonConstant.EMPTY_VALUE))).collect(Collectors.toList());
		interfacesDao.clearUserRoleDataPermission();//因为每次都是获得所有用户和角色的数据权限，所以每次都是先清空表再插入（这里做的比较简单，没有判断是否存在，存在更新不存在插入的逻辑，实际项目这部分请自行改造）
		for(int i=0;i<userPermissionResponseList.size();i++){//用户数据权限
			com.javaweb.web.po.DataPermission dataPermission = new com.javaweb.web.po.DataPermission();
			String dataPermissionId = SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO);
			dataPermission.setId(dataPermissionId);
			dataPermission.setExcludeField(userPermissionResponseList.get(i).getExcludeField());
			dataPermission.setInterfacesId(interfacesId);
			dataPermission.setCreateDate(DateUtil.getDefaultDate());
			dataPermission.setCreator(user.getUserId());
			UserData userData = new UserData();
			userData.setId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
			userData.setUserId(userPermissionResponseList.get(i).getUserId());
			userData.setDataPermissionId(dataPermissionId);
			dataPermissionDao.insertForMySql(dataPermission);
			userDataDao.insertForMySql(userData);
		}
		for(int i=0;i<rolePermissionResponseList.size();i++){//角色数据权限
			com.javaweb.web.po.DataPermission dataPermission = new com.javaweb.web.po.DataPermission();
			String dataPermissionId = SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO);
			dataPermission.setId(dataPermissionId);
			dataPermission.setExcludeField(rolePermissionResponseList.get(i).getExcludeField());
			dataPermission.setInterfacesId(interfacesId);
			dataPermission.setCreateDate(DateUtil.getDefaultDate());
			dataPermission.setCreator(user.getUserId());
			RoleData roleData = new RoleData();
			roleData.setId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
			roleData.setRoleId(rolePermissionResponseList.get(i).getRoleId());
			roleData.setDataPermissionId(dataPermissionId);
			dataPermissionDao.insertForMySql(dataPermission);
			roleDataDao.insertForMySql(roleData);
		}
	}
	
	public List<ExcludeInfoResponse> getExcludeInfoResponseList(String userId){
		//1、根据当前用户ID获得用户角色和策略
		List<UserRole> userRoleList = userRoleDao.getUserRoleByUserId(userId);
		//2、根据当前用户ID获得data_permission_id
		List<UserData> userDataList = userDataDao.selectAllByUserId(userId);
		//3、根据当前用户角色ID获得data_permission_id
		List<String> roleIdList = userRoleList.stream().map(e->e.getRoleId()).collect(Collectors.toList());
		List<RoleData> roleDataList = roleDataDao.selectAllByRoleIds(roleIdList);
		//4、根据策略合并2和3
		List<String> allList = new ArrayList<>(); 
		for(int i=0;i<userRoleList.size();i++){
			List<String> list = null;
			UserRole userRole = userRoleList.get(i);
			int strategy = userRole.getDataStrategy();//权限获取策略(0:自定义;1:并集;2:交集;3:差集;4:以用户权限为准;5:以角色权限为准;其它:默认为未定义,作为并集处理)
			List<String> list1 = userDataList.stream().map(e->e.getDataPermissionId()).collect(Collectors.toList());
			List<String> list2 = roleDataList.stream().map(e->e.getDataPermissionId()).collect(Collectors.toList());
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
				list = Stream.concat(list1.stream(),list2.stream()).distinct().collect(Collectors.toList());
			}
			allList = Stream.concat(allList.stream(),list.stream()).distinct().collect(Collectors.toList());
		}
		//5、获得data_permission集合
		List<ExcludeInfoResponse> excludeInfoResponseList = dataPermissionDao.selectExcludeInfo(allList);
		return excludeInfoResponseList;
	}

}
