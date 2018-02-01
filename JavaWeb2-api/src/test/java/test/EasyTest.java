package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javaweb.constant.SystemConstant;
import com.javaweb.web.dao.ds1.UserDao;
import com.javaweb.web.po.User;

@ComponentScan(basePackages={SystemConstant.BASE_PACKAGE}) 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=EasyTest.class)
@WebAppConfiguration
//@Transactional
public class EasyTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void test() throws Exception {
		List<User> list = userDao.selectAll();
		list.stream().forEach(i->System.out.println(i.getUserId()));
	}

}
