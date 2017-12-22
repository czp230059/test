package com.test1;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

//初始化activitie数据库
//23种activitie表
//第一种 代码
//第二种activitie.cfg.xml
public class initDB {
	@Test
	public void aDB(){
		ProcessEngineConfiguration cfg
		=ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		//设置主机
		cfg.setJdbcUrl("jdbc:mysql://localhost:3306/acti");
		//驱动
		cfg.setJdbcDriver("com.mysql.jdbc.Driver");
		//账号
		cfg.setJdbcUsername("root");
		//密码
		cfg.setJdbcPassword("root");
		
		//策略
		cfg.setDatabaseSchemaUpdate
		(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		//获取流程引擎
		ProcessEngine processEngine=cfg.buildProcessEngine();
		System.out.println(processEngine);
	}
	@Test
	public void aDB1(){
		//获取流程引擎
				ProcessEngine processEngine=ProcessEngineConfiguration
						.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
						.buildProcessEngine();
				System.out.println(processEngine);
	}
}
