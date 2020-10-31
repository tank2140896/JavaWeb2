package test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.javaweb.Application;
import com.javaweb.web.dao.ds1.UserDao;
import com.javaweb.web.po.User;

/*
@ComponentScan(basePackages={SystemConstant.BASE_PACKAGE}) 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=EasyTest.class)
@WebAppConfiguration
@Transactional
@AutoConfigureMockMvc//private MockMvc mockMvc//类似Http客户端
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})
public class EasyTest {
	
	@Autowired
	private UserDao userDao;
	
	@Before
	public void before(){
		System.out.println("It's before");
	}
	
	@Test
	public void test() throws Exception {
		List<User> list = userDao.selectAll();
		list.stream().forEach(i->System.out.println(i.getUserId()));
	}
	
	@After
	public void after(){
		System.out.println("It's after");
	}

}