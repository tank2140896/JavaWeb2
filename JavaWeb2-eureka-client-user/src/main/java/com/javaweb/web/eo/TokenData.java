package com.javaweb.web.eo;

import java.io.Serializable;
import java.util.List;

import com.javaweb.web.po.Module;
import com.javaweb.web.po.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenData implements Serializable {
	
	private static final long serialVersionUID = -6256223897799749383L;

	private String token;
	
	private User user;
	
	private String type;
	
	private List<Module> moduleList;
	
	private List<Module> menuList;
	
	private List<Module> menuListForTree;
	
	private List<Module> authOperateList;
	
}
