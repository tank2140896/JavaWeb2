package com.javaweb.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaweb.base.BaseController;

@RestController
@PropertySource({"classpath:application-dev.properties"})
public class TestController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${logging.level.root}")
	private String a;
	
	@Autowired
	private PropertiesBean propertiesBean;
	
	@GetMapping("/a")
	public Map<String,String> a() throws Exception{
		logger.info("接口被调用了");
		Map<String,String> map = new HashMap<>();
		map.put("a",a);
		map.put("b",propertiesBean.getAaa());
		return map;
	}
	
	@GetMapping("/x")
	public Map<String,String> x(//@RequestHeader(name="h") String h,
								@RequestParam(required=false,name="sfzh",defaultValue="a") String sfzh,
			                    @RequestParam(required=true,name="sjhm") String sjhm){
		Map<String,String> map = new HashMap<>();
		map.put("sfzh",sfzh);
		map.put("sjhm",sjhm);
		return map;
	}
	
	@GetMapping("/y/{x}")
	public Map<String,String> y(@PathVariable(required=false) String x){
		Map<String,String> map = new HashMap<>();
		return map;
	}
	
	@GetMapping("/z")
	public User z(){
		User u = new User();
		//User u = new Puser();
		u.setUserName("a");
		u.setAge(18);
		return u;
	}
	
}

class User{
	//@JsonIgnore
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",locale="zh",timezone="GMT+8")
	//@JsonInclude(Include.NON_NULL)
	//@JsonProperty
	private String userName;
	
	@JsonProperty("num")
	private int age;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}

class Puser extends User{
	@JsonIgnore
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}

@Component
@PropertySource({"classpath:application.properties"})
@ConfigurationProperties/*(prefix="spring")//spring.devtools.restart.enabled可以变为devtools.restart.enabled;另外写了prefix就不写@Value了*/
class PropertiesBean {
	
	//如果不写@Value那就直接寻找配置文件的aaa
	@Value("${spring.devtools.restart.enabled}")
	private String aaa;
	
	//如果不写@Value那就直接寻找配置文件的bbb
	@Value("${spring.messages.basename}")
	private String bbb;

	public String getAaa() {
		return aaa;
	}

	public void setAaa(String aaa) {
		this.aaa = aaa;
	}

	public String getBbb() {
		return bbb;
	}

	public void setBbb(String bbb) {
		this.bbb = bbb;
	}
	
}
