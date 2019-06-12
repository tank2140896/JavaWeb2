package com.javaweb.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SwaggerConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.MathUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.user.UserLoginRequest;
import com.javaweb.web.po.Module;
import com.javaweb.web.po.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags=SwaggerConstant.SWAGGER_ALL_OPEN_CONTROLLER_TAGS)
@RestController
public class AllOpenController extends BaseController {

	@ApiOperation(value=SwaggerConstant.SWAGGER_LOGIN_VALUE,notes=SwaggerConstant.SWAGGER_LOGIN_NOTES)
    @ApiImplicitParam(name="userLoginRequest",value=SwaggerConstant.SWAGGER_LOGIN_PARAM_VALUE,required=true,dataType="UserLoginRequest")
	@PostMapping(ApiConstant.LOGIN)
	public BaseResponseResult login(@RequestBody @Validated UserLoginRequest userLoginRequest,BindingResult bindingResult,HttpServletRequest request){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}
		//if(kaptchaCheck(userLoginRequest,request)){//验证码校验
		//	return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,"login.user.kaptcha");
		//}
		if(systemAdminCheck(userLoginRequest)){//管理员判断
			User user = SystemConstant.SYSTEM_DEFAULT_USER;
			TokenData token = getToken(true,user,userLoginRequest.getType());
			String key = String.join(CommonConstant.COMMA,user.getUserId(),userLoginRequest.getType());
			setDefaultDataToRedis(key,token);
			return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.loginSuccess",token);
		}
		try{userLoginRequest.setPassword(SecretUtil.getSecret(userLoginRequest.getPassword(),"SHA-256"));}catch(Exception e){}
		User user = userService.userLogin(userLoginRequest);
		if(user==null){
			return getBaseResponseResult(HttpCodeEnum.LOGIN_FAIL,"login.user.userNameOrPassword");
		}
		user.setPassword(null);
		TokenData token = getToken(false,user,userLoginRequest.getType());
		String key = String.join(CommonConstant.COMMA,user.getUserId(),userLoginRequest.getType());
		setDefaultDataToRedis(key,token);
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.loginSuccess",token);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_REQUEST_PARAMETER_LOST_VALUE)
	@RequestMapping(value=ApiConstant.REQUEST_PARAMETER_LOST,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult requestParameterLost() {
		return getBaseResponseResult(HttpCodeEnum.REQUEST_PARAMETER_LOST,"validated.permission.requestParameterLost");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_INVALID_REQUEST_VALUE)
	@RequestMapping(value=ApiConstant.INVALID_REQUEST,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult invalidRequest(){
		return getBaseResponseResult(HttpCodeEnum.INVALID_REQUEST,"validated.permission.invalidRequest");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_REQUEST_PARAMETER_ERROR_VALUE)
	@RequestMapping(value=ApiConstant.REQUEST_PARAMETER_ERROR,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult requestParameterError(){
		return getBaseResponseResult(HttpCodeEnum.REQUEST_PARAMETER_ERROR,"validated.permission.requestParameterError");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_NO_AUTHORY_VALUE)
	@RequestMapping(value=ApiConstant.NO_AUTHORY,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult noAuthory(){
		return getBaseResponseResult(HttpCodeEnum.NO_AUTHORY,"validated.permission.noAuthory");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_NOT_FOUND_VALUE)
	@RequestMapping(value=ApiConstant.NOT_FOUND,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult notFound(){
		return getBaseResponseResult(HttpCodeEnum.NOT_FOUND,"validated.permission.notFound");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_INTERNAL_ERROR_VALUE)
	@RequestMapping(value=ApiConstant.INTERNAL_ERROR,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult internalError(){
		return getBaseResponseResult(HttpCodeEnum.INTERNAL_ERROR,"validated.permission.internalError");
	}
	
	/*
	//验证码
	@GetMapping("/kaptcha/{uuid}")
	public void kaptcha(HttpServletRequest request,HttpServletResponse response,@PathVariable(name="uuid",required=true) String uuid) throws Exception {
		response.setHeader("Cache-Control", "no-store, no-cache");
	    response.setContentType("image/jpeg");
	    String text = defaultKaptcha.createText();
	    String sessionId = request.getSession().getId();
	    if(sessionId==null){
	    	sessionId = uuid;
	    }
	    setDataToRedis(sessionId,text,SystemConstant.SYSTEM_DEFAULT_KAPTCHA_TIME_OUT,TimeUnit.MINUTES);
	    BufferedImage image = defaultKaptcha.createImage(text);
	    ServletOutputStream out = response.getOutputStream();
	    ImageIO.write(image,"jpg",out);
	}
	*/
	
	//对token进行简单加密
	private String secretToken(String token) {
		String date = DateUtil.getStringDate(DateUtil.DATETIME_PATTERN_TYPE2);
		String tempArray[] = new String[token.length()];
		for(int i=0;i<tempArray.length;i++){
			tempArray[i] = String.valueOf(token.charAt(i));
		}
		for(int i=0;i<date.length();i++){
			String str = String.valueOf(date.charAt(i));//获得日期字符串的每位数字
			int m = i;
			int n = Integer.parseInt(str);
			str = tempArray[m];
			tempArray[m] = tempArray[n];
			tempArray[n] = str;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<tempArray.length;i++){
			sb.append(tempArray[i]);
		}
		String out = sb.toString();
		out = out.replaceAll(CommonConstant.BAR,CommonConstant.EMPTY_VALUE).toUpperCase()+MathUtil.getRandomNumForLCRC(token.length()*date.length());
		return out;
	}
	
	//管理员判断
	private boolean systemAdminCheck(UserLoginRequest userLoginRequest){
		final String systemAdminUsernameAndPassword = SystemConstant.SYSTEM_DEFAULT_USER_NAME+SystemConstant.SYSTEM_DEFAULT_USER_PASSWORD;
		final String requestUsernameAndPassword = userLoginRequest.getUsername()+userLoginRequest.getPassword();
		//final String requestType = userLoginRequest.getType();
		return (systemAdminUsernameAndPassword.equals(requestUsernameAndPassword))/*&&("0".equals(requestType))*/;
	}
	
	//token数据封装
	private TokenData getToken(boolean adminFlag,User user,String type){
		List<Module> moduleList = moduleService.getModule(adminFlag,user.getUserId());
		TokenData tokenData = new TokenData();
		tokenData.setToken(secretToken(UUID.randomUUID().toString()));
		tokenData.setUser(user);
		tokenData.setType(type);
		List<Module> menuList = moduleList.stream().filter(i->1==i.getModuleType()).collect(Collectors.toList());//获得菜单列表
		List<Module> authOperateList = moduleList.stream().filter(i->2==i.getModuleType()).collect(Collectors.toList());//获得操作权限列表
		tokenData.setMenuListForTree(setTreeList(menuList,null));
		tokenData.setAuthOperateList(authOperateList);
		return tokenData;
	}
	
	//封装成树形结构集合
	private List<Module> setTreeList(List<Module> originList,Module module){
		List<Module> moduleList = new ArrayList<>();
		for (int i = 0; i < originList.size(); i++) {
			Module currentModule = originList.get(i);
			if((module!=null&&module.getModuleId().equals(currentModule.getParentId()))||(module==null&&currentModule.getParentId()==null)){
				currentModule.setList(setTreeList(originList, currentModule));
				moduleList.add(currentModule);
			}
		}
		return moduleList;
	}
	
	/*
	//验证码校验
	protected boolean kaptchaCheck(UserLoginRequest userLogin,HttpServletRequest request){
		boolean result = true;
		String sessionId = request.getSession().getId();
	    if(sessionId==null){
	    	sessionId = userLogin.getUuid();
	    }
		String kaptcha = (String)getDateFromRedis(sessionId);
		if(kaptcha!=null){
			if(kaptcha.equalsIgnoreCase(userLogin.getKaptcha())){//忽略大小写
				result = false;
			}
		}
		return result;
	}
	*/
	
}