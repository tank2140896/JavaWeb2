package com.javaweb.web.service;

import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.interfaces.InterfacesListRequest;

public interface InterfacesService {
	
	public void synchronizedInterfaces();
	
	public Page interfacesList(InterfacesListRequest interfacesListRequest);

}
