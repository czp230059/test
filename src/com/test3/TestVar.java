package com.test3;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class TestVar {
	// 流程引擎
	ProcessEngine processEngine = ProcessEngineConfiguration
			.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();

	// 发布流程
	@Test
	public void deplay() {
		System.out.println(processEngine);
		Deployment deployment = processEngine.getRepositoryService().createDeployment()
				.addClasspathResource("f/hehe.bpmn")
				.addClasspathResource("f/hehe.png")
				.name("请假流程").deploy();
		// 获取流程id
		System.out.println("流程id:" + deployment.getId());
		// 获取流程name
		System.out.println("流程name:" + deployment.getName());
	}

	// 启动流程:张三
	@Test
	public void ins() {
		//传参数map
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("userid", "小凡");//提交
		map.put("userid1", "雪琪");//初审
		map.put("userid2", "碧瑶");//终审
		// 启动流程
		ProcessInstance pi = processEngine.getRuntimeService()
				.startProcessInstanceByKey("hehe",map);
		// 流程启动id
		System.out.println("流程id:" + pi.getId());
		System.out.println("流程activiti:" + pi.getActivityId());
	}

	// 查看流程代办
	@Test
	public void chlc() {
		// 查看人
		String dbz = "小凡";
		// 获取任务列表
		TaskService server = processEngine.getTaskService();
		Task task = server.createTaskQuery()
				.taskAssignee(dbz)
				.singleResult();
		// 设置变量 请假2天 原因 约会 现在请假
		// server.setVariable(task.getId(), "请假原因", "约会");
		// server.setVariable(task.getId(), "天数", "2天");
		// server.setVariable(task.getId(), "请假时间", new Date());
//		Map<String, Object> var = new HashMap<String, Object>();
//		var.put("请假原因", "约会");
//		var.put("天数", "2天");
//		var.put("请假时间", "2018-01-01");
//		server.setVariables(task.getId(), var);
		// 遍历
		System.out.println("任务id:" + task.getId());
		System.out.println("任务名称:" + task.getName());
	}

	// 办理任务
	@Test
	public void blrw() {
		String blr = "小凡";
		// 办理任务
		processEngine.getTaskService().complete("107");
		// 办理完成
		System.out.println(blr + "------>:ok!");
	}

	// 查看流程代办:李四
	@Test
	public void chlc1() {
		// 查看人
		String dbz = "雪琪";
		// 获取任务列表
		TaskService server = processEngine.getTaskService();
		Task task = server.createTaskQuery()
				.taskAssignee(dbz)
				.singleResult();
		// 查看遍历
		// Map<String, Object> map = server.getVariables(task.getId());
		// Set<String> set = map.keySet();
		// for (String key : set) {
		// System.out.println("key:" + key + "value:" + map.get(key));
		// }
//		System.out.println("请假原因:" + server.getVariable(task.getId(), "请假原因"));
//		System.out.println("天数:" + server.getVariable(task.getId(), "天数"));
//		System.out.println("请假时间:" + server.getVariable(task.getId(), "请假时间"));
//		// 提出意见
//		server.setVariable(task.getId(), "李四批复", "好好约会，完全同意");
		// 遍历
		System.out.println("任务id:" + task.getId());
		System.out.println("任务名称:" + task.getName());
	}

	// 办理任务
	@Test
	public void blrw1() {
		String blr = "雪琪";
		// 办理任务
		processEngine.getTaskService().complete("202");
		// 办理完成
		System.out.println(blr + "------>:ok!");
	}

	// 查看流程代办:王五
	@Test
	public void chlc2() {
		// 查看人
		String dbz = "碧瑶";
		// 获取任务列表
		TaskService server = processEngine.getTaskService();
		Task task = server.createTaskQuery()
				.taskAssignee(dbz)
				.singleResult();
//		// 查看遍历
//		Map<String, Object> map = server.getVariables(task.getId());
//		Set<String> set = map.keySet();
//		for (String key : set) {
//			System.out.println("" + key + "-----:" + map.get(key));
//		}
//		// 提出意见
//		server.setVariable(task.getId(), "王五批复", "好好约会，完全同意");
		// 遍历
		System.out.println("任务id:" + task.getId());
		System.out.println("任务名称:" + task.getName());
	}

	// 办理任务
	@Test
	public void blrw2() {
		String blr = "碧瑶";
		// 办理任务
		processEngine.getTaskService().complete("302");
		// 办理完成
		System.out.println(blr + "------>:ok!");
	}

	// 查询历史变量
	@Test
	public void cxlsbl() {

		List<HistoricVariableInstance> list = processEngine.getHistoryService()
				.createHistoricVariableInstanceQuery()
				// .variableName("天数")
				.list();
		// 遍历
		for (HistoricVariableInstance hi : list) {
			System.out.println(" id:" + hi.getId() + 
							   " taskid:" + hi.getVariableName() + 
							   " value:" + hi.getValue()+ 
							   " 流程:" + hi.getProcessInstanceId());
		}
	}
	//指定代办人(两种)
	@Test
	public void zddbr() {
		
	}
}
