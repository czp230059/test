package com.test2;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;



//测试
public class ActivitiDemo {
	//流程引擎
	ProcessEngine processEngine=ProcessEngineConfiguration
			.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
			.buildProcessEngine();
//	//发布流程方式1
//	@Test
//	public void deplay(){
//		System.out.println(processEngine);
//		Deployment deployment=processEngine.getRepositoryService()
//				.createDeployment()
//				.addClasspathResource("f/hehe.bpmn")
//				.addClasspathResource("f/hehe.png")
//				.name("请假流程")
//				.deploy();
//		//获取流程id
//		System.out.println("流程id:"+deployment.getId());
//		//获取流程name
//		System.out.println("流程name:"+deployment.getName());
//	}
	//发布流程方式2
	@Test
	public void deplay1(){
		InputStream in=this.getClass()
				.getClassLoader().getResourceAsStream("f/first.zip");
		//转换zip
		ZipInputStream zip=new ZipInputStream(in);
		Deployment deployment=processEngine.getRepositoryService()
				.createDeployment()
				.name("first流程发布")
				.addZipInputStream(zip)
				.deploy();
		System.out.println("---->"+deployment.getId());
		System.out.println("---->"+deployment.getName());
	}
	//查看流程定义
//	@Test
//	public void chlcdy(){
//		List<ProcessDefinition>list=processEngine.getRepositoryService()
//				.createProcessDefinitionQuery()
//				.list();
//		//遍历
//		for (ProcessDefinition p : list) {
//			System.out.println("id:"+p.getDeploymentId()+"name:"+p.getName());
//		}
//	}
	//删除流程定义
	@Test
	public void sclcdy(){
//		//普通删除（如果有正在执行的流程，就会抛异常）
//		processEngine.getRepositoryService()
//		.deleteDeployment("1001");
		//级联（定义信息，执行信息，历史信息）
		processEngine.getRepositoryService()
		.deleteDeployment("1101",true);
	}
	//查看流程状态
	@Test
	public void cklczt(){
		ProcessInstance pi=processEngine.getRuntimeService()
				.createProcessInstanceQuery()
				.processInstanceId("1201")
				.singleResult();
		if(pi!=null){
			System.out.println(pi.getId()+"流程正在执行");
		}else{
			System.out.println("流程结束！");
		}
	}
	//历史流程
	@Test
	public void ckls(){
		List<HistoricTaskInstance>list=processEngine.getHistoryService()
		.createHistoricTaskInstanceQuery()
		.taskAssignee("王五")
		.list();
		//遍历
		for (HistoricTaskInstance hi : list) {
			System.out.println("任务id："+hi.getId());
			System.out.println("进程实例id："+hi.getProcessInstanceId());
			System.out.println("任务办理人："+hi.getAssignee());
			System.out.println("执行对象id："+hi.getExecutionId());
		}
	}
	
	
	
	
	
	
	
	
	//启动流程
	@Test
	public void ins(){
		ProcessInstance pi=processEngine.getRuntimeService()
				.startProcessInstanceByKey("hehe");
		//流程启动id
		System.out.println("流程id:"+pi.getId());
		System.out.println("流程activiti:"+pi.getActivityId());
	}
	@Test
	public void chlc(){
		//查看人
		String dbz="王五";
		//获取任务列表
		List<Task> list=processEngine.getTaskService()
				.createTaskQuery()
				.taskAssignee(dbz)
				.list();
		System.out.println("list:"+list.size());
		//遍历
		for (Task task : list) {
			System.out.println("任务id:"+task.getId());
			System.out.println("任务名称:"+task.getName());
		}
	}
	@Test
	public void blrw(){
		String blr="王五";
		//办理任务
		processEngine.getTaskService()
		.complete("1502");
		//办理完成
		System.out.println(blr+"------>:ok!");
	}
}
