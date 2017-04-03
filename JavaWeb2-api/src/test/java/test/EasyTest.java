package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javaweb.dao.rbac.UserDao;

@ComponentScan(basePackages={"com.javaweb"}) 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=EasyTest.class)
@WebAppConfiguration
//@Transactional
public class EasyTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void test() throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("userName", "unkonow");
		map.put("startDate", null);
		map.put("endDate", null);
		map.put("currentStart", 1);
		map.put("currentSize", 5);
		List<List<?>> list = userDao.listUser(map);
		System.out.println(list);
	}

}
