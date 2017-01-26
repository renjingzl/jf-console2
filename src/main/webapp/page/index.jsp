<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <title>首页</title>
    <%@include file="/WEB-INF/commHeader.jsp" %>
</head>

<body class="main">
    <div class="default-cnt clearfix">
        <div class="change-skin">
            <i class="skin-ico"></i>
        </div>
        <div class="nav">
            <img src="<%=rootPath %>/theme/gray/images/logo.png" alt="小积快跑后台管理系统" />
            <ul id="menu" class="menu">
                
            </ul>
        </div>
        <!-- 右侧主内容 -->
        <div class="aside-rgt">
            <div class="aside-cnt">
                <div class="head-tit">
                    <h1 class="cnt-tit" id="cnt-title">标题提示</h1>
                    <div class="user-info">
                        <img src="<%=rootPath %>/theme/gray/images/po.jpg" class="portrait" />
                        <em class="user-xx">您好，<i><shiro:principal property="opName"></shiro:principal></i></em>
                        <em class="logout">注销</em>
                    </div>
                </div>
                <div class="">
                    <%-- <iframe src="<%=rootPath %>/page/user/userMgnt.jsp" frameborder="0" scrolling="no" height="2300px" width="100%" id="indexiframe"></iframe> --%>
                    <iframe src="demo/grid.html" frameborder="0" scrolling="no" height="2300px" width="100%" id="indexiframe"></iframe>
                </div>
            </div>
        </div>
        <!-- foot-->
        <div class="clearfix"></div>
        <div class="footer">
            <span>联系电话：xxxxxx</span>
            <span>传真号码：xxxxxx</span>
            <span>地址：xxxxxx</span>
            <span>版权：xxxxxx</span>
        </div>
    </div>
    <script src="<%=rootPath %>/page/common/js/index_page.js"></script>
    <script src="<%=rootPath %>/page/index.js"></script>
</body>

</html>