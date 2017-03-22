package com.javaweb.conf.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.factory.annotation.Autowired;

import com.javaweb.dataobject.po.User;
import com.javaweb.service.rbac.UserService;

public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;

	//验证权限
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		/**
		SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
		Long userId = user.getUserId();
		List<String> permsList = null;
		//系统管理员，拥有最高权限
		if(userId == 1){
			List<SysMenuEntity> menuList = sysMenuService.queryList(new HashMap<String, Object>());
			permsList = new ArrayList<>(menuList.size());
			for(SysMenuEntity menu : menuList){
				permsList.add(menu.getPerms());
			}
		}else{
			permsList = sysUserService.queryAllPerms(userId);
		}
		//用户权限列表
		Set<String> permsSet = new HashSet<String>();
		for(String perms : permsList){
			if(StringUtils.isBlank(perms)){
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
		*/
		//返回null的话,就会导致任何用户访问被拦截的请求时,都会自动跳转到unauthorizedUrl指定的地址
		//return null;
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();  
		simpleAuthorizationInfo.addRole("admin");  
		//simpleAuthorizationInfo.addStringPermission("see");
        return simpleAuthorizationInfo;  
	}

	//登录时的认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String username = (String)authenticationToken.getPrincipal();//得到用户名 
	    String password = new String((char[])authenticationToken.getCredentials());//得到密码
	    User user = userService.getUserByUserName(username);
	    if(user==null){
	    	throw new UnknownAccountException("账号或密码不正确");
	    }
	    if(!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        if(user.getStatus() != 0){
        	throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
    	return new SimpleAuthenticationInfo(username, password, getName());
	}

}
