package controller;

import org.junit.Test;

import com.javaweb.util.core.HttpUtil;

public class LoginAccessControllerTest extends BaseControllerTest {
	
	@Test
	public void testGetRedisTokenData() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/loginAccess/getRedisTokenData",getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testGetDictionary() throws Exception {
	    String body = "{\"dataType\":\"module_type\"}";
	    String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/loginAccess/getDictionary",body,getHeaders());
        System.out.println(out);
	}
	
	@Test
	public void testLogout() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/loginAccess/logout",getHeaders());
		System.out.println(out);
	}
	
}
