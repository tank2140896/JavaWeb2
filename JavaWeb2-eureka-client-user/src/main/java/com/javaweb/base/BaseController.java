package com.javaweb.base;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.javaweb.task.TaskService;
import com.javaweb.web.service.DictionaryService;
import com.javaweb.web.service.ModuleService;
import com.javaweb.web.service.OperationLogService;
import com.javaweb.web.service.RoleService;
import com.javaweb.web.service.ScheduleService;
import com.javaweb.web.service.UserService;

public class BaseController extends BaseTool {
	
	private static boolean initStartFlag = true;
	
	@PostConstruct
	public final void init(){
		if(initStartFlag){
			BaseSystemMemory.dictionaryList = dictionaryService.selectAll();//将字典表数据加载进内存
			initStartFlag = false;
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
	
}
