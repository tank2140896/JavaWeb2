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
import com.javaweb.annotation.url.ControllerMethod;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.base.BaseSystemMemory;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.dictionary.DictionaryListRequest;
import com.javaweb.web.po.Dictionary;
import com.javaweb.web.po.User;

//登录且需要权限才可访问的字典管理模块
@RestController
@RequestMapping(ApiConstant.WEB_DICTIONARY_PREFIX)
public class DictionaryController extends BaseController {
    
	@PostMapping(ApiConstant.DICTIONARY_ADD)
	@ControllerMethod(interfaceName="新增字典接口")
	public BaseResponseResult dictionaryAdd(@RequestBody Dictionary dictionary,@TokenDataAnnotation TokenData tokenData){
		User currentUser = tokenData.getUser();
		dictionary.setId(SecretUtil.defaultGenUniqueStr(SystemConstant.SYSTEM_NO));
		dictionary.setSort(CommonConstant.ZERO_STRING_VALUE);
		dictionary.setUniversally(0);
		dictionary.setCreator(currentUser.getUserId());
		dictionary.setCreateDate(DateUtil.getDefaultDate());
		dictionaryService.dictionaryAdd(dictionary);
		BaseSystemMemory.dictionaryList = dictionaryService.selectAll();
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dictionary.add.success",null);
	}
	
	@PostMapping(ApiConstant.DICTIONARY_LIST)
	@ControllerMethod(interfaceName="查询字典接口")
	public BaseResponseResult dictionaryList(@RequestBody DictionaryListRequest dictionaryListRequest){
		Page page = dictionaryService.dictionaryList(dictionaryListRequest);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dictionary.list.success",page);
	}
	
	@PutMapping(ApiConstant.DICTIONARY_MODIFY)
	@ControllerMethod(interfaceName="修改字典接口")
	public BaseResponseResult dictionaryModify(@RequestBody Dictionary dictionary,@TokenDataAnnotation TokenData tokenData){
		User currentUser = tokenData.getUser();
		dictionary.setUpdateDate(DateUtil.getDefaultDate());
		dictionary.setUpdater(currentUser.getUserId());
		dictionaryService.dictionaryModify(dictionary);
		BaseSystemMemory.dictionaryList = dictionaryService.selectAll();
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dictionary.modify.success",null);
	}
	
	@GetMapping(ApiConstant.DICTIONARY_DETAIL)
	@ControllerMethod(interfaceName="字典详情接口")
	public BaseResponseResult dictionaryDetail(@PathVariable(name="dictionaryId",required=true) String dictionaryId){
		Dictionary dictionary = dictionaryService.dictionaryDetail(dictionaryId);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dictionary.detail.success",dictionary);
	}
	
	@DeleteMapping(ApiConstant.DICTIONARY_DELETE)
	@ControllerMethod(interfaceName="删除字典接口（支持批量删除，用逗号隔开）")
	public BaseResponseResult dictionaryDelete(@PathVariable(name="dictionaryId",required=true) String dictionaryId){
		dictionaryService.dictionaryDelete(dictionaryId);
		BaseSystemMemory.dictionaryList = dictionaryService.selectAll();
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"dictionary.delete.success",null);
	}
	
}
