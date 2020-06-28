package com.javaweb.web.docking;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogServerApiEntity implements Serializable{
    
	private static final long serialVersionUID = 9207742483995429223L;

	private String username;
    
    private String password;

}
