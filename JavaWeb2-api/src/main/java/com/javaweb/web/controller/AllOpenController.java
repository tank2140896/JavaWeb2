package com.javaweb.web.controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.javaweb.util.core.StringUtil;
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
	
	@Value("${login.kaptcha.check}")
	private boolean loginKaptchaCheck;

	@ApiOperation(value=SwaggerConstant.SWAGGER_LOGIN,notes=SwaggerConstant.SWAGGER_LOGIN_NOTES)
    @ApiImplicitParam(name="userLoginRequest",value=SwaggerConstant.SWAGGER_LOGIN_PARAM,required=true,dataType="UserLoginRequest")
	@PostMapping(ApiConstant.WEB_LOGIN)
	public BaseResponseResult login(@RequestBody @Validated UserLoginRequest userLoginRequest,BindingResult bindingResult,HttpServletRequest request){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}
		if(loginKaptchaCheck&&kaptchaCheck(userLoginRequest,request)){//验证码校验
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,"login.user.kaptcha");
		}
		if(systemAdminCheck(userLoginRequest)){//管理员判断
			userLoginRequest.setType("0");
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
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_REQUEST_PARAMETER_LOST)
	@RequestMapping(value=ApiConstant.REQUEST_PARAMETER_LOST,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult requestParameterLost() {
		return getBaseResponseResult(HttpCodeEnum.REQUEST_PARAMETER_LOST,"validated.permission.requestParameterLost");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_INVALID_REQUEST)
	@RequestMapping(value=ApiConstant.INVALID_REQUEST,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult invalidRequest(){
		return getBaseResponseResult(HttpCodeEnum.INVALID_REQUEST,"validated.permission.invalidRequest");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_REQUEST_PARAMETER_ERROR)
	@RequestMapping(value=ApiConstant.REQUEST_PARAMETER_ERROR,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult requestParameterError(){
		return getBaseResponseResult(HttpCodeEnum.REQUEST_PARAMETER_ERROR,"validated.permission.requestParameterError");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_NO_AUTHORY)
	@RequestMapping(value=ApiConstant.NO_AUTHORY,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult noAuthory(){
		return getBaseResponseResult(HttpCodeEnum.NO_AUTHORY,"validated.permission.noAuthory");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_NOT_FOUND)
	@RequestMapping(value=ApiConstant.NOT_FOUND,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult notFound(){
		return getBaseResponseResult(HttpCodeEnum.NOT_FOUND,"validated.permission.notFound");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_INTERNAL_ERROR)
	@RequestMapping(value=ApiConstant.INTERNAL_ERROR,method={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	public BaseResponseResult internalError(){
		return getBaseResponseResult(HttpCodeEnum.INTERNAL_ERROR,"validated.permission.internalError");
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_GET_REQUEST_ID,notes=SwaggerConstant.SWAGGER_GET_REQUEST_ID_NOTES)
	@GetMapping(value=ApiConstant.WEB_REQUESTID)
	public String getRequestId() {
		Map<String,Object> map = new HashMap<>();
		map.put("key",defaultKaptcha.createText());
		map.put("date",DateUtil.getStringDate(DateUtil.DATETIME_PATTERN_TYPE1));
		return SecretUtil.createJwtToken(map,null);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_GET_KAPTCHA)
	@GetMapping(value=ApiConstant.WEB_KAPTCHA)
	public void getKaptcha(HttpServletRequest request,HttpServletResponse response,@PathVariable(name="requestId",required=true) String requestId) throws Exception {
		response.setHeader("Cache-Control","no-store,no-cache");
	    response.setContentType("image/jpeg");
	    String text = defaultKaptcha.createText();
	    try {
	    	if(loginKaptchaCheck&&!CommonConstant.EMPTY_VALUE.equals(StringUtil.handleNullString(text))) {
	    		setDataToRedis(requestId,text,SystemConstant.SYSTEM_DEFAULT_KAPTCHA_TIME_OUT,TimeUnit.MINUTES);
	    	}
	    }catch (Exception e) {
			//do nothing
		}
	    BufferedImage image = defaultKaptcha.createImage(text);
	    ServletOutputStream out = response.getOutputStream();
	    ImageIO.write(image,"jpg",out);
	}
	
	//对token进行简单加密
	private String secretToken(String token,String date,boolean random) {
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
		if(random) {
			out = out.replaceAll(CommonConstant.BAR,CommonConstant.EMPTY_VALUE).toUpperCase()+MathUtil.getRandomNumForLCRC(token.length()*date.length());
		}else {
			out = out.replaceAll(CommonConstant.BAR,CommonConstant.EMPTY_VALUE).toUpperCase();
		}
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
		tokenData.setToken(secretToken(UUID.randomUUID().toString(),DateUtil.getStringDate(DateUtil.DATETIME_PATTERN_TYPE2),true));
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
	
	//验证码校验
	private boolean kaptchaCheck(UserLoginRequest userLogin,HttpServletRequest request){
		boolean result = true;
		String kaptcha = (String)getDateFromRedis(userLogin.getRequestId());
		if(kaptcha!=null){
			if(kaptcha.equalsIgnoreCase(userLogin.getKaptcha())){//忽略大小写
				result = false;
			}
		}
		return result;
	}
	
}