package com.atguigu.jf.console.test;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.pool.DruidDataSource;
import com.atguigu.jf.console.trigger.service.impl.TriggerServiceImpl;

/**
 * 数据库连接池的测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class DBTest {
	@Autowired
	private DruidDataSource dataSource;
	@Autowired
	private TriggerServiceImpl triggerServiceImpl;
	
	
	@Test
	public void testDB(){
		try {
			System.out.println(dataSource.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testCreateExcel(){
		triggerServiceImpl.doIt();
	}
}
