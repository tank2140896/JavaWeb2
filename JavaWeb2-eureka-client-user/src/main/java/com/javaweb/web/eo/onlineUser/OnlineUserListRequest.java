package com.javaweb.web.eo.onlineUser;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnlineUserListRequest implements Serializable {
	
	private static final long serialVersionUID = -3414850279981979366L;

	private String userName;//用户名
	
	private Long currentPage = 1L;//默认当前第1页
	
	private Long pageSize = 10L;//默认每页显示10条

}
