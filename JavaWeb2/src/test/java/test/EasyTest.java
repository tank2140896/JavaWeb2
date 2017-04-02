package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javaweb.dataobject.po.User;
import com.javaweb.service.rbac.UserService;

@ComponentScan(basePackages={"com.javaweb"}) 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=EasyTest.class)
@WebAppConfiguration
//@Transactional
public class EasyTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void test() throws Exception {
		userService.createUser(new User());
	}

}
