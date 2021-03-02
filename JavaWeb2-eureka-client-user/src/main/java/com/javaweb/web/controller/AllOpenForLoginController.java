package com.javaweb.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.annotation.url.ControllerMethod;
import com.javaweb.base.BaseController;
import com.javaweb.base.BaseResponseResult;
import com.javaweb.constant.ApiConstant;
import com.javaweb.constant.CommonConstant;
import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.HttpCodeEnum;
import com.javaweb.util.core.AesDesUtil;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.MathUtil;
import com.javaweb.util.core.ObjectOperateUtil;
import com.javaweb.util.core.PatternUtil;
import com.javaweb.util.core.RsaUtil;
import com.javaweb.util.core.SecretUtil;
import com.javaweb.util.entity.RsaKey;
import com.javaweb.web.eo.TokenData;
import com.javaweb.web.eo.interfaces.ExcludeInfoResponse;
import com.javaweb.web.eo.module.SidebarInfoResponse;
import com.javaweb.web.eo.user.UserLoginRequest;
import com.javaweb.web.po.Module;
import com.javaweb.web.po.User;

//无需登录即可访问模块
@RestController
public class AllOpenForLoginController extends BaseController {
	
	@PostMapping(ApiConstant.WEB_LOGIN)
	@ControllerMethod(interfaceName="用户登录接口")
	public BaseResponseResult webLogin(@RequestBody @Validated UserLoginRequest userLoginRequest,BindingResult bindingResult,HttpServletRequest request){
		//Part1：参数常规校验
		if(bindingResult.hasErrors()){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,bindingResult);
		}
		//Part2：密码特殊处理
		String password = decodePassword(userLoginRequest.getPassword(),userLoginRequest.getTime());
		if(CommonConstant.EMPTY_VALUE.equals(password)){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,"validated.user.password.error");
		}
		if(!PatternUtil.isPattern(password,Pattern.compile(UserLoginRequest.PASSWORD_REGEXP))){
			return getBaseResponseResult(HttpCodeEnum.VALIDATE_ERROR,"validated.user.password.pattern");
		}
		userLoginRequest.setPassword(password);
		//Part3：参数业务逻辑校验（此处暂无，可参考其它Controller写法）
		//Part4：获取用户信息
		boolean isAdmin = systemAdminCheck(userLoginRequest);
		User user = getUser(userLoginRequest,isAdmin);
		if(user==null){
            return getBaseResponseResult(HttpCodeEnum.LOGIN_FAIL,"login.user.userNameOrPassword");
        }
        if(user.getStatus()==1){
        	return getBaseResponseResult(HttpCodeEnum.LOGIN_FAIL,"login.user.userLocked");
        }
        user.setPassword(CommonConstant.NULL_VALUE);//用户密码不对外提供
        //Part5：tokenData设置
        TokenData tokenData = getToken(isAdmin,user,userLoginRequest);
        /** 当前登录方式下相同账号密码的后者会把前者踢下去 */
		setDefaultDataToRedis(user.getUserId()+CommonConstant.COMMA+userLoginRequest.getClientType()+CommonConstant.COMMA+userLoginRequest.getLoginWay(),tokenData);//key值组成：userId,clientType,loginWay
		return getBaseResponseResult(HttpCodeEnum.SUCCESS,"login.user.loginSuccess",tokenData.getToken());//这里我个人认为redis中包含权限信息，但是前端不需要获得太多权限信息，权限信息可以通过其它接口获得
	}
	
	/* -------------------------------------------------- 分界线 -------------------------------------------------- */

	//解码密码
    public String decodePassword(String password,String time){
		String out = CommonConstant.EMPTY_VALUE;
    	try{
			char[] charArray = password.toCharArray();
			int one,another;char tmp;
			for(int i=time.length()-1;i>=0;){
				one = Integer.parseInt(String.valueOf(time.charAt(i--)));
				another = Integer.parseInt(String.valueOf(time.charAt(i--)));
				tmp = charArray[one];
				charArray[one] = charArray[another];
				charArray[another] = tmp;
			}
			out = SecretUtil.base64DecoderString(String.valueOf(charArray),"UTF-8");
			int position = (int)(Long.parseLong(time)%2);
			if(position==0){//偶数
				out = out.substring(0,out.length()-time.length());
			}else{//奇数
				out = out.substring(time.length(),out.length());
			}
		}catch(Exception e){
			//do nothing
		}
		return out;
	}
	
	//管理员判断
	private boolean systemAdminCheck(UserLoginRequest userLoginRequest){
		//管理员账号密码在本项目中是固定的，当然也可以配置在数据库中（个人建议写在代码中）
		final String systemAdminUsernameAndPassword = SystemConstant.SYSTEM_DEFAULT_USER_NAME+SystemConstant.SYSTEM_DEFAULT_USER_PASSWORD;
		final String requestUsernameAndPassword = userLoginRequest.getUsername()+userLoginRequest.getPassword();
		return (systemAdminUsernameAndPassword.equals(requestUsernameAndPassword))&&(1==userLoginRequest.getClientType())&&(1==userLoginRequest.getLoginWay());
	}
	
	//获取用户信息
	private User getUser(UserLoginRequest userLoginRequest,boolean isAdmin){
		User user = null;
		if(isAdmin){//管理员判断
			user = SystemConstant.SYSTEM_DEFAULT_USER;
		}else {
		    try{
		    	userLoginRequest.setPassword(SecretUtil.getSecret(userLoginRequest.getPassword(),"SHA-256"));
		    }catch(Exception e){
		    	//do nothing
		    }
	        user = userService.userLogin(userLoginRequest);
		}
		return user;
	}
	
	//token数据封装
	private TokenData getToken(boolean adminFlag,User user,UserLoginRequest userLoginRequest){
		List<Module> moduleList = moduleService.getModule(adminFlag,user.getUserId());
		TokenData tokenData = new TokenData();
		String token = getTokenForEasyWay(user.getUserId(),userLoginRequest.getClientType(),userLoginRequest.getLoginWay());
		tokenData.setToken(token);
		tokenData.setUser(user);
		tokenData.setClientType(userLoginRequest.getClientType());
		tokenData.setLoginWay(userLoginRequest.getLoginWay());
		List<String> pageUrlList = getPageUrlList(moduleList);//获得pageUrl列表
		List<String> apiUrlList = getApiUrlList(moduleList);//获得apiUrl列表
		tokenData.setPageUrlList(pageUrlList);
		tokenData.setApiUrlList(apiUrlList);
		setRsaKey(tokenData);
		List<Module> modules = moduleList.stream().filter(i->1==i.getModuleType()||2==i.getModuleType()).collect(Collectors.toList());//获得目录、菜单列表并封装成树型结构
		List<SidebarInfoResponse> menuListForTree = new ArrayList<>();
		menuListForTree = ObjectOperateUtil.copyListProperties(modules,SidebarInfoResponse.class);//主要用到：moduleName、pageUrl、icon
		menuListForTree = setTreeList(menuListForTree,null);//setTreeList(menuListForTree);
		tokenData.setMenuListForTree(menuListForTree);
		if(!adminFlag){
			//数据权限处理
			List<ExcludeInfoResponse> excludeInfoResponseList = interfacesService.getExcludeInfoResponseList(user.getUserId());
			tokenData.setExcludeInfoResponseList(excludeInfoResponseList);
		}
		return tokenData;
	}
	
	//简单获得token
	private String getTokenForEasyWay(String userId,Integer clientType,Integer loginWay) {
		String date = DateUtil.getStringDate(DateUtil.DATETIME_PATTERN_TYPE1);
		String randomNumber = String.valueOf(MathUtil.getRandomNumForLCRC(Integer.MAX_VALUE-1));
		String out = randomNumber + SecretUtil.getRandomUUID(false) + date;
		out = out + CommonConstant.COMMA + userId + CommonConstant.COMMA + clientType + CommonConstant.COMMA + loginWay;;
		out = AesDesUtil.encryptAes(out,SystemConstant.TOKEN_AES_KEY);
		try {
			out = SecretUtil.base64EncoderString(out,"UTF-8");
		} catch (Exception e) {
			//do nothing
		}
		return out;
	}
	
	//获得PAGE的URL列表
	public List<String> getPageUrlList(List<Module> moduleList){
		List<String> pageUrlList = new ArrayList<>();
		for(int i=0;i<moduleList.size();i++){
			String pageUrl = moduleList.get(i).getPageUrl();
			if((pageUrl!=null)&&(!CommonConstant.EMPTY_VALUE.equals(pageUrl))){
				pageUrlList.add(pageUrl);
			}
		}
		pageUrlList = pageUrlList.stream().distinct().collect(Collectors.toList());
		return pageUrlList;
	}
	
	//获得API的URL列表
	public List<String> getApiUrlList(List<Module> moduleList){
		List<String> apiUrlList = new ArrayList<>();
		for(int i=0;i<moduleList.size();i++){
			String apiUrl = moduleList.get(i).getApiUrl();
			if((apiUrl!=null)&&(!CommonConstant.EMPTY_VALUE.equals(apiUrl))){
				String apiUrls[] = apiUrl.split(CommonConstant.COMMA);//某一操作可能会调用多个附属操作（即API接口），多个附属操作约定用逗号分开
				for(String each:apiUrls){
					apiUrlList.add(each);
				}
			}
		}
		apiUrlList = apiUrlList.stream().distinct().collect(Collectors.toList());
		return apiUrlList;
	}
	
	//RSA秘钥设置
	public void setRsaKey(TokenData tokenData){
		RsaKey rsaKey = RsaUtil.getRsaKey();
		tokenData.setRsaPublicKeyOfBackend(rsaKey.getRsaStringPublicKey());
		tokenData.setRsaPrivateKeyOfBackend(rsaKey.getRsaStringPrivateKey());
		rsaKey = RsaUtil.getRsaKey();
		tokenData.setRsaPublicKeyOfFrontend(rsaKey.getRsaStringPublicKey());
		tokenData.setRsaPrivateKeyOfFrontend(rsaKey.getRsaStringPrivateKey());
	}
	
	//封装成树形结构集合（递归版）
	public List<SidebarInfoResponse> setTreeList(List<SidebarInfoResponse> originList,SidebarInfoResponse module){
		List<SidebarInfoResponse> moduleList = new ArrayList<>();
		for (int i = 0; i < originList.size(); i++) {
			SidebarInfoResponse currentModule = originList.get(i);
			//这里树形结构处理时需要parentId只能为null，不能为空或其它值（这个在模块新增和修改时已经控制了）
			//Long类型，封装类型一定要用equals或.longValue()比较！！！，形如：module.getModuleId().longValue()==currentModule.getParentId().longValue()
			if((module!=null&&module.getModuleId().equals(currentModule.getParentId()))||(module==null&&currentModule.getParentId()==null)){
				currentModule.setList(setTreeList(originList, currentModule));
				moduleList.add(currentModule);
			}
		}
		return moduleList;
	}
	/**
	//封装成树形结构集合（非递归版）
    public List<SidebarInfoResponse> setTreeList(List<SidebarInfoResponse> list){
        List<List<SidebarInfoResponse>> deepList = getEachDeep(list);
        for(int i=deepList.size()-1;i>0;i--){
            List<SidebarInfoResponse> childs = deepList.get(i);
            List<SidebarInfoResponse> parrents = deepList.get(i-1);
            //将子类归属于父类
            for(int j=0;j<parrents.size();j++){
            	SidebarInfoResponse parentModule = parrents.get(j);
                for(int k=0;k<childs.size();){
                	SidebarInfoResponse childModule = childs.get(k);
                    if(parentModule.getModuleId().equals(childModule.getParentId())){
                        List<SidebarInfoResponse> parentsList = parentModule.getList();
                        parentsList = (parentsList==null?new ArrayList<>():parentsList);
                        parentsList.add(childModule);
                        parentModule.setList(parentsList);
                        childs.remove(k);
                        k=0;
                    }else{
                    	k++;
                    }
                }
                parrents.set(j,parentModule);
            }
            deepList.set(i-1, parrents);
        }
        return deepList.get(0);
    }
	
    //归类每一层
    private List<List<SidebarInfoResponse>> getEachDeep(List<SidebarInfoResponse> list){
        List<List<SidebarInfoResponse>> arrayList = new ArrayList<>();//定义一个深度集合
        int deep = 0;//深度
        classifyFirstLevel(list,arrayList,deep);
        for(int i=0;i<list.size();){
        	deep++;
        	classifyNoFirstLevel(list,arrayList,deep);
        }
        return arrayList;
    }
    
    //归类第一层（deep=0）
    private void classifyFirstLevel(List<SidebarInfoResponse> list,List<List<SidebarInfoResponse>> arrayList,int deep){
        List<SidebarInfoResponse> first = new ArrayList<>();
        for(int i=0;i<list.size();){
        	SidebarInfoResponse each = list.get(i);
        	if(each.getParentId()==null){
        		first.add(each);
        		list.remove(each);
        		i=0;
        	}else{
        		i++;
        	}
        }
        arrayList.add(deep,first);
    }
    
    //归类非第一层（deep!=0）
    private void classifyNoFirstLevel(List<SidebarInfoResponse> list,List<List<SidebarInfoResponse>> arrayList,int deep){
    	List<SidebarInfoResponse> parentLevel = arrayList.get(deep-1);//上一层
    	List<SidebarInfoResponse> noFirst = new ArrayList<>();
    	for(int i=0;i<parentLevel.size();i++){
    		SidebarInfoResponse eachParentElement = parentLevel.get(i);
    		for(int j=0;j<list.size();){
            	SidebarInfoResponse each = list.get(j);
            	if(each.getParentId().equals(eachParentElement.getModuleId())){
            		noFirst.add(each);
            		list.remove(each);
            		i=0;
            	}else{
            		i++;
            	}
            }
    	}
    	arrayList.add(deep,noFirst);
    }
    */
	
}
