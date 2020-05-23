package com.javaweb.web.controller;

import java.util.List;

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
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.module.ModuleListRequest;
import com.javaweb.web.po.Module;

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
			moduleService.moduleAdd(tokenData.getUser(),module);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"module.add.success",null);
		}
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_MODULE_LIST)
	@PostMapping(ApiConstant.MODULE_LIST)
	public BaseResponseResult moduleList(@RequestBody ModuleListRequest moduleListRequest){
		Page page = moduleService.moduleList(moduleListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"module.list.success",page);
	}
	
	/**
	修改的逻辑为：
	1、删除权限的逻辑应该是：当前权限若是目录或菜单且有下级关联时，不能删除
	2、修改权限的逻辑应该是：
    	2.1、不能自己关联自己，即将pid设置为自己
     	2.2、判断是否有下级关联
     	2.3、在有下级关联的情况下（操作（按钮）永远排除在外），升降级判断（即非同级操作）：
        	2.3.1、当前权限若是目录不能向下降级为菜单或操作（按钮）
            2.3.2、当前权限若是菜单既不能向上升级也不能向下降级
	*/
	@ApiOperation(value=SwaggerConstant.SWAGGER_MODULE_MODIFY)
	@PutMapping(ApiConstant.MODULE_MODIFY)
	public BaseResponseResult moduleModify(@RequestBody @Validated({BaseValidatedGroup.update.class}) Module module,BindingResult bindingResult,@TokenDataAnnotation TokenData tokenData){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}else{
			Module originModule = moduleService.moduleDetail(module.getModuleId());
			if(originModule.getModuleId().equals(module.getParentId())){//不能自己关联自己
				return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,"module.modify.fail",null);
			}
			List<Module> moduleList = moduleService.getModuleByParentId(module.getModuleId());
			if(moduleList!=null&&moduleList.size()>0){//判断是否有下级关联
				if(originModule.getModuleType()!=module.getModuleType()){//判断是否是同级操作
					if(originModule.getModuleType()<module.getModuleType()){//判断是否是降级操作
						return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,"module.modify.fail",null);
					}else{
						if(originModule.getModuleType()!=1){//若是升级操作，只能原来是目录的升目录
							return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,"module.modify.fail",null);
						}
					}
				}
			}
			moduleService.moduleModify(tokenData.getUser(),module);
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
		String moduleIds[] = moduleId.split(",");
		for(String eachModuleId:moduleIds){
			//删除权限，只需要判断当前权限有没有下级关联，没有即可删除
			Module module = moduleService.moduleDetail(eachModuleId);
			if(module.getModuleType()!=3){//非功能需要进一步判断才能确定是否可以删除
				List<Module> moduleList = moduleService.getModuleByParentId(module.getModuleId());
				if(moduleList!=null&&moduleList.size()>0){//当删除目录或菜单时，若有下级关联，则不允许删除
					return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,"module.delete.fail",null);
				}
			}
		}
		moduleService.moduleDelete(moduleIds);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"module.delete.success",null);
	}
	
}
