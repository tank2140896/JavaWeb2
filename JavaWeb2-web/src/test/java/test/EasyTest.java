package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javaweb.service.rbac.UserService;

//@SpringApplicationConfiguration(classes=EasyTest.class)
@SpringBootConfiguration
@SpringBootApplication
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@MapperScan("com.javaweb.dao")
//@Transactional
public class EasyTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void test(){
		userService.getUserByUserId("65a85f1b-1209-11e7-9162-00ffaea60ab9");
	}

}
