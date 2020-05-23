package controller;

import org.junit.Test;

import com.javaweb.constant.ApiConstant;
import com.javaweb.util.core.HttpUtil;
import com.javaweb.web.eo.user.UserListRequest;

public class UserControllerTest extends BaseControllerTest {
	
	@Test
	public void testUserList() throws Exception {
		UserListRequest userListRequest = new UserListRequest();
		userListRequest.setUserName("username_");
		userListRequest.setCurrentPage(1L);
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+ApiConstant.WEB_USER_PREFIX+ApiConstant.USER_LIST,objectMapper.writeValueAsString(userListRequest),getHeaders());
		System.out.println(out);
	}
	
}
