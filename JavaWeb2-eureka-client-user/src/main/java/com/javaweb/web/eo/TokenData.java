package com.javaweb.web.eo;

import java.io.Serializable;
import java.util.List;

import com.javaweb.web.eo.interfaces.ExcludeInfoResponse;
import com.javaweb.web.eo.module.SidebarInfoResponse;
import com.javaweb.web.po.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenData implements Serializable {
	
	private static final long serialVersionUID = -6256223897799749383L;

	private String token;
	
	private User user;
	
	private Integer clientType;

	private Integer loginWay;

	private List<String> pageUrlList;
	
	private List<String> apiUrlList;
	
	private List<SidebarInfoResponse> menuListForTree;
	
	private String rsaPublicKeyOfBackend;//后端进行响应加密时会用到
	
	private String rsaPrivateKeyOfBackend;//前端进行响应解密时会用到
	
	private String rsaPublicKeyOfFrontend;//前端进行请求加密时会用到
	
	private String rsaPrivateKeyOfFrontend;//后端进行请求解密时会用到
	
	private List<ExcludeInfoResponse> excludeInfoResponseList;
	
}
