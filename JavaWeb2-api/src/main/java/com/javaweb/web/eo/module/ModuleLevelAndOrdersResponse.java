package com.javaweb.web.eo.module;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleLevelAndOrdersResponse implements Serializable {
	
	private static final long serialVersionUID = -4595018633238807903L;

	private Integer level;
	
	private Integer orders;
	
}
