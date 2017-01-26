package com.atguigu.jf.console.trigger.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.jf.console.comm.utils.MailTestMy;
import com.atguigu.jf.console.trigger.service.CreateExcelService;

public class TriggerServiceImpl {
	
	@Autowired
	private CreateExcelService createExcelService;
	
	
	
	
	public void doIt(){
		System.out.println("执行了创建excel方法:" + new Date());
		try {
			String fileName = createExcelService.createExcel();	//创建Excel表，并返回表名
			MailTestMy.sendEmail2(fileName);	//去工具类中调用发送邮件的方法
			System.out.println("创建excel的路径是:" + fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
