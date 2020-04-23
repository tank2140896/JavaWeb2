package com.javaweb.web.eo.interfaces;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePermissionResponse implements Serializable {

	private static final long serialVersionUID = -4705449792689725700L;
	
	private String roleId;
	
	private String roleName;
	
	private String excludeField;

}
