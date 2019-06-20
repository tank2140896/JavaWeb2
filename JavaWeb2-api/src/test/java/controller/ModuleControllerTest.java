package controller;

import org.junit.Test;

import com.javaweb.util.core.HttpUtil;
import com.javaweb.web.eo.module.ModuleListRequest;
import com.javaweb.web.po.Module;

public class ModuleControllerTest extends BaseControllerTest {
	
	@Test
	public void testModuleAdd() throws Exception {
		Module module = new Module();
		module.setModuleName("模块_1");
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/sys/module/add",objectMapper.writeValueAsString(module),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testModuleList() throws Exception {
		ModuleListRequest moduleListRequest = new ModuleListRequest();
		moduleListRequest.setModuleName("_1");
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/sys/module/list",objectMapper.writeValueAsString(moduleListRequest),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testModuleModify() throws Exception {
		Module module = new Module();
		module.setModuleId("20190413194626736");
		module.setModuleName("模块_2");
		module.setDelFlag(1);
		String out = HttpUtil.defaultPutRequest(URL_PREFIX+"/web/sys/module/modify",objectMapper.writeValueAsString(module),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testModuleDetail() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/sys/module/detail/20190408141702729",getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testModuleDelete() throws Exception {
		String out = HttpUtil.defaultDeleteRequest(URL_PREFIX+"/web/sys/module/delete/20190413194626736",null,getHeaders());
		System.out.println(out);
	}
	
}
