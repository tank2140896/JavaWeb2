package controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.javaweb.util.core.HttpUtil;
import com.javaweb.web.eo.role.RoleIdAndStrategyRequest;
import com.javaweb.web.eo.user.UserListRequest;
import com.javaweb.web.po.User;

public class UserControllerTest extends BaseControllerTest {
	
	@Test
	public void testUserAdd() throws Exception {
		for(int i=0;i<11;i++){
			User user = new User();
			user.setUserName("username_"+i);
			user.setPassword("password_"+i);
			user.setPersonName("用户"+i);
			String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/sys/user/add",objectMapper.writeValueAsString(user),getHeaders());
			System.out.println(out);
		}
	}
	
	@Test
	public void testUserList() throws Exception {
		UserListRequest userListRequest = new UserListRequest();
		userListRequest.setUserName("username_");
		userListRequest.setCurrentPage(2L);
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/sys/user/list",objectMapper.writeValueAsString(userListRequest),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserModify() throws Exception {
		User user = new User();
		user.setUserId("202003281354003631");
		user.setUserName("10_username");
		user.setPersonName("10用户");
		String out = HttpUtil.defaultPutRequest(URL_PREFIX+"/web/sys/user/modify",objectMapper.writeValueAsString(user),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserDetail() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/sys/user/detail/202003281354003631",getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserRoleAssignment() throws Exception {
		List<RoleIdAndStrategyRequest> list = new ArrayList<>();
		RoleIdAndStrategyRequest roleIdAndStrategyRequest = new RoleIdAndStrategyRequest();
		roleIdAndStrategyRequest.setRoleId("202003281400197771");
		roleIdAndStrategyRequest.setModuleStrategy(1);
		roleIdAndStrategyRequest.setDataStrategy(1);
		list.add(roleIdAndStrategyRequest);
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/sys/user/userRoleAssignment/202003281354003631",objectMapper.writeValueAsString(list),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserRoleInfo() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/sys/user/userRoleInfo/202003281354003631",getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserModuleAssignment() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("202003281408434951");
		String out = HttpUtil.defaultPostRequest(URL_PREFIX+"/web/sys/user/userModuleAssignment/202003281354003631",objectMapper.writeValueAsString(list),getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserModuleInfo() throws Exception {
		String out = HttpUtil.defaultGetRequest(URL_PREFIX+"/web/sys/user/userModuleInfo/202003281354003631",getHeaders());
		System.out.println(out);
	}
	
	@Test
	public void testUserDelete() throws Exception {
		String out = HttpUtil.defaultDeleteRequest(URL_PREFIX+"/web/sys/user/delete/202003281354003631",null,getHeaders());
		System.out.println(out);
	}
	
}
