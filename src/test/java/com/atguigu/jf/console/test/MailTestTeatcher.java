package com.atguigu.jf.console.test;

import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class MailTestTeatcher {
	/**
	 * @throws MessagingException 
	 * @throws AddressException 
	 * @方法名: sendEmail  
	 * @功能描述: 发送邮件
	 * @作者  
	 * @日期 Dec 5, 2016
	 */
	@Test
	public void sendEmail() throws AddressException, MessagingException{
		/**
		 * 1、通过session创建邮件的配置信息
			2、创建代表邮件内容的Message对象（JavaMail创建的邮件是基于MIME协议的）
			3、创建Transport对象、连接服务器、发送Message、关闭连接
		 */
		Properties props = new Properties();
		// 配置项参考文档
		// 邮件服务器的地址
		props.setProperty("mail.host", "smtp.sina.com");
		props.setProperty("mail.transport.protocol", "smtp");
		// 开启认证
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		// 调试完毕后，注释调
		session.setDebug(true);
		
		/**
		 * 2、创建代表邮件内容的Message对象（JavaMail创建的邮件是基于MIME协议的）
		 */
		MimeMessage message = new MimeMessage(session);
		// 发件人
		message.setFrom(new InternetAddress("atguigu123@sina.com"));
		// 设置主题
		message.setSubject("您好，这是本次的报表");
		// 设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress("ltzhou@atguigu.com"));
		// 设置正文
		// 纯文本的邮件
		message.setText("2016年12月5日 09:30:53，这是本次的报表");
		
		/**
		 * 3、创建Transport对象、连接服务器、发送Message、关闭连接
		 */
		Transport tran = session.getTransport();
		tran.connect("smtp.sina.com", "atguigu123@sina.com", "atguigu123qwe");
		tran.sendMessage(message, message.getAllRecipients());
		// 关闭连接
		tran.close();
		
	}

}
