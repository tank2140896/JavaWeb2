package com.javaweb.config.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.base.BaseResponseResult;

@RestController
public class ActivitiController {
	
    @Autowired
    private ProcessEngine processEngine;//流程引擎的抽象，可以通过此类获取需要的所有服务
    
    //部署流程定义
    @GetMapping("/activiti/create")
    public BaseResponseResult activitiCreate() {
    	Deployment deployment = processEngine.getRepositoryService().createDeployment()//创建一个部署对象
								                 .name("测试申请流程")
								                 .addClasspathResource("activiti/ActivitiDemoProcess.bpmn")
								                 //.addClasspathResource("processes/myProcess.png")
								                 .deploy();
        System.out.println("部署ID："+deployment.getId());//e6a8f55e-8f66-11ea-89af-509a4c33f08d
        System.out.println("部署名称："+deployment.getName());//测试申请流程
		return new BaseResponseResult(200,"success",null);
    }
    
    //启动流程实例分配任务给个人
    @GetMapping("/activiti/user")
    public BaseResponseResult activitiUser() {
        String processDefinitionKey = "ActivitiDemoProcess";//每一个流程有对应的一个key这个是某一个流程内固定的写在bpmn内的
        Map<String, Object> variables=new HashMap<String,Object>();
		variables.put("assignee1","202004041609538011,202004041601405691");//指定执行人有哪些    
		variables.put("assignee2","202004041601405691");//指定执行人有哪些    
		variables.put("assignee3","202004041609538011");//指定执行人有哪些    
        ProcessInstance instance = processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey,variables);
        System.out.println("流程实例ID:"+instance.getId());//f93d4691-8f66-11ea-89af-509a4c33f08d
        System.out.println("流程定义ID:"+instance.getProcessDefinitionId());//ActivitiDemoProcess:1:e6c9eae0-8f66-11ea-89af-509a4c33f08d
		return new BaseResponseResult(200,"success",null);
    }
    
    //完成任务
    @GetMapping("/activiti/finishTask")
    public BaseResponseResult activitiFinishTask() {
        String taskId = "f942018b-8f66-11ea-89af-509a4c33f08d";//任务ID
        HashMap<String, Object> variables=new HashMap<>();
        variables.put("assignee2","202004041601405691");
        processEngine.getTaskService().complete(taskId,variables);
        System.out.println("完成任务，任务ID："+taskId);
		return new BaseResponseResult(200,"success",null);
    }
    
    //查询当前人的个人任务
    @GetMapping("/activiti/userList")
    public BaseResponseResult activitiUserList() {
        List<Task> list = processEngine.getTaskService().createTaskQuery().list();
        if(list!=null && list.size()>0){
            for(Task task:list){
                System.out.println("任务ID:"+task.getId());//f942018b-8f66-11ea-89af-509a4c33f08d
                System.out.println("任务名称:"+task.getName());//assignee2
                System.out.println("任务的创建时间:"+task.getCreateTime());//Wed May 06 14:58:21 CST 2020
                System.out.println("任务的办理人:"+task.getAssignee());//null
                System.out.println("流程实例ID："+task.getProcessInstanceId());//f93d4691-8f66-11ea-89af-509a4c33f08d
                System.out.println("执行对象ID:"+task.getExecutionId());//f93de2d8-8f66-11ea-89af-509a4c33f08d
                System.out.println("流程定义ID:"+task.getProcessDefinitionId());//ActivitiDemoProcess:1:e6c9eae0-8f66-11ea-89af-509a4c33f08d
                System.out.println("getOwner:"+task.getOwner());//null
                System.out.println("getCategory:"+task.getCategory());//null
                System.out.println("getDescription:"+task.getDescription());//null
                System.out.println("getFormKey:"+task.getFormKey());//null
                Map<String, Object> map = task.getProcessVariables();
                for (Map.Entry<String, Object> m : map.entrySet()) {
                    System.out.println("key:" + m.getKey() + " value:" + m.getValue());
                }
                for (Map.Entry<String, Object> m : task.getTaskLocalVariables().entrySet()) {
                    System.out.println("key:" + m.getKey() + " value:" + m.getValue());
                }
            }
        }
		return new BaseResponseResult(200,"success",null);
    }
    
    //查看流程
    @GetMapping("/activiti/list")
    public BaseResponseResult activitiList() {
    	List<ProcessInstance> list = processEngine.getRuntimeService().createProcessInstanceQuery().processDefinitionKey("ActivitiDemoProcess").list();
    	for(int i=0;i<list.size();i++){
    		System.out.println(list.get(i).getId()+","+list.get(i).getBusinessKey()+","+list.get(i).getStartUserId());
    	}
		return new BaseResponseResult(200,"success",null);
    }

}
