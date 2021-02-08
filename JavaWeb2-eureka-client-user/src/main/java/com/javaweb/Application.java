package com.javaweb;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude={
		DataSourceAutoConfiguration.class,//去除数据源默认配置
		RedisAutoConfiguration.class,RedisRepositoriesAutoConfiguration.class//去除redis默认配置
})
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages={"com.javaweb"})
@EnableScheduling//开启定时任务
@EnableAsync//开启异步任务
//@MapperScan("com.javaweb.web.dao")//这样写就不必在所有的dao接口上加@Mapper
public class Application {
	
    public static void main(String[] args) {
        //System.setProperty("es.set.netty.runtime.available.processors","false");//为了避免elasticsearch依赖的netty与netty冲突 
        //new MyCommandLineRunner();
        //SpringApplication.run(Application.class,args);//最简单的启动写法
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setBannerMode(Mode.OFF);
        springApplication.run(args);
    }

}

/**
//也可以有多个类实现CommandLineRunner，多个类都写上@Component即可，若要定义顺序，加上@Order(value=1)即可，value值越高越优先执行
@Component
class MyCommandLineRunner implements CommandLineRunner {
  public void run(String... args) {
      System.setProperty("spring.devtools.restart.enabled","false");//取消热部署
  }
}
*/