package com.javaweb.config.activiti;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
插件地址：https://github.com/Activiti/Activiti-Designer/releases
第一次安装若失败解压复制过去再安装一遍即可
*/
@Configuration
public class ActivitiConfig {
	
    //@Autowired
    //private ProcessEngine processEngine;//流程引擎的抽象，可以通过此类获取需要的所有服务
	
    /** activiti强行整合SpringSecurity start */
    @Bean
    public UserDetailsService myUserDetailsService() {

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

        //用户
        String[][] usersGroupsAndRoles = {
                {"张三", "password", "ROLE_ACTIVITI_USER"},
                {"李四", "password", "ROLE_ACTIVITI_ADMIN"},
                {"王五", "password", "ROLE_ACTIVITI_USER"},
                {"钱七", "password", "ROLE_ACTIVITI_ADMIN"},
        };
        for (String[] user : usersGroupsAndRoles) {
            List<String> authoritiesStrings = Arrays.asList(Arrays.copyOfRange(user, 2, user.length));
            inMemoryUserDetailsManager.createUser(new User(user[0], passwordEncoder().encode(user[1]),authoritiesStrings.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList())));
        }
        return inMemoryUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /** activiti强行整合SpringSecurity end */
    
    //流程运行过程中，每个任务节点的相关操作接口，如complete,delete,delegate等
    @Bean
    public TaskService taskService(ProcessEngine processEngine){
        return processEngine.getTaskService();
    }

    //流程定义和部署相关的存储服务
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine){
        return processEngine.getRepositoryService();
    }
    
	//流程运行时相关的服务，如根据流程好启动流程实例startProcessInstanceByKey
    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine){
        return processEngine.getRuntimeService();
    }

    //历史记录相关服务接口
    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

}
