一、项目主要知识点
1、前后端分离【后端只提供接口】
2、多数据源【采用mybatis时要注意不同数据源对应的dao接口名称不要一样，比如可以一个叫dao1一个叫dao2）；也可使用不同数据源的JdbcTemplate，注意@Qualifier标注使用的数据源，然后直接使用如： mysql_d1_JdbcTemplate.query("select * from user where id = ?",new Object[]{"1"},new BeanPropertyRowMapper<User>(User.class))】
3、国际化
4、接口入参校验
5、reids
6、mybatis共通dao
7、kafka【kafka生产者（producer）一般可以使用kafkaTemplate；kafka消费者（consumer）一般可以使用@KafkaListener；实际项目中生产者和消费者一般都是分开的】
8、Netty客户端和服务端示例
9、websocket
二、项目使用说明
1、除了涉及事务的service加上@Transactional，建议涉及事务的Controller也加上@Transactional，另外不建议try catch，除非能确保无数据库相关事务操作或确保Controller内的方法不会抛出异常
2、关于@Configuration的类，配置代码常用的有两种写法，一是通过常量类配置，二是通过配置文件配置
3、原始JDBC
Class.forName(driveClassName);
Connection connection = DriverManager.getConnection(url,userName,password);
PreparedStatement preparedStatement = connection.prepareStatement(sql);
4、src/main/resources下的静态资源文件在SpringBoot中的默认(未添加拦截器等情况下)查找顺序为:META/resources->resources->static->public
三、其它
1、https://start.spring.io