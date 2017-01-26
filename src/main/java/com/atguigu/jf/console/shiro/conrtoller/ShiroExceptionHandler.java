package com.atguigu.jf.console.shiro.conrtoller;

import org.springframework.web.bind.annotation.ExceptionHandler;


public class ShiroExceptionHandler {
	
	@ExceptionHandler(value=org.apache.shiro.authz.UnauthorizedException.class)
	public String handleShiroException(Exception ex) {
		System.out.println("shiro注解发生异常！");
		
		return "redirect:/unauthorized.jsp";
	}
}
