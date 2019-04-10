package controller;

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
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/pc/sys/user/list",objectMapper.writeValueAsString(userListRequest),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserModify() throws Exception {
		User user = new User();
		user.setUserId("20190410145326334");
		user.setUserName("username_11");
		user.setPersonName("用户11");
		String out = HttpUtil.defaultPutRequest(URL_PREFIX+"/web/pc/sys/user/modify",objectMapper.writeValueAsString(user),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserDetail() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/pc/sys/user/detail/20190410145326334",getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserDelete() throws Exception {
		String out = HttpUtil.defaultDeleteRequest(URL_PREFIX+"/web/pc/sys/user/delete/20190410145326334",null,getHeaders());
		System.out.println(out);
	}
	
}
