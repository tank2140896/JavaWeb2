# JavaWeb2 V1.0         
# 一、环境依赖                                                       
1、jdk8                          
2、maven3.3+                 
3、redis4.0.8+                 
4、mysql5.6+                 
5、lombok     
6、nodejs              
# 二、项目依赖关系                                                                 
JavaWeb2-common                  
JavaWeb2-integration-demo->JavaWeb2-common                                                                      
JavaWeb2-eureka-server（要起3个，端口1001、1002、1003）                                                
JavaWeb2-eureka-client-user（端口2001）->JavaWeb2-common                                                                                                                       
JavaWeb2-eureka-client-zuul（端口3001）->JavaWeb2-common             
JavaWeb2-angular（cnpm install->cnpm start）              
因此，要正确完成JavaWeb2-eureka-client-user的编译需要先编译JavaWeb2-common                       
当新建一个纯净的项目引入JavaWeb2-common只需要排除默认数据源配置即可：@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})                           
# 三、启动顺序                                                       
1、JavaWeb2-eureka-server（要起3个，端口1001、1002、1003）                          
2、JavaWeb2-eureka-client-user（端口2001）、JavaWeb2-eureka-client-zuul（端口3001）          
3、JavaWeb2-angular（端口4200）     
注：与Angular整合看效果的话只要导入JavaWeb2-eureka-client-user/src/main/resources/sql/init.sql，然后修改JavaWeb2-eureka-client-user的pom.xml注释掉spring-cloud-starter-netflix-eureka-client和spring-cloud-starter-openfeign，然后加入spring-boot-starter-aop，待编译完成好后，注释掉报错代码即可，页面访问：localhost:4200，账号和密码都是admin123456     
# 四、使用前重要说明                                                                     
1、由于水平有限，本项目会存在很多不足，同时本项目更新会比较频繁、改动会比较大，请见谅（更新代码时也请同步更新JavaWeb2-eureka-client-user/src/main/resources/sql/init.sql）                 
2、本项目由于是前后端分离，因此没有使用session，而是使用redis替代session，同时也没有使用spring-session-redis，而是自己代码处理的                          
3、后端几乎所有的增删改查操作都是物理操作（忽略数据库表中的del_flag字段），如果想做逻辑操作（使用数据库表中的del_flag字段）需要重写相关SQL语句                        
4、本项目所有接口都可以用JUnit或Postman验证接口，JUnit代码也有部分可做参考                                       
5、JavaWeb2-integration-demo包括了一些常用技术与SpringBoot组合的示例（hbase、kafka、netty、solr、切面处理、mongodb、websocket（SpringBoot版和Netty版）、elasticsearch、neo4j、barcode（一维条形码）、kaptcha（二维码）、邮件发送、redis、restTemplate、quartz（分布式）定时任务、文件上传下载、activiti、qq、wechat、poi-excel、HTTPS和HTTP2.0、本项目作为第三方的接入（Oauth2）、接口对接（对方请求我们的数据）、中文分词器、轻量级文件服务器minio、FastDFS、SpringBatch批处理、JNI）                                                                        
6、所有SQL语句均未优化且没有索引等，如有需要请自行优化                          
7、所有增删改查操作几乎没有进行任何校验，如有需要请自行优化                    
8、本项目所有主键都是字符串，而不是数字或自增，主要考虑到两方面：①分布式及数据迁移；②当数字类型超过16位时，前后端交互时，前端会有精度误差，当然前端转化下或后端转化下就可以了，这里主要是为了避免相互转换的麻烦                    
9、注册中心有很多，比如eureka、zookeeper、consul、nacos等，本项目在这方面不是重点，只想快速上手，因此就用了以前的官方套餐                  
10、tomcat容器顺序：Filter->Servlet->Inteceptor->Aspect->Controller               
# 五、开发计划                                                                                                         
1、持续优化迭代各已有功能（首要目标是重写算法功能）                                                              
2、hadoop、spark、storm等大数据技术                                                                
3、容器技术                                     
4、将zuul变为gateway并加入SpringCloudConfig、SpringCloudSleuth、SpringBootAdmin、SpringSecurityOAuth2JWT                                            
5、后端一部分将采用JAVA15+、函数式、异步编程（包括WebFlux/RxJava2/Netty） 等                
6、将JAVA算法改用C语言实现，JAVA本地调用C语言的实现方法                
# 六、项目主要特点                                                       
1、微服务                          
2、前后端分离                 
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
11、数据权限：本项目的数据权限全部围绕接口展开的，使用时在Controller的方法上加上@DataPermission(entity=UserListResponse.class)，默认/web开头的接口都是权限受控的，参考：UserController中的userList                                          
12、代码自动生成                             
# 七、项目使用说明                                                       
1、除了涉及事务的service加上@Transactional，建议涉及事务的Controller也加上@Transactional，另外不建议try、catch，除非能确保无数据库相关事务操作或确保Controller内的方法不会抛出异常；另外自定义异常如果继承Exception的话，方法需要显式抛出异常，而如果继承RuntimeException，方法不需要显式抛出异常                 
2、关于@Configuration的类，配置代码常用的有两种写法，一是通过常量类配置，二是通过配置文件配置，若不想该配置类生效，可以加上如：@ConditionalOnProperty(name="http.server.on",havingValue="true")                                         
3、src/main/resources下的静态资源文件在SpringBoot中的默认(未添加拦截器等情况下)查找顺序为:META/resources->resources->static->public                 
4、读取配置文件一般有以下三种常用写法                 
A、@Autowired private Environment environment                 
B、@Value("${server.port}") private String port                 
C、@ConfigurationProperties(prefix="com.demo")                 
   @Component                 
   public class Test{                 
      ......                 
   }                 
   @Autowired private Test test           
5、指定读取配置文件的几种常用方式           
A、java -jar demo.jar --spring.config.location=/tmp/test/application.properties           
B、java -jar demo.jar --spring.profiles.active=dev          
C、java -jar demo.jar然后在同一目录下建一个config文件夹，里面放个配置文件：application.properties              
如果读取war包的话参考JavaWeb2-integration-demo里的pom.xml                                                         
# 八、后期侧重点                                                                           
数学、算法、人工智能、机器学习、编程语言底层实现                                        
# 九、其它       
1、https://start.spring.io                       
2、https://spring.io/projects/spring-cloud                          
3、https://angular.io/start          
