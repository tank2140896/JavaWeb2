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
		Role role = new Role();
		role.setRoleName("角色_1");
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/pc/sys/role/add",objectMapper.writeValueAsString(role),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testRoleList() throws Exception {
		RoleListRequest roleListRequest = new RoleListRequest();
		roleListRequest.setRoleName("_1");
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/pc/sys/role/list",objectMapper.writeValueAsString(roleListRequest),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testRoleModify() throws Exception {
		Role role = new Role();
		role.setRoleId("20190413204536637");
		role.setRoleName("角色_2");
		role.setDelFlag(1);
		String out = HttpUtil.defaultPutRequest(URL_PREFIX+"/web/pc/sys/role/modify",objectMapper.writeValueAsString(role),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testRoleDetail() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/pc/sys/role/detail/20190413204536637",getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testRoleDelete() throws Exception {
		String out = HttpUtil.defaultDeleteRequest(URL_PREFIX+"/web/pc/sys/role/delete/20190413204536637",null,getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testRoleModuleAssignment() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("20190408140043147");
		list.add("20190408140608316");
		list.add("20190408142132292");
		list.add("20190408142112268");
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/pc/sys/role/roleModuleAssignment/20190413204536637",objectMapper.writeValueAsString(list),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testRoleModuleInfo() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/pc/sys/role/roleModuleInfo/20190413204536637",getHeaders());
		System.out.println(out);
	}
	
}
