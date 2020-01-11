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
    
    @GetMapping("/test")
    public String index() {
        System.out.println(discoveryClient.getInstances("eureka-client-user"));
        System.out.println("---------------------------------------------");
        System.out.println(eurekaClient.getInstancesByVipAddress("eureka-client-user",false));
        return "test";
    }
    
	@Value("${login.kaptcha.check}")
	private boolean loginKaptchaCheck;//在前后端分离模式下，后端不推荐进行验证码生成、校验，验证码生成、校验更推荐由前端处理

	@ApiOperation(value=SwaggerConstant.SWAGGER_LOGIN,notes=SwaggerConstant.SWAGGER_LOGIN_NOTES)
    @ApiImplicitParam(name="userLoginRequest",value=SwaggerConstant.SWAGGER_LOGIN_PARAM,required=true,dataType="UserLoginRequest")
	@PostMapping(ApiConstant.WEB_LOGIN)
	public BaseResponseResult login(@RequestBody @Validated UserLoginRequest userLoginRequest,BindingResult bindingResult/*,HttpServletRequest request*/){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}
		if(loginKaptchaCheck&&kaptchaCheck(userLoginRequest,request)){//验证码校验
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,"login.user.kaptcha");
		}
		TokenData token = null;
		if(systemAdminCheck(userLoginRequest)){//管理员判断
			userLoginRequest.setType("0");
			User user = SystemConstant.SYSTEM_DEFAULT_USER;
			token = getToken(true,user,userLoginRequest.getType());
			String key = String.join(CommonConstant.COMMA,user.getUserId(),userLoginRequest.getType());
			setDefaultDataToRedis(key,token);
		}else {
		    try{
	            userLoginRequest.setPassword(SecretUtil.getSecret(userLoginRequest.getPassword(),"SHA-256"));
	        }catch(Exception e){
	            //do nothing
	        }
	        User user = userService.userLogin(userLoginRequest);
	        if(user==null){
	            return getBaseResponseResult(HttpCodeEnum.LOGIN_FAIL,"login.user.userNameOrPassword");
	        }
	        user.setPassword(null);
	        token = getToken(false,user,userLoginRequest.getType());
	        String key = String.join(CommonConstant.COMMA,user.getUserId(),userLoginRequest.getType());
	        setDefaultDataToRedis(key,token);
		}
		//这里我个人认为redis中包含权限信息，但是前端不需要获得太多权限信息，权限信息可以通过其它接口获得
		TokenData returnTokenData = new TokenData();
		returnTokenData.setToken(token.getToken());
		returnTokenData.setUser(token.getUser());
		returnTokenData.setType(token.getType());
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.loginSuccess",returnTokenData/*token*/);
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
		return SecretUtil.createJwtToken(map,null,SystemConstant.PROJECT_NAME,SystemConstant.DEFAULT_SECURITY_KEY);
	}
	
	@ApiOperation(value=SwaggerConstant.SWAGGER_GET_KAPTCHA)
	@GetMapping(value=ApiConstant.WEB_KAPTCHA)
	public void getKaptcha(/*HttpServletRequest request,HttpServletResponse response,*/@PathVariable(name="requestId",required=true) String requestId) throws Exception {
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
	
	/* -------------------------------------------------- 分界线 -------------------------------------------------- */
	
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
		List<Module> menuList = moduleList.stream().filter(i->2==i.getModuleType()).collect(Collectors.toList());//获得菜单列表
		List<Module> menuListForTree = moduleList.stream().filter(i->1==i.getModuleType()||2==i.getModuleType()).collect(Collectors.toList());//获得目录、菜单列表
		List<Module> authOperateList = moduleList.stream().filter(i->3==i.getModuleType()).collect(Collectors.toList());//获得操作权限列表
		tokenData.setMenuList(menuList);//目前其实只需要用到：moduleName、pageUrl、icon
		tokenData.setMenuListForTree(setTreeList(menuListForTree,null));//目前其实只需要用到：moduleName、pageUrl、icon
		tokenData.setAuthOperateList(authOperateList);//目前其实只需要用到：apiUrl、alias
		return tokenData;
	}
	
	//封装成树形结构集合(递归版)
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
	
	//封装成树形结构集合(非递归版)
    @SuppressWarnings("unused")
    private List<Module> setTreeList(List<Module> list){
        List<List<Module>> deepList = getEachDeep(list);
        for(int i=deepList.size()-1;i>0;i--){
            List<Module> childs = deepList.get(i);
            List<Module> parrents = deepList.get(i-1);
            //将子类归属于父类
            for(int j=0;j<parrents.size();j++){
                Module parentModule = parrents.get(j);
                for(int k=0;k<childs.size();k++){
                    Module childModule = childs.get(k);
                    if(parentModule.getModuleId().equals(childModule.getParentId())){
                        List<Module> parentsList = parentModule.getList();
                        parentsList.add(childModule);
                        parentModule.setList(parentsList);
                        childs.remove(k);
                        k--;
                    }
                }
                parrents.set(j,parentModule);
            }
            deepList.set(i-1, parrents);
        }
        return deepList.get(0);
    }
	    
    private List<List<Module>> getEachDeep(List<Module> list){
        List<List<Module>> arrayList = new ArrayList<>();//定义一个深度集合
        int deep = 0;//深度
        for(int i=0;i<list.size();){
            Module module = list.get(i);
            if(module.getParentId()==null){//第一层(顶层)
                List<Module> first = new ArrayList<>();
                first.add(module);
                arrayList.add(first);
                deep++;
                list.remove(i);
                i=0;
            }else{//非第一层(非顶层)
                if(deep-1<0){
                    continue;
                }
                List<Module> noFirst = new ArrayList<>();
                List<Module> upper = arrayList.get(deep-1);//获得上一层
                for(int j=0;j<upper.size();j++){
                    Module upperModule = upper.get(j);
                    for(int k=0;k<list.size();k++){
                        Module restEachModule = list.get(k);
                        if(upperModule.getModuleId().equals(restEachModule.getParentId())){
                            noFirst.add(restEachModule);
                            list.remove(k);
                            k--;//这里不是k=0
                            i=0;
                        }
                    }
                }
                arrayList.add(noFirst);
                deep++;
            }
        }
        //deep:由于数组下标是从0开始的,因此要获得深度,最终需要deep+1,才是我们理解的深度值
        return arrayList;
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

