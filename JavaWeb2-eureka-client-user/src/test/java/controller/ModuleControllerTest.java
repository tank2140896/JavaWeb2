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
		module.setParentId("20190408140608316");
		module.setParentName("我不会被保存到数据库的");
		module.setModuleType(1);
		module.setType(1);
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/sys/module/add",objectMapper.writeValueAsString(module),getHeaders());
		System.out.println(out);
	}
    
    @Test
    public void testGetModuleIdAndNameList() throws Exception {
        String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/sys/module/getModuleIdAndNameList/3",getHeaders());
        System.out.println(out);
    }
	
    @Test
    public void testModuleModify() throws Exception {
        Module module = new Module();
        module.setModuleId("20190707125317682");
        module.setModuleName("模块_修改");
        String out = HttpUtil.defaultPutRequest(URL_PREFIX+"/web/sys/module/modify",objectMapper.writeValueAsString(module),getHeaders());
        System.out.println(out);
    }
	
	@Test
	public void testModuleList() throws Exception {
		ModuleListRequest moduleListRequest = new ModuleListRequest();
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/sys/module/list",objectMapper.writeValueAsString(moduleListRequest),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testModuleDetail() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/sys/module/detail/202003281408434951",getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testModuleDelete() throws Exception {
		String out = HttpUtil.defaultDeleteRequest(URL_PREFIX+"/web/sys/module/delete/202003281408434951",null,getHeaders());
		System.out.println(out);
	}
	
}
