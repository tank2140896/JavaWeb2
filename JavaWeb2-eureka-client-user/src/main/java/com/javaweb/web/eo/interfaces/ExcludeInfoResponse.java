package com.javaweb.web.eo.interfaces;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcludeInfoResponse implements Serializable {
	
	private static final long serialVersionUID = 3136601161004663224L;

	private String excludeField;
	
	private String url;

}
