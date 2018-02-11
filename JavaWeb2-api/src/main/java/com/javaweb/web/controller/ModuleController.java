package com.javaweb.web.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseValidatedGroup;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.module.ModuleListRequest;
import com.javaweb.web.po.Module;
import com.javaweb.web.po.User;
import com.javaweb.web.service.ModuleService;

@RestController
@RequestMapping("/web/sys/module")
public class ModuleController extends BaseController {
	
	@Autowired
	private ModuleService moduleService;
	
	@PostMapping("/list")
	public BaseResponseResult moduleList(HttpServletRequest request,@RequestBody ModuleListRequest moduleListRequest){
		Page page = moduleService.moduleList(moduleListRequest);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("module.list.success"),page);
	}
	
	@DeleteMapping("/delete/{moduleId}")
	public BaseResponseResult moduleDelete(@PathVariable("moduleId") String moduleId){
		moduleService.moduleDelete(moduleId);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("module.delete.success"),null);
	}
	
	@PostMapping("/add")
	public BaseResponseResult moduleAdd(HttpServletRequest request,@RequestBody @Validated({BaseValidatedGroup.add.class}) Module module,BindingResult bindingResult){
		BaseResponseResult baseResponseResult = new BaseResponseResult();
		if(bindingResult.hasErrors()){
			baseResponseResult = new BaseResponseResult(SystemConstant.VALIDATE_ERROR,getValidateMessage(bindingResult),CommonConstant.EMPTY_VALUE);
		}else{
			TokenData tokenData = getTokenData(request);
			User currentUser = tokenData.getUser();
			module.setModuleId(UUID.randomUUID().toString());
			module.setCreateDate(DateUtil.getDefaultDate());
			module.setCreator(currentUser.getUserName());
			moduleService.moduleAdd(module);
			baseResponseResult = new BaseResponseResult(SystemConstant.SUCCESS,getMessage("module.add.success"),null);
		}
		return baseResponseResult;
	}
	
	@PutMapping("/modify")
	public BaseResponseResult moduleModify(HttpServletRequest request,@RequestBody @Validated({BaseValidatedGroup.update.class}) Module module,BindingResult bindingResult){
		BaseResponseResult baseResponseResult = new BaseResponseResult();
		if(bindingResult.hasErrors()){
			baseResponseResult = new BaseResponseResult(SystemConstant.VALIDATE_ERROR,getValidateMessage(bindingResult),CommonConstant.EMPTY_VALUE);
		}else{
			TokenData tokenData = getTokenData(request);
			User currentUser = tokenData.getUser();
			module.setUpdateDate(DateUtil.getDefaultDate());
			module.setUpdater(currentUser.getUserName());
			moduleService.moduleModify(module);
			baseResponseResult = new BaseResponseResult(SystemConstant.SUCCESS,getMessage("module.modify.success"),null);
		}
		return baseResponseResult;
	}
	
	@GetMapping("/detail/{moduleId}")
	public BaseResponseResult moduleDetail(@PathVariable("moduleId") String moduleId){
		Module module = moduleService.moduleDetail(moduleId);
		return new BaseResponseResult(SystemConstant.SUCCESS,getMessage("module.detail.success"),module);
	}

}
