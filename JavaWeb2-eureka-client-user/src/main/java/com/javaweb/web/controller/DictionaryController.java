package com.javaweb.web.controller;

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
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SwaggerConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.dictionary.DictionaryListRequest;
import com.javaweb.web.po.Dictionary;
import com.javaweb.web.po.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_DICTIONARY_CONTROLLER_TAGS)
@RestController
@RequestMapping(ApiConstant.WEB_DICTIONARY_PREFIX)
public class DictionaryController extends BaseController {
    
	@ApiOperation(value=SwaggerConstant.SWAGGER_DICTIONARY_ADD)
	@PostMapping(ApiConstant.DICTIONARY_ADD)
	public BaseResponseResult dictionaryAdd(@RequestBody Dictionary dictionary,@TokenDataAnnotation TokenData tokenData){
		User currentUser = tokenData.getUser();
		dictionary.setId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
		dictionary.setSort(CommonConstant.ZERO_STRING_VALUE);
		dictionary.setUniversally(0);
		dictionary.setCreator(currentUser.getUserId());
		dictionary.setCreateDate(DateUtil.getDefaultDate());
		dictionaryService.dictionaryAdd(dictionary);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dictionary.add.success",null);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_DICTIONARY_LIST)
	@PostMapping(ApiConstant.DICTIONARY_LIST)
	public BaseResponseResult dictionaryList(@RequestBody DictionaryListRequest dictionaryListRequest){
		Page page = dictionaryService.dictionaryList(dictionaryListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dictionary.list.success",page);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_DICTIONARY_MODIFY)
	@PutMapping(ApiConstant.DICTIONARY_MODIFY)
	public BaseResponseResult dictionaryModify(@RequestBody Dictionary dictionary,@TokenDataAnnotation TokenData tokenData){
		User currentUser = tokenData.getUser();
		dictionary.setUpdateDate(DateUtil.getDefaultDate());
		dictionary.setUpdater(currentUser.getUserName());
		dictionaryService.dictionaryModify(dictionary);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dictionary.modify.success",null);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_DICTIONARY_DETAIL)
	@GetMapping(ApiConstant.DICTIONARY_DETAIL)
	public BaseResponseResult dictionaryDetail(@PathVariable(name="dictionaryId",required=true) String dictionaryId){
		Dictionary dictionary = dictionaryService.dictionaryDetail(dictionaryId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dictionary.detail.success",dictionary);
	}
	
    //支持批量删除，用逗号隔开
	@ApiOperation(value=SwaggerConstant.SWAGGER_DICTIONARY_DELETE)
	@DeleteMapping(ApiConstant.DICTIONARY_DELETE)
	public BaseResponseResult dictionaryDelete(@PathVariable(name="dictionaryId",required=true) String dictionaryId){
		dictionaryService.dictionaryDelete(dictionaryId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dictionary.delete.success",null);
	}
	
}
