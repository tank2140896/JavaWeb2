package com.javaweb.web.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.javaweb.annotation.url.ControllerMethod;
import com.javaweb.base.BaseService;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.HttpUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.util.core.SystemUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.interfaces.ExcludeInfoResponse;
import com.javaweb.web.eo.interfaces.InterfacesListRequest;
import com.javaweb.web.eo.interfaces.InterfacesTestRequest;
import com.javaweb.web.eo.interfaces.RolePermissionResponse;
import com.javaweb.web.eo.interfaces.UserPermissionResponse;
import com.javaweb.web.eo.interfaces.UserRoleDataPermissionRequest;
import com.javaweb.web.eo.interfaces.UserRolePermissionRequest;
import com.javaweb.web.po.Interfaces;
import com.javaweb.web.po.RoleData;
import com.javaweb.web.po.User;
import com.javaweb.web.po.UserData;
import com.javaweb.web.po.UserRole;
import com.javaweb.web.service.InterfacesService;

import net.sf.json.JSONObject;

@Service("interfacesServiceImpl")
public class InterfacesServiceImpl extends BaseService implements InterfacesService {
	
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	public Page interfacesList(InterfacesListRequest interfacesListRequest) {
		List<Interfaces> list = interfacesDao.interfacesList(interfacesListRequest);
		if(list!=null){
			//只是为了显示方便（逗号分隔，回车展示）
			list.stream().forEach(e->e.setUrl(String.join(SystemUtil.isLinux()==true?CommonConstant.ENTER_LINUX:CommonConstant.ENTER_WINDOWS,e.getUrl().split(CommonConstant.COMMA))));
		}
		long count = interfacesDao.interfacesListCount(interfacesListRequest);
		Page page = new Page(interfacesListRequest,list,count);
		return page;
	}
	
	public Interfaces interfacesDetail(String interfacesId) {
		return interfacesDao.selectByPk(interfacesId);
	}

	@Transactional
	public void interfacesModify(Interfaces interfaces) {
		interfacesDao.update(interfaces);
	}

	@Transactional
	public void synchronizedInterfaces() {
		List<Interfaces> projectList = getAllInterfaces();//获得本项目中的所有接口
		List<Interfaces> dbList = getAll();//获得数据库中的所有接口
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
					dbListEach.setBaseUrl(projectListEach.getBaseUrl());
					dbListEach.setMethod(projectListEach.getMethod());
					dbListEach.setDataPermission(projectListEach.getDataPermission());
					dbListEach.setEntity(projectListEach.getEntity());
					dbListEach.setHistoryTimes(projectListEach.getHistoryTimes());
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
				projectListEach.setDataPermission(CommonConstant.ZERO_NUMBER_VALUE);
				projectListEach.setHistoryTimes(new BigInteger(CommonConstant.ZERO_STRING_VALUE));
				projectListEach.setRequestDataSecret(CommonConstant.ZERO_NUMBER_VALUE);
				projectListEach.setResponseDataSecret(CommonConstant.ZERO_NUMBER_VALUE);
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
				interfacesDao.update(forUpdate.get(k));
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
			String name = null;
			ControllerMethod controllerMethod = null;
			if(urlMap.get(url)==null){
				interfaces.setDataPermission(0);
				try{
					controllerMethod = map.get(requestMappingInfo).getMethodAnnotation(ControllerMethod.class);
					if(controllerMethod!=null){
						Class<?> c = controllerMethod.dataPermissionEntity();
						if(!(ControllerMethod.class.getName().equals(c.getName()))){//不是DataPermission的实例，定义了明确的实体类
							interfaces.setDataPermission(1);
							interfaces.setEntity(Stream.of(c.getDeclaredFields()).map(e->e.getName()).filter(e->!"serialVersionUID".equals(e)).collect(Collectors.toList()).toString());
						}
					}
				}catch(Exception e){
					//do nothing
				}
				//url.matches("/web(?!/loginAccess).*")&&methodName.matches("GET||POST")
				interfaces.setName(name);
				interfaces.setUrl(url);
				interfaces.setBaseUrl(url.split(CommonConstant.COMMA)[0].replaceAll("/\\{.*\\}",CommonConstant.EMPTY_VALUE));
				interfaces.setMethod(methodName);
				list.add(interfaces);
				urlMap.put(url,url);
			}
		}
		return list;
	}

	public Page userRoleDataPermission(UserRoleDataPermissionRequest userRoleDataPermissionRequest) {
		if(userRoleDataPermissionRequest.getType()==1){//用户
			List<UserPermissionResponse> userPermissionResponseList = interfacesDao.userPermissionList(userRoleDataPermissionRequest);
			long count = interfacesDao.userPermissionListCount(userRoleDataPermissionRequest);
			Page page = new Page(userRoleDataPermissionRequest,userPermissionResponseList,count);
			return page;
		}else{//角色
			List<RolePermissionResponse> rolePermissionResponseList = interfacesDao.rolePermissionList(userRoleDataPermissionRequest);
			long count = interfacesDao.rolePermissionListCount(userRoleDataPermissionRequest);
			Page page = new Page(userRoleDataPermissionRequest,rolePermissionResponseList,count);
			return page;
		}
	}

	@Transactional
	public void dataPermissionAssignment(UserRolePermissionRequest userRolePermissionResponse,String interfacesId,User user) {
		List<UserPermissionResponse> userPermissionResponseList = userRolePermissionResponse.getUserPermissionResponseList();
		List<RolePermissionResponse> rolePermissionResponseList = userRolePermissionResponse.getRolePermissionResponseList();
		//根据user_id删除user_data和data_permission相关的数据
		List<String> userIds = userPermissionResponseList.stream().map(e->e.getUserId()).collect(Collectors.toList());
		if(userIds!=null&&userIds.size()>0){
			interfacesDao.deleteUserDataPermission(userIds);
		}
		//根据user_id删除user_data和data_permission相关的数据
		List<String> roleIds = rolePermissionResponseList.stream().map(e->e.getRoleId()).collect(Collectors.toList());
		if(roleIds!=null&&roleIds.size()>0){
			interfacesDao.deleteRoleDataPermission(roleIds);
		}
		userPermissionResponseList = userPermissionResponseList.stream().filter(e->{return (e.getExcludeField()!=CommonConstant.NULL_VALUE)&&(!e.getExcludeField().equals(CommonConstant.EMPTY_VALUE));}).collect(Collectors.toList());
		rolePermissionResponseList = rolePermissionResponseList.stream().filter(e->{return (e.getExcludeField()!=CommonConstant.NULL_VALUE)&&(!e.getExcludeField().equals(CommonConstant.EMPTY_VALUE));}).collect(Collectors.toList());
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
			dataPermissionDao.insert(dataPermission);
			userDataDao.insert(userData);
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
			dataPermissionDao.insert(dataPermission);
			roleDataDao.insert(roleData);
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
		if(allList.size()>0){
			return dataPermissionDao.selectExcludeInfo(allList);
		}else{
			return null;
		}
	}

	public void synchronizedRedisInterfaceHistoryTimes() {
		List<Interfaces> list = interfacesDao.selectAll();
		Set<Object> set = stringRedisTemplate.opsForHash().keys(SystemConstant.REDIS_INTERFACE_COUNT_KEY);
		if(list!=null&&list.size()>0){
			if(set!=null&&set.size()>0){
				for(Object obj:set){
					String url = obj.toString();
					BigInteger historyTimes = new BigInteger(stringRedisTemplate.opsForHash().get(SystemConstant.REDIS_INTERFACE_COUNT_KEY,obj).toString());
					for(int i=0;i<list.size();i++){
						Interfaces each = list.get(i);
						if(each.getBaseUrl().equals(url)){
							each.setHistoryTimes(historyTimes.add(each.getHistoryTimes()));//数字累加
							stringRedisTemplate.opsForHash().put(SystemConstant.REDIS_INTERFACE_COUNT_KEY,obj,CommonConstant.ZERO_STRING_VALUE);//重置redis中的统计数据为0
							break;
						}
					}
				}
			}
		}
		for(int i=0;i<list.size();i++){
			interfacesDao.update(list.get(i));//更新库中的统计数据信息
		}
	}

	public List<Interfaces> getAll() {
		return interfacesDao.selectAll();//获得数据库中的所有接口
	}

	public String interfacesTest(InterfacesTestRequest interfacesTestRequest) {
		String out = null;
		try{
			String url = interfacesTestRequest.getRequestUrl();
			List<Header> list = null;
			if(interfacesTestRequest.getRequestHeader()!=null&&!"".equals(interfacesTestRequest.getRequestHeader().trim())){
				list = new ArrayList<>();
				try{
					String keyvalue[] = interfacesTestRequest.getRequestHeader().split(CommonConstant.SEMICOLON);
					for(String str:keyvalue){
						String strs[] = str.split(CommonConstant.COLON); 
						Header header = new BasicHeader(strs[0],strs[1]);
						list.add(header);
					}
				}catch(Exception e){
					//header解析错误暂不做任何处理
				}
			}
			String body = interfacesTestRequest.getRequestBody();
			if(body!=null&&!"".equals(body)){
				body = JSONObject.fromObject(body.replaceAll("\n","")).toString();
			}
			if("GET".equals(interfacesTestRequest.getRequestType().toUpperCase())){
				out = HttpUtil.defaultGetRequest(url,list);
			}else if("PUT".equals(interfacesTestRequest.getRequestType().toUpperCase())){
				out = HttpUtil.defaultPutRequest(url,body,list);
			}else if("POST".equals(interfacesTestRequest.getRequestType().toUpperCase())){
				out = HttpUtil.defaultPostRequest(url,body,list);
			}else if("DELETE".equals(interfacesTestRequest.getRequestType().toUpperCase())){
				out = HttpUtil.defaultDeleteRequest(url,body,list);
			}
		}catch(Exception e){
			out = e.getMessage();
		}
		return out;
	}

}
