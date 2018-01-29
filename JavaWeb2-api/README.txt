一、项目主要知识点：
1、前后端分离（后端只提供接口）
	A、完全前后端分离（前后端session跨域/spring-session-redis/session用redis做）
	B、伪前后端分离（前后端session跨域/jsp之类）
	注：spring-session-redis配置
	   <dependency>
	       <groupId>org.springframework.boot</groupId>
	       <artifactId>spring-boot-starter-data-redis</artifactId>
	   </dependency>
	   <dependency>
	       <groupId>org.springframework.session</groupId>
	       <artifactId>spring-session-data-redis</artifactId>
	   </dependency>
	   @EnableRedisHttpSession(maxInactiveIntervalInSeconds=900)//设置session15分钟过期
	   spring.session.store-type=Redis
2、多数据源（采用mybatis时要注意不同数据源对应的dao接口名称不要一样，比如可以一个叫dao1一个叫dao2）
3、国际化
4、接口入参校验
5、reids
	A、bind 0.0.0.0 设置允许访问的ip
	B、requirepass 123456 设置访问密码
	C、protected-mode no 关闭保护模式
6、mybatis共通dao（还需完善、优化、增强）
二、项目使用说明：
1、除了涉及事务的service加上@Transactional，建议涉及事务的Controller也加上@Transactional，另外不建议try catch，除非能确保无数据库相关事务操作或确保Controller内的方法不会抛出异常
2、验证码可以使用kaptcha
<dependency>
	<groupId>com.github.penggle</groupId>
	<artifactId>kaptcha</artifactId>
</dependency>
@Autowired
private DefaultKaptcha defaultKaptcha;	
@GetMapping("/kaptcha")
public void captcha(HttpServletResponse response)throws Exception {
    response.setHeader("Cache-Control", "no-store, no-cache");
    response.setContentType("image/jpeg");
    String text = defaultKaptcha.createText();//生成文字验证码
    BufferedImage image = defaultKaptcha.createImage(text);//生成图片验证码
    ServletOutputStream out = response.getOutputStream();
    ImageIO.write(image,"jpg",out);
}





