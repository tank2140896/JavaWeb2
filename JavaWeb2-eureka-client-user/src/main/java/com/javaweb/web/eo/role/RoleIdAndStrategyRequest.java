package com.javaweb.web.eo.role;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleIdAndStrategyRequest implements Serializable {

	private static final long serialVersionUID = -507429746720199113L;

	private String roleId;
    
    private Integer moduleStrategy;
    
    private Integer dataStrategy;
    
}
