package com.javaweb.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.module.ModuleListRequest;
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

}
