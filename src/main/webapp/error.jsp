<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/commHeader.jsp" %>
<script type="text/javascript">
	
	$(function(){
		$("button").click(function(){
			//回到浏览器历史记录中的上一页，相当于点击后退按钮
			window.history.back();
		});
	});
	
</script>
</head>
<body>
		<center>
			<h2>对不起，您没有该访问权限！</h2>
			<br/>
			<button>回到上一页</button>
		</center>
</body>
</html>