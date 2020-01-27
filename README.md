# 一、环境依赖                                                       
1、jdk8                 
2、maven3.3+                 
3、redis4.0.8+                 
4、mysql5.6+                 
5、lombok                 
# 二、项目依赖关系                                                                 
JavaWeb2-database->JavaWeb2-common                        
JavaWeb2-eureka-server-1（端口1001）                 
JavaWeb2-eureka-server-2（端口1002）                 
JavaWeb2-eureka-server-3（端口1003）                 
JavaWeb2-eureka-client-user（端口2001）->JavaWeb2-database                                                                    
JavaWeb2-eureka-client-log（端口2002）->JavaWeb2-database                                                            
JavaWeb2-eureka-client-zuul（端口3001）->JavaWeb2-database                                                     
因此，要正确完成JavaWeb2-eureka-client-user的编译需要先编译JavaWeb2-common再编译JavaWeb2-database                                  
# 三、启动顺序                                                       
1、JavaWeb2-eureka-server-1（端口1001）、JavaWeb2-eureka-server-2（端口1002）、JavaWeb2-eureka-server-3（端口1003）                                  
2、JavaWeb2-eureka-client-user（端口2001）、JavaWeb2-eureka-client-log（端口2002）、JavaWeb2-eureka-client-zuul（端口3001）                                  
# 四、使用前重要说明：                                                                     
1、本项目使用redis存储session，但是没用spring-session-redis（写法在代码中也有参考），而是采用自己设置session到redis                 
2、后端几乎所有的增删改查删除操作都是物理操作（忽略数据库表中的del_flag字段），如果想做逻辑操作（使用数据库表中的del_flag字段）需要重写相关SQL语句（目前虽然保留了逻辑删除字段，但是形同虚设）                 
3、本项目目前暂无前端实现，本项目目前全程采用Postman验证接口                 
4、jar包依赖细节参考JavaWeb2-eureka-client-user中的pom.xml               
# 五、后端近期开发计划：                                                       
1、完善、优化JavaWeb2-eureka-client-user的代码                 
2、接口测试管理（swagger2完善、优化）                 
3、文件上传下载                                 
4、websoket完善、优化                 
5、优化后端验证码的代码                 
6、后端一部分将采用JAVA13+及函数式编程（包括WebFlux/RxJava2）                 
7、将JAVA算法改用C语言实现，JAVA本地调用C语言的实现方法                 
8、将zuul变为gateway                 
9、加入SpringCloudConfig、SpringCloudSleuth、SpringBootAdmin、SpringSecurityOAuth2JWT                 
10、容器技术的加入                 
11、用户角色权限的完善（包括数据权限的处理）                 
12、尽量多的将东西提取到JavaWeb2-common和JavaWeb2-database中去                    
13、抽取单独的分布式定时任务                     
# 六、项目主要特点                                                       
1、微服务化                 
2、前后端分离(后端只提供接口)                 
3、多数据源                 
提供了三种方式接入：                 
第一种是包命名方式：采用mybatis时要注意不同数据源对应的dao接口名称不要一样，比如可以一个叫UserDao一个叫UserDao2,如果一样会报：Specified class is an interface                 
第二种是注解的方式：采用mybatis时在接口标注的@Mapper上加上如@DataSource(value="ds1")即可                 
第三种是JdbcTemplate的方式：采用@Qualifier标注使用的数据源，然后直接使用如： mysql_d1_JdbcTemplate.query("select * from user where id = ?",new Object[]{"1"},new BeanPropertyRowMapper<User>(User.class))                 
4、国际化                 
5、接口入参校验                 
6、reids                 
7、mybatis共通dao(位于项目JavaWeb2-database)                 
8、kafka【kafka生产者（producer）一般可以使用kafkaTemplate；kafka消费者（consumer）一般可以使用@KafkaListener；实际项目中生产者和消费者一般都是分开的】                 
9、Netty客户端和服务端示例（IO模型、数据协议、线程模型）                 
10、websocket                 
# 七、项目使用说明                                                       
1、除了涉及事务的service加上@Transactional，建议涉及事务的Controller也加上@Transactional，另外不建议try、catch，除非能确保无数据库相关事务操作或确保Controller内的方法不会抛出异常                 
2、关于@Configuration的类，配置代码常用的有两种写法，一是通过常量类配置，二是通过配置文件配置                 
3、原始JDBC                 
Class.forName(driveClassName);                 
Connection connection = DriverManager.getConnection(url,userName,password);                 
PreparedStatement preparedStatement = connection.prepareStatement(sql);                 
4、src/main/resources下的静态资源文件在SpringBoot中的默认(未添加拦截器等情况下)查找顺序为:META/resources->resources->static->public                 
5、读取配置文件一般有以下三种常用写法                 
A、@Autowired private Environment environment                 
B、@Value("${server.port}") private String port                 
C、@ConfigurationProperties(prefix="com.demo")                 
   @Component                 
   public class Test{                 
      ......                 
   }                 
   @Autowired private Test test                 
# 八、其它                                                       
1、https://start.spring.io                 
2、https://spring.io/projects/spring-cloud                 