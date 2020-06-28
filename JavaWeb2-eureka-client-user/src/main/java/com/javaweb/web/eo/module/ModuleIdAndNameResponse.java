package com.javaweb.web.eo.module;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleIdAndNameResponse implements Serializable {
    
	private static final long serialVersionUID = -663493355165859584L;

	private String moduleId;
    
    private String moduleName;

}
