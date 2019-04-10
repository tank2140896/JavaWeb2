package controller;

import org.junit.Test;

import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.HttpUtil;
import com.javaweb.web.eo.user.UserLoginRequest;

public class AllOpenControllerTest extends BaseControllerTest {
	
	@Test
	public void testLogin() throws Exception {
		UserLoginRequest userLoginRequest = new UserLoginRequest();
		userLoginRequest.setUsername(SystemConstant.ADMIN);
		userLoginRequest.setPassword(SystemConstant.ADMIN);
		userLoginRequest.setType("0");
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/login",objectMapper.writeValueAsString(userLoginRequest),null);
		System.out.println(out);
	}

}
