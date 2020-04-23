package com.javaweb.web.eo.role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleIdAndStrategyRequest {

    private String roleId;
    
    private Integer moduleStrategy;
    
    private Integer dataStrategy;
    
}
