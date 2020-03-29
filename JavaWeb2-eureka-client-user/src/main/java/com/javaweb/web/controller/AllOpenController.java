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
    
	//登录接口
	@ApiOperation(value=SwaggerConstant.SWAGGER_LOGIN,notes=SwaggerConstant.SWAGGER_LOGIN_NOTES)
    @ApiImplicitParam(name="userLoginRequest",value=SwaggerConstant.SWAGGER_LOGIN_PARAM,required=true,dataType="UserLoginRequest")
	@PostMapping(ApiConstant.WEB_LOGIN)
	public BaseResponseResult webLogin(@RequestBody @Validated UserLoginRequest userLoginRequest,BindingResult bindingResult,HttpServletRequest request){
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}
		//还可以进行验证码校验等处理
		TokenData tokenData = null;
		if(systemAdminCheck(userLoginRequest)){//管理员判断
			userLoginRequest.setType("0");
			User user = SystemConstant.SYSTEM_DEFAULT_USER;
			tokenData = getToken(true,user,userLoginRequest.getType());
			setDefaultDataToRedis(user.getUserId()+CommonConstant.COMMA+userLoginRequest.getType(),tokenData);
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
	        tokenData = getToken(false,user,userLoginRequest.getType());
	        setDefaultDataToRedis(user.getUserId()+CommonConstant.COMMA+userLoginRequest.getType(),tokenData);
		}
		//这里我个人认为redis中包含权限信息，但是前端不需要获得太多权限信息，权限信息可以通过其它接口获得
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.loginSuccess",tokenData.getToken()/*tokenData.getToken()*/);
	}
	
	/* -------------------------------------------------- 分界线 -------------------------------------------------- */
	
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
	
	/* -------------------------------------------------- 分界线 -------------------------------------------------- */
	
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
		String token = SecretUtil.secretTokenForEasyWay(UUID.randomUUID().toString(),true);
		try {
			token = SecretUtil.base64EncoderString(token+CommonConstant.COMMA+user.getUserId()+CommonConstant.COMMA+type,"UTF-8");
		} catch (Exception e) {
			//do nothing
		}
		tokenData.setToken(token);
		tokenData.setUser(user);
		tokenData.setType(type);
		List<Module> menuList = moduleList.stream().filter(i->2==i.getModuleType()).collect(Collectors.toList());//获得菜单列表
		List<Module> menuListForTree = moduleList.stream().filter(i->1==i.getModuleType()||2==i.getModuleType()).collect(Collectors.toList());//获得目录、菜单列表并封装成树型结构
		List<Module> authOperateList = moduleList.stream().filter(i->3==i.getModuleType()).collect(Collectors.toList());//获得操作权限列表
		tokenData.setMenuList(menuList);
		tokenData.setMenuListForTree(setTreeList(menuListForTree,null));//主要用到：moduleName、pageUrl、icon
		tokenData.setAuthOperateList(authOperateList);//主要用到：apiUrl、alias
		return tokenData;
	}
	
	//封装成树形结构集合(递归版)
	private List<Module> setTreeList(List<Module> originList,Module module){
		List<Module> moduleList = new ArrayList<>();
		for (int i = 0; i < originList.size(); i++) {
			Module currentModule = originList.get(i);
			//String类型写法
			if((module!=null&&module.getModuleId().equals(currentModule.getParentId()))||(module==null&&currentModule.getParentId()==null)){
			//Long类型写法
			//if(((module!=null)&&(module.getModuleId()==currentModule.getParentId()))||((module==null)&&(currentModule.getParentId()==null||currentModule.getParentId()==0))){
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
	
}
