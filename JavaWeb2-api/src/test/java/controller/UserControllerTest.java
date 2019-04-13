package controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.javaweb.util.core.HttpUtil;
import com.javaweb.web.eo.user.UserListRequest;
import com.javaweb.web.po.User;

public class UserControllerTest extends BaseControllerTest {
	
	@Test
	public void testUserAdd() throws Exception {
		User user = new User();
		user.setUserName("username_1");
		user.setPassword("password_1");
		user.setPersonName("用户1");
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/pc/sys/user/add",objectMapper.writeValueAsString(user),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserList() throws Exception {
		UserListRequest userListRequest = new UserListRequest();
		userListRequest.setUserName("_1");
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/pc/sys/user/list",objectMapper.writeValueAsString(userListRequest),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserModify() throws Exception {
		User user = new User();
		user.setUserId("20190413214407588");
		user.setUserName("username_1");
		user.setPersonName("用户2");
		String out = HttpUtil.defaultPutRequest(URL_PREFIX+"/web/pc/sys/user/modify",objectMapper.writeValueAsString(user),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserDetail() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/pc/sys/user/detail/20190413215101793",getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserDelete() throws Exception {
		String out = HttpUtil.defaultDeleteRequest(URL_PREFIX+"/web/pc/sys/user/delete/20190413214407588",null,getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserRoleInfo() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/pc/sys/user/userRoleInfo/20190413215101793",getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserRoleAssignment() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("20190413204536637");
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/pc/sys/user/userRoleAssignment/20190413215101793",objectMapper.writeValueAsString(list),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserModuleInfo() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/pc/sys/user/userModuleInfo/20190413215101793",getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserModuleAssignment() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("20190408142112268");
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/pc/sys/user/userModuleAssignment/20190413215101793",objectMapper.writeValueAsString(list),getHeaders());
		System.out.println(out);
	}
	
}
