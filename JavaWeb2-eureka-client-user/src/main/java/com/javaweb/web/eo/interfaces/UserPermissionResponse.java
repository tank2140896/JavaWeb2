package com.javaweb.web.eo.interfaces;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPermissionResponse implements Serializable {

	private static final long serialVersionUID = -4705449792689725700L;
	
	private String userId;
	
	private String userName;
	
	private String excludeField;

}
