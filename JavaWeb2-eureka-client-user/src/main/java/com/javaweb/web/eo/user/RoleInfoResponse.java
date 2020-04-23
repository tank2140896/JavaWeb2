package com.javaweb.web.eo.user;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleInfoResponse implements Serializable {
	
	private static final long serialVersionUID = 6623363971665601199L;

	private String roleId;
	
	private String roleName;
	
	private Boolean checkFlag;//false表示未被选中；true表示被选中
	
	private Integer moduleStrategy;
	
	private Integer dataStrategy;

}
