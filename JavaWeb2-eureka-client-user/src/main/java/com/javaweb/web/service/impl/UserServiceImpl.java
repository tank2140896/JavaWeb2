package com.javaweb.web.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.base.BaseService;
import com.javaweb.base.BaseSystemMemory;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.FileUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.util.core.StringUtil;
import com.javaweb.util.core.SystemUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.role.ModuleInfoResponse;
import com.javaweb.web.eo.role.RoleIdAndStrategyRequest;
import com.javaweb.web.eo.user.RoleInfoResponse;
import com.javaweb.web.eo.user.UserListRequest;
import com.javaweb.web.eo.user.UserListResponse;
import com.javaweb.web.eo.user.UserLoginRequest;
import com.javaweb.web.po.Dictionary;
import com.javaweb.web.po.User;
import com.javaweb.web.po.UserModule;
import com.javaweb.web.po.UserRole;
import com.javaweb.web.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl extends BaseService implements UserService {
	
	public User userLogin(UserLoginRequest userLogin) {
		return userDao.userLogin(userLogin);
	}

	public Page userList(UserListRequest userListRequest){
		List<UserListResponse> list = userDao.userList(userListRequest);
		long count = userDao.userListCount(userListRequest);
		Page page = new Page(userListRequest,list,count);
		return page;
	}

	@Transactional
	public void userDelete(String userId) {
		String userIds[] = userId.split(",");
		for(String id:userIds){
			User user = userDao.selectByPkForMySql(userId);
			if(user.getPortrait()!=null){
				new File(user.getPortrait()).delete();//文件不存在也不会报错的
			}
			userDao.userDelete(id);
		}
	}
	
	@Transactional
	public void userAdd(User user) {
		userDao.insertForMySql(user);
	}

	@Transactional
	public void userModify(User user) {
		userDao.updateForMySql(user);
	}

	public User userDetail(String userId) {
		return userDao.userDetail(userId);
	}
	
	public List<User> getUsersByUserId(List<String> list) {
		return userDao.getUsersByUserId(list);
	}

	public List<RoleInfoResponse> userRoleInfo(String userId) {
		return userDao.userRoleInfo(userId);
	}
	
	@Transactional
	public void userRoleAssignment(String userId,List<RoleIdAndStrategyRequest> list) {
		Map<String,Object> map = new HashMap<>();
		List<UserRole> userRoleList = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			UserRole userRole = new UserRole();
			userRole.setId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
			userRole.setUserId(userId);
			userRole.setRoleId(list.get(i).getRoleId());
			Integer moduleStrategy = list.get(i).getModuleStrategy();
			Integer dataStrategy = list.get(i).getDataStrategy();
			userRole.setModuleStrategy(moduleStrategy==null?0:moduleStrategy);
			userRole.setDataStrategy(dataStrategy==null?0:dataStrategy);
			userRoleList.add(userRole);
		}
		map.put("userId",userId);
		map.put("list",userRoleList);
		userDao.userRoleAssignment(map);
	}
	
	public List<ModuleInfoResponse> userModuleInfo(String userId) {
		List<ModuleInfoResponse> list = userDao.userModuleInfo(userId);
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
	public void userModuleAssignment(String userId,List<String> list) {
		Map<String,Object> map = new HashMap<>();
		List<UserModule> userModuleList = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			UserModule userModule = new UserModule();
			userModule.setId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
			userModule.setUserId(userId);
			userModule.setModuleId(list.get(i));
			userModuleList.add(userModule);
		}
		map.put("userId",userId);
		map.put("list",userModuleList);
		userDao.userModuleAssignment(map);
	}

	public List<User> getAllUsers() {
		return userDao.selectAllForMySql();
	}

	public void userInitPassword(String userId,TokenData tokenData) {
		String initPassword = SystemConstant.SYSTEM_DEFAULT_USER_INIT_PASSWORD;
		Dictionary dictionary = BaseSystemMemory.getDictionaryByKey("init.user.password");
		if(dictionary!=null){
			initPassword = StringUtil.handleNullString(dictionary.getValueCode());
			if(initPassword.length()<6){
				initPassword = SystemConstant.SYSTEM_DEFAULT_USER_INIT_PASSWORD;
			}
		}
		try{
			initPassword = SecretUtil.getSecret(initPassword,"SHA-256");
		}catch(Exception e){ 
			//do nothing
		}
		User user = new User();
		user.setUserId(userId);
		user.setPassword(initPassword);
		user.setStatus(null);
		user.setUpdater(tokenData.getUser().getUserId());
		user.setUpdateDate(DateUtil.getDefaultDate());
		userDao.updateForMySql(user);
	}

	@Transactional
	public void userPortraitUpload(String userId,MultipartFile multipartFile) {
		String uploadMultipartFileName = multipartFile.getOriginalFilename();//得到上传文件的文件名称
		String uploadFileTypes[] = uploadMultipartFileName.split("\\.");
		String uploadFileName = userId+CommonConstant.DOT+uploadFileTypes[uploadFileTypes.length-1];
		String rootPath = getRootPath();
		FileUtil.makeFolder(new File(rootPath));
		boolean writeSuccess = true;
		try{
			FileUtil.writeFile(multipartFile.getInputStream(),new byte[1024],new File(rootPath+uploadFileName));
		}catch(IOException e){
			writeSuccess = false;
		}
		if(writeSuccess){
			User user = userDao.selectByPkForMySql(userId);
			if(user!=null){
				String portrait = user.getPortrait();
				if((portrait!=null)&&(!CommonConstant.EMPTY_VALUE.equals(portrait))){
					new File(portrait).delete();//文件不存在也不会报错的
				}
				user.setPortrait(rootPath+uploadFileName);
				userDao.updateForMySql(user);
			}
		}
	}
	
	private String getRootPath(){
		String rootPath = CommonConstant.EMPTY_VALUE;
		if(SystemUtil.isLinux()){//linux路径
			Dictionary dictionary = BaseSystemMemory.getDictionaryByKey("user.portrait.linux.path");
			if(dictionary!=null){
				rootPath = StringUtil.handleNullString(dictionary.getValueCode());
				if(!CommonConstant.EMPTY_VALUE.equals(rootPath)){
					return rootPath;
				}
			}
			return rootPath = SystemConstant.SYSTEM_DEFAULT_USER_PORTRAIT_LINUX_PATH;
		}else{//windows路径
			Dictionary dictionary = BaseSystemMemory.getDictionaryByKey("user.portrait.windows.path");
			if(dictionary!=null){
				rootPath = StringUtil.handleNullString(dictionary.getValueCode());
				if(!CommonConstant.EMPTY_VALUE.equals(rootPath)){
					return rootPath;
				}
			}
			return rootPath = SystemConstant.SYSTEM_DEFAULT_USER_PORTRAIT_WINDOWS_PATH;
		}
	}

	public void userPortrait(String userId,HttpServletResponse httpServletResponse) {
		if(userId!=null){
			User user = userDao.selectByPkForMySql(userId);
			if(user!=null){
				String portrait = user.getPortrait();
				if(portrait!=null){
					File file = new File(portrait);
					if(file.exists()){
						try {
							FileUtil.downloadFile(httpServletResponse.getOutputStream(),new byte[1024],file);
						} catch (IOException e) {
							//do nothing
						}
					}
				}
			}
		}
	}

}
