package com.javaweb.web.controller;

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

import com.javaweb.annotation.token.TokenDataAnnotation;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseValidatedGroup;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.SwaggerConstant;
import com.javaweb.constant.SystemConstant;
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
    
    @ApiOperation(value=SwaggerConstant.SWAGGER_MODULE_GET_MODULE_ID_AND_NAME_LIST)
    @GetMapping(ApiConstant.MODULE_GET_MODULE_ID_AND_NAME_LIST)
    public BaseResponseResult getModuleIdAndNameList(@PathVariable(name="moduleType",required=false) String moduleType) {
        return getBaseResponseResult(HttpCodeEnum.SUCCESS,"module.getModuleIdAndNameList.success",moduleService.getModuleIdAndNameList(moduleType));
    }
	
    /**
          实践建议：
    1、目录：模块的上级ID可选（那就是子目录）也可不选（那就是根目录），页面URL和API的URL都是不需要的
    2、菜单：模块的上级ID必选（所有目录，即moduleType=1），页面URL需要
    3、功能：模块的上级ID必选（所有菜单，即moduleType=2），API的URL需要
	*/
	@ApiOperation(value=SwaggerConstant.SWAGGER_MODULE_ADD)
	@PostMapping(ApiConstant.MODULE_ADD)
	public BaseResponseResult moduleAdd(@RequestBody @Validated({BaseValidatedGroup.add.class}) Module module,BindingResult bindingResult,@TokenDataAnnotation TokenData tokenData){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}else{
			User currentUser = tokenData.getUser();
			module.setModuleId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
			module.setCreateDate(DateUtil.getDefaultDate());
			module.setCreator(currentUser.getUserName());
			module.setDelFlag(0);
			if(module.getModuleType()==1){//目录
				module.setApiUrl(null);
				module.setPageUrl(null);
			}else if(module.getModuleType()==2){//菜单
				module.setApiUrl(null);
			}else if(module.getModuleType()==3){//功能
				module.setPageUrl(null);
			}
			moduleService.moduleAdd(module);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"module.add.success",null);
		}
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_MODULE_LIST)
	@PostMapping(ApiConstant.MODULE_LIST)
	public BaseResponseResult moduleList(@RequestBody ModuleListRequest moduleListRequest){
		Page page = moduleService.moduleList(moduleListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"module.list.success",page);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_MODULE_MODIFY)
	@PutMapping(ApiConstant.MODULE_MODIFY)
	public BaseResponseResult moduleModify(@RequestBody @Validated({BaseValidatedGroup.update.class}) Module module,BindingResult bindingResult,@TokenDataAnnotation TokenData tokenData){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}else{
			User currentUser = tokenData.getUser();
			module.setLevel(null);
			module.setType(null);
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
	
    //支持批量删除，用逗号隔开
	@ApiOperation(value=SwaggerConstant.SWAGGER_MODULE_DELETE)
	@DeleteMapping(ApiConstant.MODULE_DELETE)
	public BaseResponseResult moduleDelete(@PathVariable(name="moduleId",required=true) String moduleId){
		moduleService.moduleDelete(moduleId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"module.delete.success",null);
	}
	
}
