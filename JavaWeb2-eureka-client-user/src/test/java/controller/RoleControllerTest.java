package controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.javaweb.util.core.HttpUtil;
import com.javaweb.web.eo.role.RoleListRequest;
import com.javaweb.web.po.Role;

public class RoleControllerTest extends BaseControllerTest {
	
	@Test
	public void testRoleAdd() throws Exception {
		for(int i=0;i<11;i++){
			Role role = new Role();
			role.setRoleName("角色_"+i);
			String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/sys/role/add",objectMapper.writeValueAsString(role),getHeaders());
			System.out.println(out);
		}
	}
	
	@Test
	public void testRoleList() throws Exception {
		RoleListRequest roleListRequest = new RoleListRequest();
		roleListRequest.setRoleName("角色");
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/sys/role/list",objectMapper.writeValueAsString(roleListRequest),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testRoleModify() throws Exception {
		Role role = new Role();
		role.setRoleId("202003281400199551");
		role.setRoleName("10角色");
		String out = HttpUtil.defaultPutRequest(URL_PREFIX+"/web/sys/role/modify",objectMapper.writeValueAsString(role),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testRoleDetail() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/sys/role/detail/202003281400199551",getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testRoleDelete() throws Exception {
		String out = HttpUtil.defaultDeleteRequest(URL_PREFIX+"/web/sys/role/delete/202003281400199551",null,getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testRoleModuleAssignment() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("20190408140043147");
		list.add("20190408140608316");
		list.add("20190408142132292");
		list.add("20190408142112268");
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/sys/role/roleModuleAssignment/20190413204536637",objectMapper.writeValueAsString(list),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testRoleModuleInfo() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/sys/role/roleModuleInfo/20190413204536637",getHeaders());
		System.out.println(out);
	}
	
}
