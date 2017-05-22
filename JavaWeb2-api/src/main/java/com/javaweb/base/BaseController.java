package com.javaweb.base;

import javax.servlet.http.HttpServletRequest;

import com.javaweb.constant.SystemConstant;
import com.javaweb.controller.LoginController;
import com.javaweb.dataobject.eo.CheckData;
import com.javaweb.dataobject.eo.TokenData;
import com.javaweb.help.ResponseResult;

public class BaseController extends Base {
	
	/**
	httpServletRequest.getRequestURI()             /javaweb/app/html/home.html
	httpServletRequest.getRequestURL().toString()  http://localhost:8080/javaweb/app/html/home.html 
	httpServletRequest.getServletPath()            /app/html/home.html
	*/
	
	public CheckData check(HttpServletRequest request){
		CheckData checkData = new CheckData();
		try {
			String userId = request.getHeader(SystemConstant.HEAD_USERID);
			String token = request.getHeader(SystemConstant.HEAD_TOKEN);
			String servletPath = request.getServletPath();
			if(userId==null||token==null){
				checkData.setResponseResult(new ResponseResult(SystemConstant.REQUEST_PARAMETER_LOST,"请求参数缺失",null));
			}
			TokenData tokenData = LoginController.getCache(userId,valueOperations);
			if(tokenData==null){
				checkData.setResponseResult(new ResponseResult(SystemConstant.INVALID_REQUEST_CODE,"无效请求",null));
			}
			if(!tokenData.getUser().getUserId().equals(userId)){
				checkData.setResponseResult(new ResponseResult(SystemConstant.REQUEST_PARAMETER_ERROR,"请求参数错误",null));
			}
			if(!tokenData.getToken().equals(token)){
				checkData.setResponseResult(new ResponseResult(SystemConstant.REQUEST_PARAMETER_ERROR,"请求参数错误",null));;
			}
			long count = tokenData.getAuthOperateList().stream().filter(i->i.getApiUrl().equals(servletPath)).count();
			if(count<=0){
				checkData.setResponseResult(new ResponseResult(SystemConstant.NO_AUTHORY_CODE,"没有权限",null));
			}
			LoginController.setCache(tokenData, valueOperations);
			checkData.setCheckFlag(true);
			checkData.setTokenData(tokenData);
		} catch (Exception e) {
			checkData.setResponseResult(new ResponseResult(SystemConstant.INTERNAL_ERROR_CODE,"服务器内部异常",null));
		}
		return checkData;
	}

}
