package controller;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.constant.SystemConstant;
import com.javaweb.util.core.HttpUtil;
import com.javaweb.web.eo.user.UserLoginRequest;

public class AllOpenControllerTest {
	
	public final String URL_PREFIX = "http://localhost:8888/javaweb-web";
	
	@Test
	public void testLogin() throws Exception {
		UserLoginRequest userLoginRequest = new UserLoginRequest();
		userLoginRequest.setUsername(SystemConstant.ADMIN);
		userLoginRequest.setPassword(SystemConstant.ADMIN);
		userLoginRequest.setType("1");
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/login",new ObjectMapper().writeValueAsString(userLoginRequest));
		System.out.println(out);
	}

}
