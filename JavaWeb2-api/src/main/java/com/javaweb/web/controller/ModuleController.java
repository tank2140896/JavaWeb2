package com.javaweb.web.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.SwaggerConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.module.ModuleListRequest;
import com.javaweb.web.po.Module;
import com.javaweb.web.po.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_MODULE_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_MODULE_PREFIX)
public class ModuleController extends BaseController {
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_MODULE_ADD)
	@PostMapping(ApiConstant.MODULE_ADD)
	public BaseResponseResult moduleAdd(HttpServletRequest request,@RequestBody @Validated({BaseValidatedGroup.add.class}) Module module,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}else{
			TokenData tokenData = getTokenData(request);
			User currentUser = tokenData.getUser();
			module.setModuleId(SecretUtil.defaultGenUniqueStr());
			module.setCreateDate(DateUtil.getDefaultDate());
			module.setCreator(currentUser.getUserName());
			module.setDelFlag(0);
			moduleService.moduleAdd(module);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"module.add.success",null);
		}
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_MODULE_LIST)
	@PostMapping(ApiConstant.MODULE_LIST)
	public BaseResponseResult moduleList(HttpServletRequest request,@RequestBody ModuleListRequest moduleListRequest){
		Page page = moduleService.moduleList(moduleListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"module.list.success",page);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_MODULE_MODIFY)
	@PutMapping(ApiConstant.MODULE_MODIFY)
	public BaseResponseResult moduleModify(HttpServletRequest request,@RequestBody @Validated({BaseValidatedGroup.update.class}) Module module,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}else{
			TokenData tokenData = getTokenData(request);
			User currentUser = tokenData.getUser();
			module.setUpdateDate(DateUtil.getDefaultDate());
			module.setUpdater(currentUser.getUserName());
			moduleService.moduleModify(module);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"module.modify.success",null);
		}
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_MODULE_DETAIL)
	@GetMapping(ApiConstant.MODULE_DETAIL)
	public BaseResponseResult moduleDetail(@PathVariable(name="moduleId",required=true) String moduleId){
		Module module = moduleService.moduleDetail(moduleId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"module.detail.success",module);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_MODULE_DELETE)
	@DeleteMapping(ApiConstant.MODULE_DELETE)
	public BaseResponseResult moduleDelete(@PathVariable(name="moduleId",required=true) String moduleId){
		moduleService.moduleDelete(moduleId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"module.delete.success",null);
	}
	
}
