package com.javaweb.web.eo.role;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleInfoResponse implements Serializable {
	
	private static final long serialVersionUID = 2026162447864735033L;

	private Boolean checkFlag;
	
	private String moduleName;
	
	private String moduleId;
	
	private String parentId;
	
	private List<ModuleInfoResponse> list;

}
