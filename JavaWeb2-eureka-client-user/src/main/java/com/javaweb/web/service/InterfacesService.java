package com.javaweb.web.service;

import com.javaweb.util.entity.Page;
import com.javaweb.web.eo.interfaces.InterfacesListRequest;
import com.javaweb.web.po.Interfaces;

public interface InterfacesService {
	
	public void synchronizedInterfaces();
	
	public Page interfacesList(InterfacesListRequest interfacesListRequest);
	
	public Interfaces interfacesDetail(String interfacesId);
	
	public void interfacesModify(Interfaces interfaces);

}
