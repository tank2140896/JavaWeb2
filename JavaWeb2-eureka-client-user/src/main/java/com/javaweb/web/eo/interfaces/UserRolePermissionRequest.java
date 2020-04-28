package com.javaweb.web.eo.interfaces;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRolePermissionRequest implements Serializable {

	private static final long serialVersionUID = 8502645489040187193L;

	private List<UserPermissionResponse> userPermissionResponseList;
	
	private List<RolePermissionResponse> rolePermissionResponseList;

}
