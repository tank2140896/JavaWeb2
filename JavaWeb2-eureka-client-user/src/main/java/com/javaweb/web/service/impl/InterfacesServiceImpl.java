package com.javaweb.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.javaweb.base.BaseService;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.interfaces.InterfacesListRequest;
import com.javaweb.web.po.Interfaces;
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
			if(urlMap.get(url)==null){
				try{
					swaggerApiName = map.get(requestMappingInfo).getMethodAnnotation(ApiOperation.class).value();
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

}
