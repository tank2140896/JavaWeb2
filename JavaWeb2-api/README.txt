一、项目主要知识点：
1、前后端分离（后端只提供接口）
方式：前后端session跨域、spring-session-redis（自动档）、redis-session（手动档）
spring-session-redis配置：
	spring-boot-starter-data-redis
	spring-session-data-redis
	@EnableRedisHttpSession(maxInactiveIntervalInSeconds=900)//设置session15分钟过期
	spring.session.store-type=Redis
2、多数据源（采用mybatis时要注意不同数据源对应的dao接口名称不要一样，比如可以一个叫dao1一个叫dao2）
3、国际化
4、接口入参校验
5、reids
6、mybatis共通dao
7、kafka
kafka生产者（producer）一般可以使用kafkaTemplate；kafka消费者（consumer）一般可以使用@KafkaListener；实际项目中生产者和消费者一般都是分开的
二、项目使用说明：
1、除了涉及事务的service加上@Transactional，建议涉及事务的Controller也加上@Transactional，另外不建议try catch，除非能确保无数据库相关事务操作或确保Controller内的方法不会抛出异常
2、关于@Configuration的类，配置代码常用的有两种写法，一是通过常量类配置，二是通过配置文件配置