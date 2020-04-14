package com.javaweb.base;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.javaweb.task.TaskService;
import com.javaweb.web.service.DictionaryService;
import com.javaweb.web.service.InterfacesService;
import com.javaweb.web.service.ModuleService;
import com.javaweb.web.service.OperationLogService;
import com.javaweb.web.service.RoleService;
import com.javaweb.web.service.ScheduleService;
import com.javaweb.web.service.UserService;

public class BaseController extends BaseTool {
	
	private static boolean initStartFlag = true;
	
	@PostConstruct
	final public synchronized void init(){
		if(initStartFlag){
			initStartFlag = false;
			BaseSystemMemory.dictionaryList = dictionaryService.selectAll();//将字典表数据加载进内存
			interfacesService.synchronizedInterfaces();//同步数据库中的接口信息表
		}
	}
    
    @Autowired
    protected DictionaryService dictionaryService;
    
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected RoleService roleService;
	
	@Autowired
	protected ModuleService moduleService;
	
	@Autowired  
	protected OperationLogService operationLogService;
	
	@Autowired  
	protected ScheduleService scheduleService;
	
	@Autowired  
	protected TaskService taskService;
	
	@Autowired  
	protected InterfacesService interfacesService;
	
}
